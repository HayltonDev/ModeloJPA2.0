package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author t1076986
 */
@Entity
@Table(name="produto")
public class Produto implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_produto", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotBlank(message="O nome deve ser preenchida")
    @NotNull(message="O nome não pode ser nulo")
    @Length(max =50, message="O nome pode ter até {max} caracteres")
    @Column(name = "nome",length = 50, nullable = false)
    private String nome; 
    
    
    @NotNull(message="O preco não pode ser nulo")
    @Column(name = "preco", nullable = false)
    private Double preco;
    
    
    @NotNull(message="A quantidade em estoque não pode ser nula")
    @Column(name = "quantiade_estoque", nullable = false)
    private Double quantiadeEstoque;
    
    @NotBlank(message="A descrição deve ser preenchida")
    @NotNull(message="A descrição não pode ser nula")
    @Column(name = "descricao", nullable = false)  
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "id", nullable = false)
    @ForeignKey(name="fk_categoria")
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn(name="marca", referencedColumnName = "id", nullable = false)
    @ForeignKey(name="fk_marca")
    private Marca marca;

    public Produto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getQuantiadeEstoque() {
        return quantiadeEstoque;
    }

    public void setQuantiadeEstoque(Double quantiadeEstoque) {
        this.quantiadeEstoque = quantiadeEstoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}
