/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.sg.model.dao.hibernate;

import java.util.List;
import br.edu.ifpe.garanhuns.sg.model.dao.interfaces.PostoSaudeDAO;
import br.edu.ifpe.garanhuns.sg.model.Atendente;
import br.edu.ifpe.garanhuns.sg.model.Endereco;
import br.edu.ifpe.garanhuns.sg.model.Paciente;
import br.edu.ifpe.garanhuns.sg.model.PostoSaude;
import org.hibernate.Session;
import br.edu.ifpe.garanhuns.sg.util.HibernateUtil;

/**
 *
 * @author Herikles
 */
public class PostoSaudeHibernate implements PostoSaudeDAO {

    @Override
    public void inserir(PostoSaude o) {
        Session session = HibernateUtil.getSession();
        EnderecoHibernate eh = new EnderecoHibernate();
        try {
            session.beginTransaction();
            Endereco e = eh.recuperarPorLogradouro(o.getEndereco().getLogradouro());
            if (e == null) {
                eh.inserir(o.getEndereco());
            } else {
                o.setEndereco(e);
            }
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Posto de Saude. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void atualizar(PostoSaude o) {
        Session session = HibernateUtil.getSession();
        EnderecoHibernate eh = new EnderecoHibernate();
        try {
            session.beginTransaction();
            Endereco e = eh.recuperarPorLogradouro(o.getEndereco().getLogradouro());
            if (e == null) {
                eh.inserir(o.getEndereco());
            }
            else {
                o.setEndereco(e);
            }
            session.update(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao alterar Posto de Saude. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void deletar(PostoSaude o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover Posto de Saude. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public PostoSaude recuperar(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            return (PostoSaude) session.get(PostoSaude.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar PostoSaude. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<PostoSaude> recuperarTodos() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<PostoSaude> lista = session.createQuery("from " + PostoSaude.class.getName()).list();
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Postos de Saude. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void deletarEmCascata(PostoSaude ps) {
        PacienteHibernate ph = new PacienteHibernate();
        AtendenteHibernate ah = new AtendenteHibernate();

        List<Paciente> todosPacientes = ph.recuperarTodos();
        List<Atendente> todosAtendentes = ah.recuperarTodos();

        for (Paciente p : todosPacientes) {
            if (p.getEndereco().getId() == ps.getId()) {
                ph.deletar(p);
            }
        }
        for (Atendente a : todosAtendentes) {
            if (a.getPostoSaude().getId() == ps.getId()) {
                ah.deletar(a);
            }
        }

        deletar(ps);
    }

    @Override
    public PostoSaude recuperarPorNome(String name) {
        try (Session session = HibernateUtil.getSession()) {
            List<PostoSaude> posto = (session.createQuery("from PostoSaude p where p.nome = :name").setParameter("name", name).list());
            if ((posto != null)||!posto.isEmpty() ) {
                return posto.get(0);
            }

        } catch (Exception e) {
            System.err.println("Falha ao recuperar o PostoSaude por nome. Erro: " + e.toString());
        }
        return null;
    }

}
