/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.sg.model.dao.hibernate;

import java.util.List;
import br.edu.ifpe.garanhuns.sg.model.dao.interfaces.HorarioAtendimentoDAO;
import br.edu.ifpe.garanhuns.sg.model.HorarioAtendimento;
import br.edu.ifpe.garanhuns.sg.model.PostoSaude;
import br.edu.ifpe.garanhuns.sg.util.ConnectionJDBC;
import org.hibernate.Session;
import br.edu.ifpe.garanhuns.sg.util.HibernateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Hérikles
 */
public class HorarioAtendimentoHibernate implements HorarioAtendimentoDAO {

    @Override
    public void inserir(HorarioAtendimento o) {
        Session session = HibernateUtil.getSession();
        AtendimentoHibernate aH = new AtendimentoHibernate();
        try {
            session.beginTransaction();
            aH.inserir(o.getAtendimento());
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar HorarioAtendimento. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void atualizar(HorarioAtendimento o) {
        Session session = HibernateUtil.getSession();
        AtendimentoHibernate aH = new AtendimentoHibernate();
        try {
            session.beginTransaction();
            aH.inserir(o.getAtendimento());
            session.save(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao salvar HorarioAtendimento. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public void deletar(HorarioAtendimento o) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao remover HorarioAtendimento. Erro: " + e.toString());
        } finally {
            session.close();
        }
    }

    @Override
    public HorarioAtendimento recuperar(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            return (HorarioAtendimento) session.get(HorarioAtendimento.class.getName(), id);
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar HorarioAtendimento. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<HorarioAtendimento> recuperarTodos() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            List<HorarioAtendimento> lista = session.createQuery("from " + HorarioAtendimento.class.getName()).list();
            session.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.err.println("Falha ao recuperar todos os HorarioAtendimento. Erro: " + e.toString());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<HorarioAtendimento> recuperarHorarioAtendimentoPorPostoSaude(PostoSaude ps) {
        
        System.out.println("#########################" + ps.getId());
        try (Connection connection = new ConnectionJDBC().getConnection()) {
            List<HorarioAtendimento> horariosAtendimento = new ArrayList<HorarioAtendimento>();
            String sql ="select * from HorarioAtendimento h where h.atendimento_id in (	select id from Atendimento a where a.postoSaude_id = "+ps.getId()+")";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HorarioAtendimento h_aux = new HorarioAtendimento();
                System.out.println(rs.getString("atendimento_id"));
            }
            /*horariosAtendimento = (session.createQuery(
                    "from HorarioAtendimento h"
                    + " where h.atendimento_id in ("
                    + "select id "
                    + "from Atendimento a "
                    + "where a.postoSaude_id = :id)").setParameter("id", ps.getId()).list());
            System.out.println("##############sldkfjlafj###########" + horariosAtendimento);
           */ if (horariosAtendimento != null && !horariosAtendimento.isEmpty()) {
                return horariosAtendimento;
            }

        } catch (Exception e) {
            System.err.println("Falha ao recuperar o Horario Atendimento Por PostoSaude. Erro: " + e.toString());
        }
        return null;
    }
}
