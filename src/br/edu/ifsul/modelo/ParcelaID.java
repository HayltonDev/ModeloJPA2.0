/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Haylto.
 * essa classe seria equivalente a apenas er uma função como chave composta das duas classes, tanto para VENDA, quanto para PARCELA, onde venda vem o id e parcela é o próprio numero, com a junção dos dois atributos, temos a chave composta 
 */
@Embeddable //essa anotação quer dizer que essa classe é uma classe embutível em outra
public class ParcelaID implements Serializable{
    @NotNull(message = "O numero não pode ser nulo")
    @Column(name="numero", nullable = false)
    private Integer numero; //atributo que identifica a parcela.
    @NotNull(message = "A venda não pode ser nula")
    @ManyToOne
    @JoinColumn(name="venda", referencedColumnName = "id", nullable = false) //atributo que vem da classe venda que identifica venda. 
    private Venda venda; 

    public ParcelaID() {
    }
    
    

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.numero);
        hash = 97 * hash + Objects.hashCode(this.venda);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParcelaID other = (ParcelaID) obj;
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.venda, other.venda)) {
            return false;
        }
        return true;
    }
    
    
}
