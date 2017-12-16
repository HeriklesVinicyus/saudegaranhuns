/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.sg.util;

import br.edu.ifpe.garanhuns.sg.model.Atendimento;
import br.edu.ifpe.garanhuns.sg.model.Bairro;
import br.edu.ifpe.garanhuns.sg.model.Endereco;
import br.edu.ifpe.garanhuns.sg.model.HorarioAtendimento;
import br.edu.ifpe.garanhuns.sg.model.PostoSaude;
import br.edu.ifpe.garanhuns.sg.model.dao.hibernate.BairroHibernate;
import br.edu.ifpe.garanhuns.sg.model.dao.hibernate.HorarioAtendimentoHibernate;
import br.edu.ifpe.garanhuns.sg.model.dao.hibernate.PostoSaudeHibernate;
import br.edu.ifpe.garanhuns.sg.model.enumarador.DiasSemana;
import br.edu.ifpe.garanhuns.sg.model.enumarador.Especialidade;

/**
 *
 * @author nosso
 */
public class NewClass {

    public static void main(String[] args) {
        HorarioAtendimentoHibernate hH = new HorarioAtendimentoHibernate();
        hH.inserir(new HorarioAtendimento(DiasSemana.SEGUNDA, "08:00", "12:00", 10, new Atendimento(Especialidade.GERAL, new PostoSaude("Casa de deus", new Endereco("0", "Rua do cão 2", new Bairro("COHAB 6"))))));
        hH.inserir(new HorarioAtendimento(DiasSemana.SEGUNDA, "08:00", "12:00", 10, new Atendimento(Especialidade.GERAL, new PostoSaude("Casa de deus", new Endereco("0", "Rua do cão 3", new Bairro("COHAB 5"))))));
         System.out.println(new PostoSaudeHibernate().recuperarPorNome("Casa de deus"));
        System.out.println(new BairroHibernate().recuperarPorNome("COHAB 6") + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
    }
}
