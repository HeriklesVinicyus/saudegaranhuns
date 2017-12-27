/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.model.testes;

import br.edu.ifpe.garanhuns.model.helper.DbUnitHelper;
import br.edu.ifpe.garanhuns.sg.model.Bairro;
import br.edu.ifpe.garanhuns.sg.model.dao.hibernate.BairroHibernate;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Hérikles
 */
public class BairroTest {

    private static DbUnitHelper dbUnitHelper;
    private static BairroHibernate bH;

    public BairroTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        dbUnitHelper = new DbUnitHelper("DbUnitXml");
        bH = new BairroHibernate();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dbUnitHelper.execute(DatabaseOperation.DELETE_ALL, "Bairro.xml");
        dbUnitHelper.execute(DatabaseOperation.INSERT, "Bairro.xml");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void deveRetornarBairroPorId() {
        Bairro bairro = bH.recuperar(9);
        Bairro b = new Bairro("COHAB 9");
        Assert.assertEquals(bairro.getNome(), b.getNome());
    }
}
