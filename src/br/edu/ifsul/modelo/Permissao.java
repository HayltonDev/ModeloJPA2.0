/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Haylton
 */
@Entity
@Table(name="permissao")
public class Permissao implements Serializable{
    @Id
    @NotBlank(message= "O nome não pode ser em branco")
    @NotNull(message= "O nome não pode ser nulo")
    @Length(max = 30, message = "O nome não pode ter mais de {max} caracteres")
    @Column(name= "nome", length = 30, nullable = false)
    private String nome;
    @NotBlank(message= "A descricao não pode ser em branco")
    @NotNull(message= "A descricao não pode ser nula")
    @Length(max = 30, message = "A descricao não pode ter mais de {max} caracteres")
    @Column(name="descricao", length = 40, nullable = false)
    private String descricao;

    public Permissao() {
    }
    
    

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nome);
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
        final Permissao other = (Permissao) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
    
}
