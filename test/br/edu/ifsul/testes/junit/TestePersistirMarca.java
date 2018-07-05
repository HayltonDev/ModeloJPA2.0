/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Marca;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author t1076986
 */
public class TestePersistirMarca {
    EntityManager em;
    public TestePersistirMarca() {
    }
    
    @Before
    public void setUp() {
        em = EntityManagerUtil.getEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
    }
    
    @Test
    public void teste(){
        boolean exception = false;
        try {
            Marca marca = new Marca();
            marca.setNome("Microsoft");
            em.getTransaction().begin();
            em.persist(marca);
            em.getTransaction().commit();
            
        } catch (Exception e) {
          exception = true;
          e.printStackTrace();
        }
        
        Assert.assertEquals(false, exception);
    }
    
}
