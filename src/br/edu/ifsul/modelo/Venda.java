/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Haylton
 */
@Entity
@Table(name="venda")
public class Venda implements Serializable {
    @Id
    @SequenceGenerator(name="seq_venda", schema = "seq_venda_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_venda", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "A data não pode ser nula")
    @Temporal(TemporalType.TIMESTAMP) //alterei de data para TimeStamp
    @Column(name = "data",nullable = false)
    private Calendar data;
    @NotNull(message = "O valor total deve ser informado")
    @Min(value = 0, message = "O valor total não pode ser negativo")
    @Column(name = "valor_total", nullable = false, columnDefinition = "decimal(12,2)" )
    private Double valorTotal;
    @NotNull(message = "A quantidade de parcelas deve ser informado")
    @Min(value = 0, message = "A quantidade de parcelas não pode ser negativo")
    @Column(name="parcelas", nullable = false)
    private Integer parcelas;
    @NotNull(message = "A pessoa fisica não pode ser nula")
    @ManyToOne
    @JoinColumn(name="pessoa_fisica", referencedColumnName = "id", nullable = false)
    private PessoaFisica pessoaFisica;
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL,orphanRemoval = false, fetch = FetchType.LAZY) 
    private List<VendaItens> itens = new ArrayList<>(); //por ser uma agregação composta, aquele diamante pintado de preto, VENDAITENS não existe se não existe VENDA, por isso a lista
    @OneToMany(mappedBy = "parcelaID.venda",  cascade = CascadeType.ALL, orphanRemoval = false, 
            fetch = FetchType.LAZY) //qual atributo na PARCELA que faz referencia da VENDA para o mappedBy? Acessa então PARCELA, viu que é composta e é a classe PARCELAID, então acessa lá e vê que em PARCELA o que identifica a VENDA é o atributo venda
    private List<Parcela> listaParcelas = new ArrayList<>();
    
    
    public Venda() { //aqui dentro é como um contador, a medida que vou adicionando e removendo itens , quero atualizr o valorTotal da venda
        this.valorTotal =0.0;
    }
    
    //método para gerar as parcelas da venda
    public void gerarParcelas(){
        Double valorParcela = this.valorTotal / this.parcelas;
        for(int i = 1; i <= this.parcelas; i++){
            Parcela p = new Parcela();
            ParcelaID id = new ParcelaID();
            id.setNumero(i);
            id.setVenda(this); //this aqui é a prórpia venda
            p.setParcelaID(id);
            p.setValor(valorParcela);
            Calendar vencimento = (Calendar) this.data.clone(); // pego a data horiginal, clonando sem alterar ela
            vencimento.add(Calendar.MONTH, i);
            p.setVencimento(vencimento);
            this.listaParcelas.add(p);
        }
    }

    //métodos para adicionar e remover vendasITens dessa lista. Esses métodos são operações de adicionar e remover itens lá na página sendo que ainda n~~ao tenho um ID
    
    public void adicionarItem(VendaItens vendaItens){
        vendaItens.setVenda(this);
        this.valorTotal += vendaItens.getValorTotal();
        this.itens.add(vendaItens);
    }
    
    public void removerItem(int index){
        VendaItens vendaItens = this.itens.get(index);
        this.valorTotal -= vendaItens.getValorTotal();
        this.itens.remove(index);
    }
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the data
     */
    public Calendar getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Calendar data) {
        this.data = data;
    }

    /**
     * @return the valorTotal
     */
    public Double getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the parcelas
     */
    public Integer getParcelas() {
        return parcelas;
    }

    /**
     * @param parcelas the parcelas to set
     */
    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    /**
     * @return the pessoaFisica
     */
    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    /**
     * @param pessoaFisica the pessoaFisica to set
     */
    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public List<Parcela> getListaParcelas() {
        return listaParcelas;
    }

    public void setListaParcelas(List<Parcela> listaParcelas) {
        this.listaParcelas = listaParcelas;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<VendaItens> getItens() {
        return itens;
    }

    public void setItens(List<VendaItens> itens) {
        this.itens = itens;
    }
    
    
    
    
}
