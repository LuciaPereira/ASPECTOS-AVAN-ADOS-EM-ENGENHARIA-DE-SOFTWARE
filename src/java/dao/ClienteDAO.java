/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

//import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author lucia
 */
public class ClienteDAO extends DAO{
    //singleton
    private static ClienteDAO instancia = new ClienteDAO();
    public static ClienteDAO getInstancia()
    {
        return instancia;
    }
    private ClienteDAO(){
        
    }
    /// ate aqui
    

public Cliente obterCliente(int codCliente) throws ClassNotFoundException, SQLException{
    Connection conexao = null;
    PreparedStatement comando = null;
    Cliente cliente = null;
    try{
        conexao = BD.getInstancia().getConexao();
        comando = conexao.prepareStatement("select * from cliente where codigo = " + codCliente);
        ResultSet rs = comando.executeQuery();
        rs.first();
        cliente = instanciarCliente(rs);
        
    }finally{
        conexao.close();
        comando.execute();
    }
        return cliente;
}
public List<Cliente> obterClientes() throws ClassNotFoundException, SQLException{
    Connection conexao = null;
    
    PreparedStatement comando = null;
    List<Cliente> clientes = new ArrayList<Cliente>();
    Cliente cliente = null;
    try{
        conexao = BD.getInstancia().getConexao();
        comando = conexao.prepareStatement("select * from cliente ");
         ResultSet rs = comando.executeQuery();
        
        while (rs.next()){
            cliente = instanciarCliente(rs);
            clientes.add(cliente);
        }
               
    }finally{
     
        conexao.close();
       
    }
        return clientes;
}

    private Cliente instanciarCliente(ResultSet rs)  throws SQLException{
        Cliente cliente = new Cliente (
        rs.getInt("codigo"),
        rs.getString("nome"),
        rs.getString("email"));
        return cliente;
    }
    
    public void gravar (Cliente cliente)throws ClassNotFoundException, SQLException{
    Connection conexao = null;
    PreparedStatement comando = null;
    try{
        conexao = BD.getInstancia().getConexao();
        comando = conexao.prepareStatement("insert into cliente(codigo, nome,email) values(?,?,?)");
        comando.setInt(1,cliente.getCodigo());
        comando.setString(2,cliente.getNome());
        comando.setString(3,cliente.getEmail());
        comando.executeUpdate();
    }finally{
        conexao.close();
      
    }
   }
    public void alterar(Cliente cliente)  throws ClassNotFoundException, SQLException{
    Connection conexao = null;
    PreparedStatement comando = null;
    
    try{
        conexao = BD.getInstancia().getConexao();
       comando = conexao.prepareStatement("update cliente set nome = ?, email = ? where codigo = ?");
       comando.setString(1, cliente.getNome());
       comando.setString(2, cliente.getEmail());
       comando.setInt(3, cliente.getCodigo());
       comando.executeUpdate();
       
    }finally{
       
        conexao.close();
    }
    }
    public void excluir(Cliente cliente)throws ClassNotFoundException, SQLException{
    Connection conexao = null;
    PreparedStatement comando = null;
    try{
        conexao = BD.getInstancia().getConexao();
        comando = conexao.prepareStatement("delete from cliente where codigo =" + cliente.getCodigo());
        comando.executeUpdate();
    
    }finally{
       conexao.close();
    }
    
    



    }



}