

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        
        
        <title>Gerenciar Pedidos</title>
        <script>
            $(document).ready(function(){
                alterarEstado = function(){
                    var tr = $(item).closest('tr');
                    var numero = tr.find('td[data-numero]').data('numero');
                    $.ajax({
                        url: 'GerenciarPedidosController?acao=mudarEstado',
                        method: 'POST',
                        data:{numero:numero, estado:estado},
                        sucess: function(resposta){
                            alert(resposta);
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
                    <h1 class="header">Gerenciar Pedidos</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="row">
                        <table class="table-striped table-bordered table-hover table-condensed" id="tabela" name="tabela">
                            <tr>
                                <td>Pedido</td>
                                <td>Cliente</td>
                                <td>Estado</td>
                                <td colspan="5">Açoes</td>
                            </tr>
                            
                            <c:forEach itens ="${pedidos}" var="umPedido">
                                <tr>
                                    <td data-numero="${umPedido.numero}">${umPedido.numero}</td>
                                    <td>${umPedido.cliente.nome}</td>
                                    <td>${umPedido.nomeEstado}</td>
                                    <td><button type="button" class="btn btn-info btn-xs" onClick="alterarEstado(this,'receber')"> Recebido <span class="glyphicon glyphicon-thumbs-up"></span></button></td>
                                    <td><button type="button" class="btn btn-info btn-xs" onClick="alterarEstado(this,'receber')"> Preparo <span class="glyphicon glyphicon-refresh"></span></button></td>
                                    <td><button type="button" class="btn btn-info btn-xs" onClick="alterarEstado(this,'receber')"> Enviado <span class="glyphicon glyphicon-send"></span></button></td>
                                    <td><button type="button" class="btn btn-info btn-xs" onClick="alterarEstado(this,'receber')"> Entregue <span class="glyphicon glyphicon-ok-circle"></span></button></td>
                                    <td><button type="button" class="btn btn-info btn-xs" onClick="alterarEstado(this,'receber')"> Cancelado <span class="glyphicon glyphicon-remove-circle"></span></button></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
