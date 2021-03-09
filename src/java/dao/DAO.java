/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

//import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author lucia
 * 
 */
public class DAO {

    /**
     *
     * @param conexao
     * @param comando
     * @throws SQLException
     */
    public void fecharConexao(Connection conexao, PreparedStatement comando) throws SQLException{
        
        
        if(comando != null){
            boolean execute = comando.execute();
           
        }
        if(conexao != null){
            
            conexao.close();
        }
    }
    
}
