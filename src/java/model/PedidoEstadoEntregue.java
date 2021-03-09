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
public class PedidoEstadoEntregue implements PedidoEstado {

    @Override
    public boolean receber(Pedido pedido) {
       return false;
    }

    @Override
    public boolean preparar(Pedido pedido) {
        return false;
    }

    @Override
    public boolean enviar(Pedido pedido) {
        return false;
    }

    @Override
    public boolean entregar(Pedido pedido) {
        return false;
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        return false;
    }

    @Override
    public String getNome() {
        return "Entregue";
    }
    
}
