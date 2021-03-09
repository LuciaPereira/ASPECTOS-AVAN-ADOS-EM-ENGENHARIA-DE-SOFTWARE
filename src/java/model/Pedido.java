/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.PedidoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author lucia
 */
public class Pedido  extends Observable{

   
    private Integer numero;
    private Cliente cliente;
    private Date dataPedido;
    private Float valorTotal;
    private ArrayList<ItemPedido> itensPedido;
    private PedidoEstado pedidoEstado;
    
    
    public Pedido(){
        this.itensPedido = new ArrayList<ItemPedido>();
        this.pedidoEstado = new PedidoEstadoRecebido();
    }
    public Pedido(Integer numero, Cliente cliente, Date dataPedido, Float valorTotal, ArrayList<ItemPedido> itensPedido,PedidoEstado pedidoEstado){
       this.numero = numero;
       this.cliente = cliente;
       this.dataPedido = dataPedido;
       this.valorTotal = valorTotal;
       this.itensPedido = itensPedido;
       this.pedidoEstado =  pedidoEstado;
       this.addObserver(cliente);
    }

    public Integer getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public ArrayList<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setItensPedido(ArrayList<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }
    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
    public void setDataPedido(Date dataPedido){
        this.dataPedido = dataPedido;
    }
    
    public void setValorTotal(Float valorTotal){
        this.valorTotal = valorTotal;
    }
    
    public void adicionarItem(ItemPedido item){
        this.itensPedido.add(item);
    }  
    public int gravar()throws ClassNotFoundException, SQLException{
        return PedidoDAO.getInstancia().gravar(this);
    }
    public void alterarEstado() throws SQLException, ClassNotFoundException{
        PedidoDAO.getInstancia().alterarEstado(this);
        setChanged();
        notifyObservers();
    }
    public static Pedido obterPedido(int numero)throws SQLException, ClassNotFoundException{
       return PedidoDAO.getInstancia().obterPedido(numero);
    }
    public static List<Pedido> obterPedidos()throws SQLException, ClassNotFoundException {
        return PedidoDAO.getInstancia().obterPedidos();
    }
    public boolean receber(){
        return this.pedidoEstado.receber(this);
    }
    public boolean preparar(){
        return this.pedidoEstado.preparar(this);
    }
    public boolean enviar(){
        return this.pedidoEstado.enviar(this);
    }
    public boolean entregar(){
        return this.pedidoEstado.entregar(this);
    }
    public boolean cancelar(){
        return this.pedidoEstado.cancelar(this);
    }
    public void setPedidoEstado(PedidoEstado pedidoEstado){
        this.pedidoEstado = pedidoEstado;
    }

    public PedidoEstado getPedidoEstado() {
        return pedidoEstado;
    }
    
    public String getNomeEstado(){
        return this.pedidoEstado.getNome();
    }

    public int getCodigo() {
        int codigo = 0;
        return codigo;
    }
    

    

    
    
}
