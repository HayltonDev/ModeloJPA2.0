/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Endereco;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.TipoEndereco;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Haylton
 */
public class TestePersistirEndereco {
    EntityManager em;
    public TestePersistirEndereco() {
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
            PessoaFisica pf = em.find(PessoaFisica.class,2 );
            Endereco endereco = new Endereco();
            endereco.setBairro("Centro");
            endereco.setCep("73752-065");
            endereco.setComplemento("AP 152");
            endereco.setEndereco("QA 6 MR" );
            endereco.setNickname("Casa");
            endereco.setNumero("6");
            endereco.setReferencia("Perto do supermercado");
            endereco.setTipoEndereco(em.find(TipoEndereco.class, 1));
            pf.adicionarEndereco(endereco);
            em.getTransaction().begin();
              /* 
                Pq eu persisto somente se a Pessoa Física em vez de endereco? 
                Pq primeiro a associação de endereco com a classe Pessoa é que 
                para endereco(s) existir, tem que existir a pessoa, então é uma 
                associação composta, só olhar o diagrama que tem o diamante. 
            Quando eu persisto somente pessoa fisica, eu estou ali emcima chamando o metodo de adicionar endereco que está na classe pessoa
                public void adicionarEndereco(Endereco endereco){
                    endereco.setPessoa(this);
                    this.enderecos.add(endereco);
                }
            Esse método vai fazer com que seja feita a persistencia do endereco pois foi passado em seu argumento o Endereco,
            pois além disso, foi declarado em PESSOA que endereco seria composto de cascade
                 @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY )
                 List<Endereco> enderecos = new ArrayList<>();
            Pois com o cacadeType.ALL eu mapeie todas as operações de persistencia que será passada para as filhas
            
            */
            em.persist(pf);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
            
        }
        
        Assert.assertEquals(false, exception);
    }
    
}
