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
public interface PedidoEstado {
    public boolean receber(Pedido pedido);
    public boolean preparar(Pedido pedido);
    public boolean enviar(Pedido pedido);
    public boolean entregar(Pedido pedido);
    public boolean cancelar(Pedido pedido);
    public String getNome();
    
}
