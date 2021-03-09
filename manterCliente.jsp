<%-- 
    Document   : manterCliente
    Created on : 16/02/2021, 22:55:52
    Author     : lucia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <div class="container">

            <div class="page-header">
                <h1>Manter de Clientes - ${operacao}</h1>
            </div>
            <div class="row">
                <div class="col-sn-8">
                    <form action="ManterClienteController?acao=confirmarOperacao&operacao="${operacao}
                          method="post" name="frmManterCliente">
                        
                        
                        
                        <div class="form-group">
                            <label form="txtCodCliente"> Codigo do cliente</label>
                            <input type="number" class="form-control" id="txtCodCliente" name="txtCodCliente" value="${cliente.codigo}" <c:if test="${operacao != 'Incluir'}">
                                   readenly </c:if> required>                           
                        </div>
                                   
                        <div class="form-group">
                            <label form="txtNomeCliente"> Nome do cliente</label>
                            <input type="text" class="form-control" id="txtNomeCliente" name="txtNomeCliente" value="${cliente.nome}" <c:if test="${operacao != 'Excluir'}">
                                   readenly </c:if> required>                           
                        </div>
                                   
                        <div class="form-group">
                            <label form="txtEmailCliente"> Email do cliente</label>
                            <input type="text" class="form-control" id="txtEmailCliente" name="txtEmailCliente" value="${cliente.email}" <c:if test="${operacao != 'Excluir'}">
                                   readenly </c:if> required>                           
                        </div>
                                   
                                   <button type="submit" class="btn-success">Confirmar</button>
                        
                    </form>
                    
                    
                </div>
                
            </div>
           
       </div>
                                   <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
