/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.sg.model.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifpe.garanhuns.sg.model.dao.interfaces.ConsultaDAO;
import br.edu.ifpe.garanhuns.sg.model.Consulta;
import br.edu.ifpe.garanhuns.sg.model.Paciente;
import br.edu.ifpe.garanhuns.sg.model.enumarador.Status;
import org.hibernate.Session;
import br.edu.ifpe.garanhuns.sg.util.HibernateUtil;

/**
 *
 * @author Hérikles
 */
public class ConsultaHibernate implements ConsultaDAO {

    @Override
    public void inserir(Consulta o) {
        Session session = HibernateUtil.getSession();
        PacienteHibernate pH = new PacienteHibernate();
        try {
            session.beginTransaction();
            Paciente p = pH.recuperarPorNome((o.getPaciente()).getNome());
            if (p == null) {
                pH.inserir(o.getPaciente());
            } else {
                o.setPaciente(p);
            }
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Consulta. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void atualizar(Consulta o) {
        Session session = HibernateUtil.getSession();
        PacienteHibernate pH = new PacienteHibernate();
        try {
            session.beginTransaction();
            Paciente p = pH.recuperarPorNome((o.getPaciente()).getNome());
            if (p == null) {
                pH.inserir(o.getPaciente());
            } else {
                o.setPaciente(p);
            }
            session.update(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar Consulta. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void deletar(Consulta o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover Consulta. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public Consulta recuperar(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            return (Consulta) session.get(Consulta.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar Consulta. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Consulta> recuperarTodos() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<Consulta> lista = session.createQuery("from " + Consulta.class.getName()).getResultList();
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os Consultas. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Consulta> recuperarConsultasPorPaciente(Paciente c) {
        try (Session session = HibernateUtil.getSession()) {
            List<Consulta> consulta = (session.createQuery("from Consulta c where c.CartaoSus = :cartao").setParameter("cartao", c.getCartaoSus()).list());
            if(consulta!=null)
                return consulta;
            
        } catch (Exception e) {
            System.err.println("Falha ao recuperar o Consultas Por Paciente. Erro: " + e.toString());
        }
        return null;
    }

    @Override
    public void alterarStatusConsulta(Consulta c, int i) {
        switch(i){
            case 1:
                c.setStatus(Status.FILA);
            break;
            case 2:
                c.setStatus(Status.AGENDADO);
            break;
            case 3:
                c.setStatus(Status.CANCELADO);
            break;   
            default:
                throw new IllegalArgumentException("Agumento invalido!");
        }
        atualizar(c);
    }

}
