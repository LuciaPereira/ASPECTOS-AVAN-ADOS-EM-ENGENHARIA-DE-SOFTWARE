/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.abstratos.Produto;
import model.abstratos.Endereco;
import model.interfaces.PedidoEstado;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Observable;
import model.abstratos.Usuario;
import model.estados.PedidoEmPreparo;
import model.interfaces.Promocao;
import persistence.PedidoDAO;

/**
 *
 * @author andradeld
 */
public class Pedido extends Observable {
    //campos só para o banco de dados?
    private Usuario entregador,
            cliente,
            empresa;
    
    private Long id;
    private List<PedidoProduto> produtos =new ArrayList<>();
    private PedidoEstado estado;
    private Endereco endereco;
    private double frete=0;

    public Pedido(Long id, List<PedidoProduto> produtos,Endereco endereco,
            double frete) {
        this.id = id;
        estado = new PedidoEmPreparo();
        this.produtos = produtos;
        this.endereco = endereco;
        this.frete = frete;
    }
    public Pedido(Long id, Endereco endereco, double frete) {
        this.id = id;
        estado = new PedidoEmPreparo();
        this.produtos = new ArrayList<>();
        this.endereco = endereco;
        this.frete = frete;
    }
    public Pedido(Long id, List<PedidoProduto> produtos,Endereco endereco,
            double frete, PedidoEstado pedidoEstado) {
        this.id = id;
        estado = pedidoEstado;
        this.produtos = produtos;
        this.endereco = endereco;
        this.frete = frete;
    }
    public Pedido(Long id, Endereco endereco, double frete,
            PedidoEstado pedidoEstado) {
        this.id = id;
        estado = pedidoEstado;
        this.produtos = new ArrayList<>();
        this.endereco = endereco;
        this.frete = frete;
    }

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public List<PedidoProduto> getProdutos() {
        return produtos;
    }

    public PedidoEstado getEstado() {
        return estado;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public double getFrete() {
        return frete;
    }

    public double getPrecoProdutos() {
        double precoProdutos = 0;
        Iterator<PedidoProduto> IteratorProdutos = produtos.iterator();
        while(IteratorProdutos.hasNext())
        {   
            PedidoProduto produtoAtual = IteratorProdutos.next();
            int desconto = produtoAtual.getPromocao() != null ? produtoAtual.getPromocao().obterDesconto() : 0;
            precoProdutos = precoProdutos + (produtoAtual.getProduto().getPreco() - ((produtoAtual.getProduto().getPreco()*desconto)/100)  * produtoAtual.getQuantidade());
        }
        return precoProdutos;
    }

    public Usuario getEntregador() {
        return entregador;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public Usuario getEmpresa() {
        return empresa;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public void setEntregador(Usuario entregador) {
        this.entregador = entregador;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public void setEmpresa(Usuario empresa) {
        this.empresa = empresa;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public void setEstado(PedidoEstado estado) {
        this.estado = estado;
        setChanged(); //observer
        notifyObservers(); //observer
    }

    public void setProdutos(List<PedidoProduto> produtos) {
        this.produtos = produtos;
    }
    
    public void addProduto(PedidoProduto novoProduto)
    {
        produtos.add(novoProduto);
    }
    public void removeProduto(PedidoProduto produto)
    {
        produtos.remove(produto);
    }

    public void addListaProdutos(List<Produto> listProdutos) {
        for(Iterator i = listProdutos.iterator();i.hasNext();){
            Produto p = (Produto)i.next();
            PedidoProduto pedidoProduto = getPedidoProdutoByProdutoId(p.getId());
            if (pedidoProduto != null)
                pedidoProduto.incrementaQuantidade();
            else{
                pedidoProduto = new PedidoProduto();
                pedidoProduto.setQuantidade(1);
                pedidoProduto.setProduto(p);
                addProduto(pedidoProduto);
            }
        }
            
    }

    public void addListaProdutosPromocao(List<Produto> listProdutos, Promocao promocao) {
        for(Iterator i = listProdutos.iterator();i.hasNext();){
            Produto p = (Produto)i.next();
            PedidoProduto pedidoProduto = getPedidoProdutoByProdutoId(p.getId());
            if (pedidoProduto != null)
                pedidoProduto.incrementaQuantidade();
            else{
                pedidoProduto = new PedidoProduto();
                pedidoProduto.setQuantidade(1);
                pedidoProduto.setProduto(p);
                pedidoProduto.setPromocao(promocao);
                addProduto(pedidoProduto);
            }
        }
            
    }
    
    private PedidoProduto getPedidoProdutoByProdutoId(String id){
        return getPedidoProdutoByProdutoId(Long.parseLong(id));
    }
    
    private PedidoProduto getPedidoProdutoByProdutoId(Long id){
        for(Iterator i = produtos.iterator();i.hasNext();){
            PedidoProduto pedidoproduto = (PedidoProduto)i.next();
            if (Objects.equals(pedidoproduto.getProduto().getId(), id))
                return pedidoproduto;
        }
        return null;
    }

    public int getCountProdutos() {
        return produtos.size();
    }

    public void removeListaProdutos(List<String> idsList) {
        for(Iterator i = idsList.iterator();i.hasNext();){
            String idProduto = (String)i.next();
            PedidoProduto pedidoProduto = getPedidoProdutoByProdutoId(idProduto);
            if (pedidoProduto != null)
                removeProduto(pedidoProduto);
        }
    }

    public void atualizaQuantidades(List<String> qtdItensList) {
        for(ListIterator  i = qtdItensList.listIterator();i.hasNext();){
            int index = i.nextIndex();
            String qtdProduto = (String)i.next();
            produtos.get(index).setQuantidade(Integer.parseInt(qtdProduto));
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEndereco() {
        this.endereco = cliente.getEnderecoPrincipal();
    }
    
    
}
