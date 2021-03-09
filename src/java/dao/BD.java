/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lucia
 */
public class BD {
    //singleton
    private static BD instancia = new BD();
    public static BD getInstancia(){
        return instancia;
    }
    private BD(){}
    
    public Connection getConexao() throws ClassNotFoundException, SQLException{
        Connection conexao = null;
        Class.forName("com.mysql.jdbc.Driver");
        conexao = DriverManager.getConnection("jdbc:mysql://localhost/SCP", "root","");
        return conexao;
    }
    
}
