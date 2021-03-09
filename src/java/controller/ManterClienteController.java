/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;

/**
 *
 * @author lucia
 */
public class ManterClienteController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        String acao = request.getParameter("acao");
        if(acao.equals("confirmarOperacao")){
           confirmarOperacao(request, response);            
        }
        else{
            if(acao.equals("prepararOperacao")){
                prepararOperacao(request, response);
            }
        }
    }
    public void prepararOperacao(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, SQLException, ClassNotFoundException{
        try{
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao",operacao);
            if(!operacao.equals("Incluir")){
                int codCliente = Integer.parseInt(request.getParameter("codCliente"));
                Cliente cliente = Cliente.obterCliente(codCliente);
                request.setAttribute("cliente", cliente);
        }
            RequestDispatcher view = request.getRequestDispatcher("/manterCliente.jsp");
            view.forward(request, response);
    }
        catch(ServletException e){
            throw e;
        }
        catch(IOException e){
            throw new ServletException(e);
        }
        catch (SQLException ex){
             throw new ServletException(ex);
        }
        catch (ClassNotFoundException e){
             throw new ServletException(e);
        }
    }

    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        String operacao = request.getParameter("operacao");
        int codigo = Integer.parseInt(request.getParameter("txtCodCliente"));
        String nome = request.getParameter("txtNomeCliente");
        String email = request.getParameter("txtEmailCliente");
        try{
        Cliente cliente = new Cliente(codigo,nome,email);
        if(operacao.equals("Incluir")){
            cliente.gravar();
        }else{
            if(operacao.equals("Editar")){
                cliente.alterar();
            }else{
                if(operacao.equals("Excluir")){
                    cliente.excluir();
                }
            }
        }
        RequestDispatcher view = request.getRequestDispatcher("PesquisaClienteController");
        }
        catch (IOException e){
           throw new ServletException(e);
        }
        catch(SQLException e){
            throw new ServletException(e);
        }
        catch(ClassNotFoundException e)
        {
            throw new ServletException(e);
        }
        catch(ServletException e){
            throw e;
        }
    }
    
   

}
