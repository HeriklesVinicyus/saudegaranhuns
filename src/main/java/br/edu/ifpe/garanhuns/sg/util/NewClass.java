/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.sg.util;

import br.edu.ifpe.garanhuns.sg.model.PostoSaude;
import br.edu.ifpe.garanhuns.sg.model.dao.hibernate.ConsultaHibernate;
import br.edu.ifpe.garanhuns.sg.model.dao.hibernate.HorarioAtendimentoHibernate;
import br.edu.ifpe.garanhuns.sg.model.dao.hibernate.PostoSaudeHibernate;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author nosso
 */
public class NewClass {
    
    public static void main(String[] args) {
        HorarioAtendimentoHibernate hH = new HorarioAtendimentoHibernate();
        ConsultaHibernate cH = new ConsultaHibernate();

        /*hH.inserir(new HorarioAtendimento(DiasSemana.SEGUNDA, "08:00", "12:00", 10, new Atendimento(Especialidade.GERAL, new PostoSaude("Casa de deus", new Endereco("0", "Rua do cão 2", new Bairro("COHAB 6"))))));
        hH.inserir(new HorarioAtendimento(DiasSemana.TERÇA, "08:00", "12:00", 10, new Atendimento(Especialidade.GERAL, new PostoSaude("Casa de deus2", new Endereco("0", "Rua do cão 3", new Bairro("COHAB 5"))))));
        hH.inserir(new HorarioAtendimento(DiasSemana.DOMINGO, "01:00", "12:00", 10, new Atendimento(Especialidade.GERAL, new PostoSaude("Casa de deus", new Endereco("0", "Rua do cão 2", new Bairro("COHAB 6"))))));
        hH.inserir(new HorarioAtendimento(DiasSemana.SEGUNDA, "03:00", "12:00", 10, new Atendimento(Especialidade.GERAL, new PostoSaude("Casa de deus2", new Endereco("0", "Rua do cão 3", new Bairro("COHAB 5"))))));
        hH.inserir(new HorarioAtendimento(DiasSemana.SEXTA, "07:00", "13:00", 10, new Atendimento(Especialidade.GERAL, new PostoSaude("Casa de deus", new Endereco("0", "Rua do cão 2", new Bairro("COHAB 6"))))));
        hH.inserir(new HorarioAtendimento(DiasSemana.TERÇA, "18:00", "19:00", 10, new Atendimento(Especialidade.GERAL, new PostoSaude("Casa de deus2", new Endereco("0", "Rua do cão 3", new Bairro("COHAB 5"))))));
         */
        LocalDate ld = LocalDate.now();
        LocalDate ld1 = LocalDate.of(2017, 12, 25);
        Period periodo = Period.between(ld , ld1);
        System.out.println(ld.getDayOfWeek());
        System.out.println(ld1.getDayOfWeek());
        System.out.println(ld1.getDayOfYear());
        System.out.println(ld1.plusDays(2));
        PostoSaude ps = new PostoSaudeHibernate().recuperarPorNome("Casa de deus");
        System.out.println("###4######################"+ps);
        System.out.println(hH.recuperarHorarioAtendimentoPorPostoSaude(ps));
        
        /*cH.inserir(new Consulta(Especialidade.GERAL, Prioridade.IDOSO, Status.FILA, ld, null, new Paciente("lala", "123456", LocalDate.of(1995, 06, 1), null, new PostoSaude("Casa de deus2", new Endereco("0", "Rua do cão 3", new Bairro("COHAB 5"))), new Usuario("123", "123", PerfilUsuario.ADMINISTRADOR))));
        cH.inserir(new Consulta(Especialidade.DENTISTA, Prioridade.BEBEDECOLO, Status.FILA, ld, null, new Paciente("lala1", "1234w56", LocalDate.of(1995, 06, 1), null, new PostoSaude("Casa de deus2", new Endereco("0", "Rua do cão 3", new Bairro("COHAB 5"))), new Usuario("123", "123", PerfilUsuario.ADMINISTRADOR))));
        cH.inserir(new Consulta(Especialidade.GERAL, Prioridade.DEFICIENTEFISICO, Status.FILA, ld, null, new Paciente("lalwa", "12w3456", LocalDate.of(1995, 06, 1), null, new PostoSaude("Casa de deus2", new Endereco("0", "Rua do cão 3", new Bairro("COHAB 5"))), new Usuario("123", "123", PerfilUsuario.ADMINISTRADOR))));
        cH.inserir(new Consulta(Especialidade.GERAL, Prioridade.NENHUMA, Status.FILA, ld, null, new Paciente("lawela", "123wwwww456", LocalDate.of(1995, 06, 1), null, new PostoSaude("Casa de deus2", new Endereco("0", "Rua do cão 3", new Bairro("COHAB 5"))), new Usuario("123", "123", PerfilUsuario.ADMINISTRADOR))));
        cH.inserir(new Consulta(Especialidade.DENTISTA, Prioridade.GESTANTE, Status.FILA, ld, null, new Paciente("lalweqwea", "1234wwewe56", LocalDate.of(1995, 06, 1), null, new PostoSaude("Casa de deus2", new Endereco("0", "Rua do cão 3", new Bairro("COHAB 5"))), new Usuario("123", "123", PerfilUsuario.ADMINISTRADOR))));
        *///cH.deletar(cH.recuperar(1));
        // System.out.println(new PostoSaudeHibernate().recuperarPorNome("Casa de deus"));
        //System.out.println(new BairroHibernate().recuperarPorNome("COHAB 6") + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
    }
}
