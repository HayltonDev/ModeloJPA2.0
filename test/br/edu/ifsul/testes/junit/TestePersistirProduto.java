/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Categoria;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.Produto;
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
public class TestePersistirProduto {
    EntityManager em;
    public TestePersistirProduto() {
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
            Produto produto = new Produto();
            produto.setNome("Galaxy S9");
            produto.setDescricao("Smartphone de lançamento em 2018 com Infinity Screen");
            produto.setPreco(Double.valueOf(4000));
            produto.setQuantiadeEstoque(Double.valueOf(10));
            produto.setMarca(em.find(Marca.class, 1));
            produto.setCategoria(em.find(Categoria.class, 3));
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
                        
        }
        
        Assert.assertEquals(false, exception);
    }
    
}
