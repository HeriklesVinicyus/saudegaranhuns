/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.model.testes;

import br.edu.ifpe.garanhuns.model.helper.DbUnitHelper;
import br.edu.ifpe.garanhuns.sg.model.Bairro;
import br.edu.ifpe.garanhuns.sg.model.dao.hibernate.BairroHibernate;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author herik
 */
public class BairroTest {

    private static BairroHibernate bH;
    private static DbUnitHelper dbUnitHelper;

    public BairroTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        dbUnitHelper = new DbUnitHelper();
        bH = new BairroHibernate();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dbUnitHelper.deleteAll("/tabelas/Bairro.xml");
        JOptionPane.showMessageDialog(null, "delete");
        dbUnitHelper.cleanInsert("/tabelas/Bairro.xml");
        JOptionPane.showMessageDialog(null, "insert");
    }

    @After
    public void tearDown() {
        dbUnitHelper.deleteAll("/tabelas/Bairro.xml");
    }

    @Test
    public void deveRetornarBairroPorId() {
        Bairro bairro = bH.recuperar(9);
        JOptionPane.showMessageDialog(null, bairro);
        Bairro b = new Bairro("Bairro1");
        Assert.assertEquals(b.getNome(), b.getNome());
    }
}
