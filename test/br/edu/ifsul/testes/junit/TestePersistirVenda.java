/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Categoria;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Venda;
import br.edu.ifsul.modelo.VendaItens;
import static java.time.temporal.TemporalQueries.zone;
import java.util.Calendar;
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
public class TestePersistirVenda {
    EntityManager em;
    public TestePersistirVenda() {
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
            PessoaFisica pf = em.find(PessoaFisica.class, 2);
            Venda venda = new Venda();
            venda.setData(Calendar.getInstance());
            venda.setParcelas(3);
            venda.setPessoaFisica(pf);
            VendaItens vendaItens = new VendaItens();
            vendaItens.setProduto(produto);
            vendaItens.setQuantidade(5.0);
            vendaItens.setValorUnitario(produto.getPreco());
            vendaItens.setValorTotal(vendaItens.getQuantidade() * vendaItens.getValorUnitario());
            venda.adicionarItem(vendaItens); //por fazer isso, eu só necessito persistir a venda
            em.getTransaction().begin();
            em.persist(venda);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
                        
        }
        
        Assert.assertEquals(false, exception);
    }
    
}
