/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Categoria;
import br.edu.ifsul.modelo.Foto;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.Produto;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class TestePersistirFoto {
    EntityManager em;
    public TestePersistirFoto() {
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
            Produto produto = em.find(Produto.class, 1);
            Foto foto = new Foto();
            foto.setNome("galaxy-s9.jpg");
            foto.setDescricao("Foto do produto");
            Path path = Paths.get("C:\\Desenvolvimento\\Java\\Libs\\galaxy-s9.jpg");
            foto.setArquivo(Files.readAllBytes(path));
            produto.adicionarFoto(foto);
            em.getTransaction().begin();
            em.persist(foto);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
                        
        }
        
        Assert.assertEquals(false, exception);
    }
    
}
