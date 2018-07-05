/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Pais;
import br.edu.ifsul.modelo.PessoaFisica;
import java.util.Calendar;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Haylton
 */
public class TestePersistirPessoaFisica {
    EntityManager em;
    
    public TestePersistirPessoaFisica() {
    }
    
    @Before
    public void setUp() {
        
        em = EntityManagerUtil.getEntityManager();
        
    }
    
    @After
    public void tearDown() {
        em.close();
    }
    
    @Test //seria equivalente ao PSVM
    public void teste(){
        boolean exception = false;
        try{
            PessoaFisica pf = new PessoaFisica();
            pf.setCpf("878.693.850-93");
            pf.setEmail("tes@gmail.com");
            pf.setNascimento(Calendar.getInstance());
            pf.setNome("Tes Rodrigues");
            pf.setRg("33225224");
            pf.setNomeUsuario("TesT");
            pf.setSenha("usuario");
            pf.setTelefone("999444456");
            em.getTransaction().begin();
            em.persist(pf);
            em.getTransaction().commit();
        } catch(Exception e){
            exception = true;
            e.printStackTrace();
        }
        
        Assert.assertEquals(false,exception); //tô verificando com o boolean que eu criei para saber se a transação ocorreu ou não
    }
    
}
