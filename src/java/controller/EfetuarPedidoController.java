/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import model.PedidoEstadoRecebido;
import model.Produto;

/**
 *
 * @author lucia
 */
public class EfetuarPedidoController extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("acao");
        if(acao.equals("preparar")){
            preparar(request,response);
        }else{
            if(acao.equals("confirmar")){
                confirmar(request,response);
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
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

    private void preparar(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException{
        
            try {
                request.setAttribute("clientes",Cliente.obterClientes());
                request.setAttribute("produtos",Produto.obterProdutos());
                RequestDispatcher view = request.getRequestDispatcher("/efetuarPedido.jsp");
                
                    view.forward(request, response);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EfetuarPedidoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(EfetuarPedidoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EfetuarPedidoController.class.getName()).log(Level.SEVERE, null, ex);
            }catch (ServletException e){
                throw e;
            }
           
        
    }
    

    private void confirmar(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException{
        try{
            Integer codCliente = Integer.parseInt(request.getParameter("cliente"));
            Cliente cliente = Cliente.obterCliente(codCliente);
            String itensProdutos = request.getParameter("itensProdutos");
            String[] produtos = itensProdutos.split(";");
            String itensQuantidades = request.getParameter("itensQuantidades");
            String[] quantidades = itensQuantidades.split(";");
            String itensPrecos = request.getParameter("itensPrecos");
            String[] precos = itensPrecos.split(";");
            Float valorTotal = Float.parseFloat(request.getParameter("valorTotal"));
            
            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setDataPedido(new Date());
            pedido.setValorTotal(valorTotal);
            pedido.setPedidoEstado(new PedidoEstadoRecebido());
            
            for(int i=0; i< produtos.length; i++)
            {
                Produto produto = Produto.obterProduto(Integer.parseInt(produtos[i]));
                ItemPedido item = new ItemPedido(produto, Float.parseFloat(quantidades[i]), Float.parseFloat(precos[i]));
                pedido.adicionarItem(item);
            }
        int num_pedido = pedido.gravar();
        response.getWriter().println(num_pedido);
        }
        catch (ClassNotFoundException ex){
            Logger.getLogger(EfetuarPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex){
            Logger.getLogger(EfetuarPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex){
            Logger.getLogger(EfetuarPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
