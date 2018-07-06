package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Estado;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 
 */
public class TestePersistirCidade {
    EntityManager em;
    public TestePersistirCidade() {
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
            Cidade cidade = new Cidade();
            cidade.setNome("Cruzeiro");
            cidade.setEstado(em.find(Estado.class, 2));
            em.getTransaction().begin();
            em.persist(cidade);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false,exception);
    }
    
}
