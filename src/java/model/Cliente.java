/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ClienteDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Email;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;

/**
 *
 * @author lucia
 */
public class Cliente implements Observer{

    
    private Integer codigo;
    private String nome;
    private String email;

    public Cliente(Integer codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }
    
    public static List<Cliente> obterClientes()throws ClassNotFoundException, SQLException {
        return ClienteDAO.getInstancia().obterClientes();
    }
    public static Cliente obterCliente(int codCliente)throws ClassNotFoundException, SQLException {
        return ClienteDAO.getInstancia().obterCliente(codCliente);
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void gravar() throws SQLException, ClassNotFoundException, IOException, ServletException{
        ClienteDAO.getInstancia().gravar(this);
    }
    public void alterar() throws SQLException, ClassNotFoundException{
        ClienteDAO.getInstancia().alterar(this);
    }
    public void excluir() throws SQLException, ClassNotFoundException{
        ClienteDAO.getInstancia().excluir(this);
    }
    public void update(Observable pedidoObservado, Object arg1){
        if(pedidoObservado instanceof Pedido){
            Pedido pedido = (Pedido)pedidoObservado;
            try {
                Email.enviarEmail(
                        "Estado alterado de seu pedido!",
                        "Ola" + pedido.getCliente().getNome() +
                                "! Seu pedido " + pedido.getNumero()+ "mudou para o estado" + pedido.getNomeEstado() + ".Agradecemos sua preferencia!" ,
                        pedido.getCliente().getEmail());
            } catch (MessagingException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Ola" + pedido.getCliente().getNome() +
                            "! Seu pedido " + pedido.getNumero()+ "mudou para o estado" + pedido.getNomeEstado() + ".Agradecemos sua preferencia!");
        }
    }
    
    
}
