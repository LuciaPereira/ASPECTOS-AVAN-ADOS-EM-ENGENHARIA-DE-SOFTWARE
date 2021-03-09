/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.EfetuarPedidoController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucia
 */
public class PedidoEstadoEnviado implements PedidoEstado {

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
        try{
            pedido.setPedidoEstado(new PedidoEstadoEntregue());
            pedido.alterarEstado();
        }catch(SQLException ex){
            Logger.getLogger(PedidoEstadoEnviado.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(ClassNotFoundException ex){
            Logger.getLogger(PedidoEstadoEnviado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean cancelar(Pedido pedido) {
        try{
            pedido.setPedidoEstado(new PedidoEstadoCancelado());
            pedido.alterarEstado();
            
        }catch(SQLException ex){
            Logger.getLogger(PedidoEstadoEnviado.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(ClassNotFoundException ex){
            Logger.getLogger(PedidoEstadoEnviado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public String getNome() {
        return "Enviado";
    }
    
}
