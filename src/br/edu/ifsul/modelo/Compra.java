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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Haylton
 */
@Entity
@Table(name="compra")
public class Compra implements Serializable{
    @EmbeddedId
    private CompraID compraID;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data", nullable = false)
    private Calendar data;
    @NotNull(message = "O valor total deve ser infromado")
    @Column(name="valor_total", nullable = false, columnDefinition = "numeric(10,2)")
    private Double valorTotal;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CompraItem> itensCompra = new ArrayList<>();
    
    public Compra() {
        valorTotal = 0.0;
    }
    
    public void adicionarItem(CompraItem compraItem){
        compraItem.setCompra(this);//eu digo que a compra desse compra item é a própria classe compra, pois é isso que ele espera como parametro, só da um ctrl+espaço
        valorTotal += compraItem.getValorTotal(); //digo que o valor total é incrementado com o valor total da compra do item
        this.itensCompra.add(compraItem); // agora eu adiciona a lista de ItensCompra do dipo CompraItem
    }
    
    public void removerItem(int index){
        CompraItem compraItem = (CompraItem) this.itensCompra.get(index); //recupero a compra a partir do index da lista
        valorTotal -= compraItem.getValorTotal();
        this.itensCompra.remove(index);
    }

        
    public CompraID getCompraID() {
        return compraID;
    }

    public void setCompraID(CompraID compraID) {
        this.compraID = compraID;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<CompraItem> getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(List<CompraItem> itensCompra) {
        this.itensCompra = itensCompra;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.compraID);
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
        final Compra other = (Compra) obj;
        if (!Objects.equals(this.compraID, other.compraID)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
