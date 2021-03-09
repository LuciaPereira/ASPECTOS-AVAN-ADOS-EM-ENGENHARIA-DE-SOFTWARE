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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import model.PedidoEstado;




/**
 *
 * @author lucia
 */
public class PedidoDAO extends DAO{
    
    private static PedidoDAO instancia = new PedidoDAO();
    public static PedidoDAO getInstancia()
    {
        return instancia;
    }
    private PedidoDAO(){
        
    }

    
    public int gravar(Pedido pedido) throws ClassNotFoundException, SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        int id = 0;
        try{
            conexao = BD.getInstancia().getConexao();
            conexao.setAutoCommit(false);
            comando = conexao.prepareStatement(
            "insert into pedido(cod_cliente, data_venda, valor_total, status) values (?,?,?,?)");
            //Statement.RETURN_GENERATED_KEYS;
           
            comando.setInt(1,pedido.getCliente().getCodigo());
            comando.setDate(2, new java.sql.Date(pedido.getDataPedido().getTime()));
            comando.setFloat(3,pedido.getValorTotal());
            comando.setString(4, pedido.getPedidoEstado().getNome());
            comando.executeUpdate();
       
            ResultSet rs = comando.executeQuery();
            //ResultSet rs = comando.getGeneratedKeys();
            id = 0;
            if(rs.next()){
                id = rs.getInt(1);
        }
        int num_item = 0;
        for(ItemPedido item : pedido.getItensPedido()){
            comando = conexao.prepareStatement(
                    "insert into item_pedido(num_pedido, num_item, cod_produto, quantidade, valor_unitario) values (?,?,?,?,?)" );
                    num_item = num_item + 1;
                    comando.setInt(1, id);
                    comando.setInt(2, num_item);
                    comando.setInt(3, item.getProduto().getCodigo());
                    comando.setFloat(4, item.getQuantidade());
                    comando.setFloat(5, item.getPrecoUnitario());
                    comando.executeUpdate();
                    
        }
        conexao.commit();
        }catch (SQLException e){
            conexao.rollback();
        }finally{
            fecharConexao(conexao,comando);
        }
        return id;
    }

    public void alterarEstado(Pedido pedido)throws ClassNotFoundException, SQLException  {
        Connection conexao = null;
        PreparedStatement comando = null;
     
        String stringSQL;
        try{
            conexao = BD.getInstancia().getConexao();
            comando = conexao.prepareStatement( "update pedido set" 
                    + "status = '" + pedido.getPedidoEstado().getNome() + "'"
                    + "where numero = " + pedido.getNumero());
            comando.executeUpdate();
          
        }finally{
            fecharConexao(conexao, comando);
        }
    }

    public List<Pedido> obterPedidos()throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        
        List<Pedido> pedidos = new ArrayList<Pedido>();
        Pedido pedido = null;
        try{
        conexao = BD.getInstancia().getConexao();
        comando = conexao.prepareStatement("select * from pedido ");
         ResultSet rs = comando.executeQuery();
        
        while (rs.next()){
            pedido = instanciarPedido(rs);
            pedidos.add(pedido);
        }
     
        }finally{
            fecharConexao(conexao,comando);
        }
        return (List<Pedido>) pedido;
    }

    public Pedido obterPedido(int numero) throws ClassNotFoundException, SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        Pedido pedido = null;
        try{
            conexao = BD.getInstancia().getConexao();
        comando = conexao.prepareStatement("select * from pedido where numero =" + numero);
        ResultSet rs = comando.executeQuery();
            rs.first();
            pedido = instanciarPedido(rs);
        }finally{
            fecharConexao(conexao,comando);
        }
        return pedido;
        
    }

    private Pedido instanciarPedido(ResultSet rs)throws SQLException {
        Class classe = null;
        Object objeto = null;
        Pedido pedido = null;
        try{
            classe = Class.forName("model.PedidoEstado" + rs.getString("status"));
            objeto = classe.newInstance();
            
            pedido = new Pedido(
            rs.getInt("numero"),
            Cliente.obterCliente(rs.getInt("cod_cliente")),
            rs.getDate("data_venda"),
            rs.getFloat("valor_total"),null,
            (PedidoEstado) objeto);
        
            
        }catch(ClassNotFoundException ex){
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE,null,ex);
        }catch(Exception ex){
            
        }
        return pedido;
    }
        
    

   
}
