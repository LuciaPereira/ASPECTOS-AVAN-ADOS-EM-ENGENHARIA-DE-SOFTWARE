<%-- 
    Document   : manterProduto
    Created on : 28/02/2021, 14:01:33
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
        <title>Manter Produto</title>
    </head>
    <body>
       <div class="container">

            <div class="page-header">
                <h1>Manter Produtos - ${operacao}</h1>
            </div>
            <div class="row">
                <div class="col-sn-8">
                    <form action="ManterProdutoController?acao=confirmarOperacao&operacao="${operacao}
                          method="post" name="frmManterproduto">
                        
                        <div class="form-group">
                            <label form="txtCodProduto"> Codigo do produto</label>
                            <input type="number" class="form-control" id="txtCodProduto" name="txtCodProduto" value="${produto.codigo}" <c:if test="${operacao != 'Incluir'}">
                                   readenly </c:if> required>                           
                        </div>
                                   
                        <div class="form-group">
                            <label form="txtNomeProduto"> Nome do produto</label>
                            <input type="text" class="form-control" id="txtNomeProduto" name="txtNomeProduto" value="${produto.nome}" <c:if test="${operacao != 'Excluir'}">
                                   readenly </c:if> required>                           
                        </div>
                                   
                        <div class="form-group">
                            <label form="txtPrecoProduto"> Preco do Produto</label>
                            <input type="text" class="form-control" id="txtPrecoProduto" name="txtPrecoProduto" value="${produto.preco}" <c:if test="${operacao != 'Excluir'}">
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
