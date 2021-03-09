<%-- 
    Document   : efetuarPedido
    Created on : 28/02/2021, 14:02:03
    Author     : lucia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SCP</title>
        <link href="=bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function(){
                adicionar = function(){
                    if($("quantidade").val() !=0){
                        var valotTotal = parseFloat($("#valorTotal").text());
                        var linha = $("<tr>");
                        var colunas = "";
                        var opcao = $("#produto").find("option:selected");
                        valorTotal = valorTotal + (opcao.data('preco') * $("#quantidade").val());
                        colunas += '<td style="visibility:hidden;" data-cod-produto=' + $("#produto").val() + '>' + $("#produto").val() + '</td>';
                        colunas += '<td>' + opcao.data('nome') + '</td>';
                        colunas += '<td data-quantidade=' + $("#quantidade").val() + '>' + $("#quantidade").val() + '</td>'>;
                        colunas += '<td data-preco=' + opcao.data('preco') + '>' + opcao.data('preco') + '</td>';
                        colunas += '<td><button type="button" class="btn-danger btn-xs" onClick="remover(this)"> Excluir <span class= "glyphicon-trash"></span></button></td>';
                        colunas += '</tr>';
                        linha.append(coluna);
                        $("#tabela").append(linha);
                        $("#quantidade").val(null);
                        $("#valorTotal").text(valorTotal);
                        
                    }
                };
                remover = function(item){
                    var valorTotal = parseFloat($("#valorTotal").text());
                    var tr = $(item).closest('tr');
                    var quantidade =tr.find('td[data-quantidade]').data('quantidade');
                    var preco = tr.find('td[data-preco]').data('preco');
                    valorTotal = valorTotal - (quantidade * preco);
                    $("#valorTotal").text(valorTotal);
                    tr.remove();                    
                }
                $("#cliente").change(function(){
                    $("#tabela tr").remove();
                    var linha = $("<tr>");
                    var colunas = "";
                    colunas +='<td style="visibility:hidden;">Codigo</td>';
                    colunas +='<td>Produto</td>';
                    colunas +='<td>Quantidade</td>';
                    colunas +='<td>Preço Unit</td>';
                    colunas +='<td>Acao</td>';
                    colunas +='</tr>';
                    linha.append(colunas);
                    $("#tabela").append(linha);
                    $("#valorTotal").text(0);
                });
                finalizar = function(){
                    var itensProdutos = "";
                    var itensQuantidades = "";
                    var itensPrecos = "";
                    $("#tabela").find('tr').next('tr').each(function(){
                        itensProdutos = itensProdutos + $(this).find('td[data-cod-produto]').data('cod-produto') + ";";
                        itensQuantidades = itensQuantidades + $(this).find('td[data-quantidade]').data("quantidade") + ";";
                        itensPrecos = itensPrecos + $(this).find('td[data-preco]').data('preco') + ";";
                    });
                    
                    $.ajax({
                        url: 'EfetuarPedidoController?acao=confirmar',
                        method: 'POST',
                        data: {cliente: $("#cliente").val(),
                            itensProdutos: itensProdutos,
                            itensQuantidades: itensQuantidades,
                            itensPrecos: itensPrecos,
                            valorTotal: parseFloat($("#valorTotal").text())},
                        success: function(resposta){
                            alert("Numero do Pedido = " resposta);
                            location.reload();
                        }
                    });
                    
                };
            });       
            
     </script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-12 page-header">
                    <h1 class="header">Efetuar Pedido</h1>
                </div> 
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="cliente">Cliente</label>
                        <select class="form-control" id="cliente" name="cliente">
                            <c:forEach items="${clientes}" var="umCliente">
                                <option value="${umCliente.codigo}">${umCliente.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="produto">Produto</label>
                        <select class="form-control" id="produto" name="produto">
                            <c:forEach items="${produtos}" var="umProduto">
                                <option value="${umProduto.codigo}"
                                        data-nome="${umProduto.nome}"
                                        data-preco="${umProduto.preco}">${umProduto.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="quantidade">Quantidade</label>
                        <input type="number" id="quantidade" name="quantidade" class="form-control"><br><br>
                    </div>
                    <button onclick="adicionar()" type="button" class="btn btn-primary"> Adicionar Item</button>
                    <br><br>
                    <center>
                        <button onclick="finalizar" type="button" class="btn btn-success">Finalizar Pedido</button>
                    </center>
                </div>
                <div class="col-sm-6">
                    <div class="row">
                        <h2 align="right">
                           Valor Total - R$
                           <label id="valorTotal" name="valorTotal">0</label>
                        </h2>
                    </div>
                    <div class="row">
                        <table class="table-striped table-bordered table-hover table-condensed" id="tabela" name="tabela">
                            <tr>
                                <td style="visibility: hidden">Codigo</td>
                                <td>Produto</td>
                                <td>Quantidade</td>
                                <td>Preço Unit</td>
                                <td>Açao</td>
                            </tr>   
                        </table>
                    </div>
                </div>
            </div>
            
        </div>
    </body>
</html>
