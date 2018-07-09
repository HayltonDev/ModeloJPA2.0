/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Categoria;
import br.edu.ifsul.modelo.Compra;
import br.edu.ifsul.modelo.CompraID;
import br.edu.ifsul.modelo.CompraItem;
import br.edu.ifsul.modelo.Marca;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.PessoaJuridica;
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
public class TestePersistirCompra {
    EntityManager em;
    public TestePersistirCompra() {
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
            PessoaJuridica pj = em.find(PessoaJuridica.class, 3);
            Compra compra = new Compra();
            CompraID id = new CompraID();
            id.setPessoaJuridica(pj);
            id.setNumeroNota(123456);
            compra.setCompraID(id);
            compra.setData(Calendar.getInstance());
            CompraItem compraItem = new CompraItem();
            compraItem.setCompra(compra);
            compraItem.setProduto(produto);
            compraItem.setValorUnitario(produto.getPreco());
            compraItem.setQuantidade(5.0);
            compraItem.setValorTotal(compraItem.getQuantidade() * compraItem.getValorUnitario());
            compra.adicionarItem(compraItem);
            em.getTransaction().begin();
            em.persist(compra);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
                        
        }
        
        Assert.assertEquals(false, exception);
    }
    
}
