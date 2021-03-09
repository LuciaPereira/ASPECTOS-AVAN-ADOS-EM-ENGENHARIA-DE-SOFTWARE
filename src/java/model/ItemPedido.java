/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lucia
 */
public class ItemPedido {
    private Produto produto;
    private Float quantidade;
    private Float precoUnitario;
    
    public ItemPedido(Produto produto, Float quantidade,Float precounitario){
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }
    public Produto getProduto(){
        return produto;
    }
    public void setProduto(Produto produto){
        this.produto = produto;
    }
    public Float getQuantidade(){
        return quantidade;
    }

    public Float getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    
    
    
    
    
    
    
}
