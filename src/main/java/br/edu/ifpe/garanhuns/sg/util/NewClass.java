/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.sg.util;

import br.edu.ifpe.garanhuns.sg.model.Bairro;
import br.edu.ifpe.garanhuns.sg.model.dao.hibernate.BairroHibernate;

/**
 *
 * @author nosso
 */
public class NewClass {

    public static void main(String[] args) {
        Bairro b = new Bairro("asd");
        BairroHibernate bH = new BairroHibernate();
        bH.inserir(b);
    }
}
