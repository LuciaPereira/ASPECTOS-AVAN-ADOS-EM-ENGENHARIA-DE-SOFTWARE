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
import model.Produto;

/**
 *
 * @author lucia
 */
public class ManterProdutoController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
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
                int codProduto = Integer.parseInt(request.getParameter("codProduto"));
                Produto produto = Produto.obterProduto(codProduto);
                request.setAttribute("produto", produto);
        }
            RequestDispatcher view = request.getRequestDispatcher("/manterProduto.jsp");
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
        int codigo = Integer.parseInt(request.getParameter("txtCodProduto"));
        String nome = request.getParameter("txtNomeProduto");
        //Float   preco = request.getParameter("txtPrecoProduto");
        try{
            Float preco = null;
        Produto produto = new Produto(codigo,nome,preco);
        if(operacao.equals("Incluir")){
            produto.gravar();
        }else{
            if(operacao.equals("Editar")){
                produto.alterar();
            }else{
                if(operacao.equals("Excluir")){
                    produto.excluir();
                }
            }
        }
        RequestDispatcher view = request.getRequestDispatcher("PesquisarProdutoController");
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ManterProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ManterProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
