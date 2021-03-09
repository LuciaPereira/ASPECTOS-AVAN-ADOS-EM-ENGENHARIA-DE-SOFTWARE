/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import dao.ProdutoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Email;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;

/**
 *
 * @author lucia
 */
public class Produto implements Observer {
    
   
    private Integer codigo;
    private String nome;
    private Float preco;

    public Produto(Integer codigo, String nome, Float preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
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

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }
    
    public static List<Produto> obterProdutos()throws ClassNotFoundException, SQLException {
        return ProdutoDAO.getInstancia().obterProdutos();
    }
    public static Produto obterProduto(int codProduto)throws ClassNotFoundException, SQLException {
        return ProdutoDAO.getInstancia().obterProduto(codProduto);
    }
    public void gravar() throws SQLException, ClassNotFoundException, IOException, ServletException{
        ProdutoDAO.getInstancia().gravar(this);
    }
    public void alterar() throws SQLException, ClassNotFoundException{
        ProdutoDAO.getInstancia().alterar(this);
    }
    public void excluir() throws SQLException, ClassNotFoundException{
        ProdutoDAO.getInstancia().excluir(this);
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
