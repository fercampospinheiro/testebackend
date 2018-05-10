<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/1.0.0/handlebars.js"></script>

    <script>
        
    var hideMessages = function () {
        $(".error-register").hide();
        $(".success-register").hide();
        $(".info-register").hide();
    };
    
    //Funcao que verifica qual a acao foi chamada e que fazer no retorno 
    var actionExecute = function(action,calbackExecute){    
        switch (action) {
            case PLAYER_REGISTER:
                calbackExecute();
              [break;]
            case PLAYER_LIST:
                calbackExecute();
              [break;]
            case PLAYER_REMOVE:
                calbackExecute();
              [break;]
          case PLAYER_REMOVE:
                calbackExecute();    
              [break;]
          }
      }
     
    //Obtem o array de dados do formulario
    function getFormData(data) {
            var unindexed_array = data;
            var indexed_array = {};

            $.map(unindexed_array, function (n, i) {
                indexed_array[n['name']] = n['value'];
            });

            return indexed_array;
    }
    
    //Funcao que pega o tenplate do Handlebar e injeta informacoes nele
    var populateHandleBarTemplate = function (template, injectTag, data) {
        var gridPlayerTemplate = 
        var source = $(template).html();
        var template = Handlebars.compile(source);
        var html = template(data);
        $(tag).append(html);
    };

    var createMessage = function(message $typeMessage){
        var $info =  $typeMessage;
        var $strong = $info.find("strong");
        $strong.empty();
        $strong.append(message);
        $info.show(); 
    } 
    
    $(document).ready(function () {
            hideMessages();
            
            //Objetos que represem enum e que fazem conforme o tipo de codigo
            var PLAYER_REGISTER = {
                var CODE_200 = function (){ createMessage("Ops! :( Jogador criado com sucesso",$(".success-register")); }
                var CODE_201 = function (){ createMessage("Ops! :( Jogador criado com sucesso",$(".success-register")); }
                var CODE_204 = function (){ console.log("Codigo 204 - Não trata este codigono registro de jogado");}
                var CODE_208 = function (){ createMessage("Ops! :? Já existe um jogador com este email",$(".info-register"));}
                var ERROR_MSG =  function(){ createMessage("Ops! :( Houve um erro não foi possivel te cadastrar.",$(".error-register"));}
            }
            
            var PLAYER_LIST = {
                var CODE_200 = function (){ console.log("Codigo 200 - Lista de jogadores recuperada com sucesso"); }
                var CODE_201 = function (){ console.log("Codigo 201 - Lista de jogadores recuperada com sucesso"); }
                var CODE_204 = function (){ createMessage("Ops! :/ Não possui nenhum jogador cadastrado",$(".info-register");}
                var CODE_208 = function (){ console.log("Codigo 208 - Nao trata este codigo na lista de jogadores ");}
                var ERROR_MSG =  function(){ createMessage("Ops! :( Houve um erro ao carrega a lista de jogadores",$(".error-register"));}
            }
            
            var PLAYER_REMOVE = {
                var CODE_200 = function (){ $(".success-register").show(); }
                var CODE_201 = function (){ $(".success-register").show(); }
                var CODE_204 = function (){ $(".success-register").show(); }
                var CODE_208 = function (){ $(".success-register").show(); 
                var ERROR_MSG =  function(){ createMessage("Ops! :( Houve um erro ao remover o jogador",$(".error-register"));}
            }
            
            var PLAYER_EDIT = {
                var CODE_200 = function (){ $(".success-register").show(); }
                var CODE_201 = function (){ $(".success-register").show(); }
                var CODE_204 = function (){ $(".success-register").show(); }
                var CODE_208 = function (){ $(".success-register").show(); }
                var ERROR_MSG =  function(){ createMessage("Ops! :( Houve um erro ao editar o jogador",$(".error-register"));}
            }
            
            var urlPlayerList = "/api/v1/players";
            callResourceUrl(urlPlayerList, "", "POST");
            
            //Envia informacoes pra o registro do jogador
            $("#form-player").submit(function (e) {
                e.preventDefault();
                var form = $(this);
                var data = form.serializeArray();
                var url = "/api/v1/player";
                callResourceUrl(url, getFormData(data), "POST");
            });
            
            
        });

        var callResourceUrl = function (url, json, httpMethod , action) {
            var jsonData = "{}";
            
            if(action !== PLAYER_LIST){ jsonData = '{"Player":' + JSON.stringify(json) + '}'};
   
            $.ajax(url, {
                type: httpMethod,
                dataType: 'json',
                data: jsonData,
                contentType: 'application/json; charset=utf-8',
                error: action.ERROR_MSG,
                statusCode: {
                    200: function (data){
                        actionExcute(action, action.CODE_200());
                    },
                    201: function () {
                        actionExcute(action, action.CODE_201());
                    },
                    204: function (){
                        actionExcute(action, action.CODE_204());
                    },
                    208: function () {
                        actionExcute(action, action.CODE_208());
                    }                    
                }
            });
        }

    </script>

    <div class="container">
        <div class="row">

            <section class="content">
                <h1>Cadastro do Jogador UOL</h1>
                <div class="col-md-8 col-md-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="table-container">
                                <table class="table table-filter">
                                    <form action="#" id="form-player">

                                        <input type="hidden" name="idPlayer"/>

                                        <div class="form-group">
                                            <label for="name">Nome:</label>
                                            <input type="text" name="name" class="form-control" id="name"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="mail">Email:</label>
                                            <input type="email" name="email" class="form-control" id="mail">
                                        </div>
                                        <div class="form-group">
                                            <label for="phone">Telefone:</label>
                                            <input type="text"name="phone" class="form-control" id="phone"/>
                                        </div>
                                        <div class="checkbox">
                                            <label><input type="radio" name="playerGroup" value="AVANGERS" checked/>Liga da Justiça</label>

                                        </div>
                                        <div class="checkbox">
                                            <label><input type="radio" name="playerGroup" value="JUSTICE_LEAGUE"/>Os Vingadores</label>
                                        </div>
                                        <div class="form-group">
                                            <label><button type="submit" id ="cadastrar" class="btn btn-default "/>Cadastrar</button></label>
                                        </div>
                                        <div class="form-group">
                                            <label><button type="button" onclick="location.href = '/players/form'" class="btn btn-default">Listar Jogadores</button></label>
                                        </div>
                                    </form>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="content-footer">
                        <div class="alert alert-danger error-register">
                            <strong></strong>.
                        </div>
                        <div class="alert alert-success success-register">
                            <strong>Ehh! :) Você se registrou com sucesso</strong>
                        </div>
                        <div class="alert alert-info info-register">
                            <strong></strong>
                        </div>
                    </div>
                </div>
            </section>

        </div>
    </div></body>
</html>