<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            /*    --------------------------------------------------
                    :: General
                    -------------------------------------------------- */
            body {
                font-family: 'Open Sans', sans-serif;
                color: #353535;
            }
            body,
            .modal-open .page-container,
            .modal-open .page-container .navbar-fixed-top,
            .modal-open .modal-container {
                    overflow-y: scroll;
            }

            @media (max-width: 979px) {
                    .modal-open .page-container .navbar-fixed-top{
                            overflow-y: visible;
                    }
            }
            
            .content h1 {
                text-align: center;
            }
            .content .content-footer p {
                color: #6d6d6d;
                font-size: 12px;
                text-align: center;
            }
            .content .content-footer p a {
                color: inherit;
                font-weight: bold;
            }

            /*	--------------------------------------------------
                    :: Table Filter
                    -------------------------------------------------- */
            .panel {
                border: 1px solid #ddd;
                background-color: #fcfcfc;
            }
            .panel .btn-group {
                margin: 15px 0 30px;
            }
            .panel .btn-group .btn {
                transition: background-color .3s ease;
            }
            .table-filter {
                background-color: #fff;
                border-bottom: 1px solid #eee;
            }
            .table-filter tbody tr:hover {
                cursor: pointer;
                background-color: #eee;
            }
            .table-filter tbody tr td {
                padding: 10px;
                vertical-align: middle;
                border-top-color: #eee;
            }
            .table-filter tbody tr.selected td {
                background-color: #eee;
            }
            .table-filter tr td:first-child {
                width: 38px;
            }
            .table-filter tr td:nth-child(2) {
                width: 35px;
            }
   
            .table-filter .media-photo {
                width: 35px;
            }
            .table-filter .media-body {
                display: block;
            }
            .table-filter .media-meta {
                font-size: 11px;
                color: #999;
            }
            .table-filter .media .title {
                color: #2BBCDE;
                font-size: 14px;
                font-weight: bold;
                line-height: normal;
                margin: 0;
            }
            .table-filter .media .title span {
                font-size: .8em;
                margin-right: 20px;
            }
    
            .table-filter .media .title span.codename {
                font-size: 19px;
                color: #f0ad4e;
            }
            
             span.playerGoup {
                font-size: 19px;
                color: brown;
            }
            .span.remove {
                color: #6d6d6d;
                font-size: 19px;
                font-weight: 800;
            }
            .table-filter .media .summary {
                font-size: 14px;
            }

            .modal-body .form-horizontal .col-sm-2,
            .modal-body .form-horizontal .col-sm-10 {
                width: 100%
            }

            .modal-body .form-horizontal .control-label {
                text-align: left;
            }
            .modal-body .form-horizontal .col-sm-offset-2 {
                margin-left: 15px;
            }
            
            body,
            .modal-open .page-container,
            .modal-open .page-container .navbar-fixed-top,
            .modal-open .modal-container {
                    overflow-y: scroll;
            }

            @media (max-width: 979px) {
                    .modal-open .page-container .navbar-fixed-top{
                            overflow-y: visible;
                    }
            }
            

    </style><title>Jogadores UOL</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/1.0.0/handlebars.js"></script>
  
    <script>
        
  
    
    //Funcao que verifica qual a acao foi chamada e que fazer no retorno 
    var actionExecute = function(action,code,data){    
        switch (action.ACTION) {
            case MSG_REGISTER.ACTION:
                createMessage(code.MSG, code.TYPE , action.LOCAL_MSG);
                loadGrid(data, code.isPopulateGrid);
              break;
            case MSG_LIST.ACTION:
                createMessage(code.MSG, code.TYPE,action.LOCAL_MSG);
                loadGrid(data, code.isPopulateGrid);
              break;
            case MSG_REMOVE.ACTION:
                createMessage(code.MSG, code.TYPE,action.LOCAL_MSG);
                callListPlayers();
              break;
          case MSG_EDIT.ACTION:
                createMessage(code.MSG, code.TYPE,action.LOCAL_MSG);
                loadGrid(data, code.isPopulateGrid);
              break;
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
        var source = $(template).html();
        var template = Handlebars.compile(source);
        var html = data? template(data) : "";
        resetGrid();
        $(injectTag).append(html);
    };
    
    var resetGrid = function(){
        $("tbody").empty();
    }

    var callListPlayers = function(){  
        var urlPlayerList = "/api/v1/players";
        resetGrid();
        callResourceUrl(urlPlayerList, "", "GET",MSG_LIST); 
    }

    var loadGrid =  function(data,isLoad){
        if(!data || !data.List) return;
        if(isLoad) populateHandleBarTemplate("#grid-player-template","tbody",data.List);
    }

    var createMessage = function(message,classMessage,localMsg){
        if(classMessage === "console") {
            console.log(message);
            return;
        }
        
        hideMessages();  
        
        if(localMsg === "FORM") classMessage = ".form" + classMessage;
        
        var $message =  $(classMessage);
        var $strong = $message.find("strong");
        $strong.empty();
        $strong.append(message);
        $message.show(); 
        console.log("message " + message);
        setTimeout(function () {
		$($message).hide(); 
	}, 2500);
    } ;
    
    $(document).ready(function () {
            var TYPE_MESSAGE = {
                SUCCESS : ".alert-success",
                INFO : ".alert-info" ,
                ERROR :".alert-danger" ,
                WARN : ".alert-warning",
                CONSOLE: "console"
            }
                 
            window.hideMessages = function () {
                $(TYPE_MESSAGE.SUCCESS).hide();
                $(TYPE_MESSAGE.INFO).hide();
                $(TYPE_MESSAGE.ERROR).hide();
                $(TYPE_MESSAGE.WARN).hide();
                $('.form '+TYPE_MESSAGE.SUCCESS).hide();
                $('.form '+TYPE_MESSAGE.INFO).hide();
                $('.form '+TYPE_MESSAGE.ERROR).hide();
                $('.form '+TYPE_MESSAGE.WARN).hide();
            };

            hideMessages();
            
            //Enum que tratam cada codigo http e que fazem conforme o tipo de codigo
            MSG_REGISTER = {
                CODE_200 : { MSG : "Ops! :( Jogador criado com sucesso", TYPE: TYPE_MESSAGE.SUCCESS, isPopulateGrid : true},
                CODE_201 : { MSG : "Ops! :( Jogador criado com sucesso", TYPE: TYPE_MESSAGE.SUCCESS,isPopulateGrid : true},
                CODE_204 : { MSG : "Codigo 204 - Não trata este codigono registro de jogado", TYPE: TYPE_MESSAGE.CONSOLE,isPopulateGrid : false},
                CODE_208 :{ MSG : "Ops! :? Já existe um jogador com este email", TYPE: TYPE_MESSAGE.INFO,isPopulateGrid : false}, 
                CODE_412 :{ MSG : "Todos os codinomes foram utilizados.Tente outra lista", TYPE: TYPE_MESSAGE.WARN , isPopulateGrid : false},
                ERROR_MSG: { MSG : "Ops! :( Houve um erro não foi possivel te cadastrar.", TYPE: TYPE_MESSAGE.ERROR,isPopulateGrid : false}, 
                ACTION: "REGISTER",
                LOCAL_MSG: "FORM"
            }
            
            MSG_LIST = {
                CODE_200 : { MSG : "Codigo 200 - Lista de jogadores recuperada com sucesso", TYPE: TYPE_MESSAGE.CONSOLE, isPopulateGrid : true},
                CODE_201 : { MSG : "Codigo 201 - Lista de jogadores recuperada com sucesso", TYPE: TYPE_MESSAGE.CONSOLE, isPopulateGrid : true},
                CODE_204 : { MSG : "Ops! :/ Não possui nenhum jogador cadastrado", TYPE: TYPE_MESSAGE.INFO, isPopulateGrid : false},
                CODE_208 :{ MSG : "Codigo 208 - Nao trata este codigo na lista de jogadores", TYPE: TYPE_MESSAGE.CONSOLE, isPopulateGrid : false}, 
                ERROR_MSG: { MSG : "Ops! :( Houve um erro ao carrega a lista de jogadores", TYPE: TYPE_MESSAGE.ERROR},  
                ACTION: "LIST",
                LOCAL_MSG: "GRID"
            }
            MSG_REMOVE = {
                CODE_200 : { MSG : "Jogador removido com sucesso", TYPE: TYPE_MESSAGE.SUCCESS, isPopulateGrid : true},
                CODE_201 : { MSG : "Codigo 201 - Mensagem de remocao", TYPE: TYPE_MESSAGE.SUCCESS, isPopulateGrid : true},
                CODE_204 : { MSG : "Codigo 204 - Mensagem de remocao", TYPE: TYPE_MESSAGE.CONSOLE,isPopulateGrid : false},
                CODE_208 :{ MSG : "Codigo 208 - Mensagem de remocao", TYPE: TYPE_MESSAGE.CONSOLE, isPopulateGrid : false}, 
                ERROR_MSG: { MSG : "Ops! :( Houve um erro ao remover o jogador", TYPE: TYPE_MESSAGE.ERROR}, 
                ACTION: "REMOVE",
                LOCAL_MSG: "GRID"
            }           
            MSG_EDIT = {
                CODE_200 : { MSG : "Ops! :D Jogador alterado com sucesso", TYPE: TYPE_MESSAGE.SUCCESS , isPopulateGrid : true},
                CODE_201 : { MSG : "Ops! :D Jogador alterado com sucesso", TYPE: TYPE_MESSAGE.SUCCESS , isPopulateGrid : true},
                CODE_204 : { MSG : "Codigo 204 - Não trata este codigo na alateracao de jogador", TYPE: TYPE_MESSAGE.CONSOLE , isPopulateGrid : false},
                CODE_208 :{ MSG : "Ops! =/ Já existe um jogador com este email", TYPE: TYPE_MESSAGE.INFO , isPopulateGrid : false}, 
                CODE_412 :{ MSG : "Todos os codinomes foram utilizados.Tente outra lista", TYPE: TYPE_MESSAGE.WARN , isPopulateGrid : false}, 
                ERROR_MSG: { MSG : "Ops! :( Houve um erro não foi possivel alterar o jogador.", TYPE: TYPE_MESSAGE.ERROR}, 
                ACTION: "EDIT",
                LOCAL_MSG: "FORM"
            }           
            
            callListPlayers();  
            
            //Envia informacoes pra o registro do jogador
            $("#form-player").submit(function (e) {
                e.preventDefault();
                var form = $(this);
                var data = form.serializeArray();
                var url = "/api/v1/player";
                var method = $("#form-player").attr("action");
                
                var MSG = method === "POST"? MSG_REGISTER : MSG_EDIT;
                
                callResourceUrl(url, getFormData(data), method , MSG);
            });
            
            //Botao voltar --> carrega a lista novamente
            $("#back-grid").click(function (){
                callListPlayers();       
                $("#form-player")[0].reset();
               $("#salvar").empty().html("Cadastrar").prop("id","cadastrar");
               $("#form-player").attr("action","POST");
            })
            
   
            removePlayer = function (idPlayer){
                var urlRemovePlayer = "/api/v1/player";
                var params = {idPlayer : idPlayer};
                callResourceUrl(urlRemovePlayer, params, "DELETE",MSG_REMOVE);
            }  
            
            populateFormPlayer = function (idPLayer){
                hideMessages();
                $("#form-player")[0].reset()
                $('#loadModalPlayerForm').trigger('click');
                
                var params = {
                    idPlayer : $("#idPlayer" + idPLayer).val(),
                    name: $("#player_name"+idPLayer).val(),
                    codename: $("#player_codename"+idPLayer).val(),
                    phone: $("#player_phone"+idPLayer).val(),
                    email: $("#player_email"+idPLayer).val(),
                    playerGroup: $("#player_group_name"+idPLayer).val()
                
                 };
   
                $("#inputIdpPlayer").prop("value",params.idPlayer);
                $("#inputCodename").prop("value",params.codename);
                $("#inputName").prop("value",params.name);
                $("#inputPhone").prop("value",params.phone);
                $("#inputEmail").prop("value",params.email);
                
                if(params.playerGroup === "AVANGERS"){
                    $("#inputPlayerGroup2").prop('checked', true);
                }else{
                    $("#inputPlayerGroup1").prop('checked', true);
                }
                
                $("#myModalLabel").empty().html("Cadastro do " +"<Strong>"+ params.codename+"</Strong>");
                $("#cadastrar").empty().html("Salvar").prop("id","salvar");
                $("#form-player").attr("action","PUT");
  
            }
            
        });

        var callResourceUrl = function (url, json, httpMethod , action) {
            var jsonData = "";
            
            if(action.ACTION !== MSG_LIST.ACTION){ jsonData = '{"Player":' + JSON.stringify(json) + '}'};
   
            $.ajax(url, {
                type: httpMethod,
                dataType: 'json',
                data: jsonData,
                contentType: 'application/json; charset=utf-8',
                error: action.ERROR_MSG,
                statusCode: {
                    200: function (data){
                        actionExecute(action, action.CODE_200,data);
                    },
                    201: function (data) {
                        actionExecute(action, action.CODE_201,data);
                    },
                    204: function (data){
                        actionExecute(action, action.CODE_204,data);
                    },
                    208: function (data) {
                        actionExecute(action, action.CODE_208,data);
                    }, 
                    412 :function (data) {
                        actionExecute(action, action.CODE_412,data);
                    } 
                }
            });
        } 
        
        
    </script>
    </head>
    <body>
    
    <script id="grid-player-template" type="text/x-handlebars-template">
        {{#each this}}
        <tr>
            <td>
            <div class="media">
                <a href="#" class="pull-left">
                    <img src="https://s3.amazonaws.com/uifaces/faces/twitter/fffabs/128.jpg" class="media-photo">
                </a>
                <div class="media-body">   
                        <span class="pull-right ">
                        <input type="hidden" id="idPlayer{{idPlayer}}" name="idPlayer" value="{{idPlayer}}"/> 
                        <input type="hidden" id="player_name{{idPlayer}}" name="name" value="{{name}}"/>
                        <input type="hidden" id="player_phone{{idPlayer}}" name="phone" value="{{phone}}"/>
                        <input type="hidden" id="player_codename{{idPlayer}}" name="codename" value="{{codename}}"/>
                        <input type="hidden" id="player_email{{idPlayer}}" name="email" value="{{email}}"/>
                        <input type="hidden" id="player_group_name{{idPlayer}}" name="playerGroup" value="{{playerGroup}}"/>
                        
                            <a href="#" onclick= "removePlayer({{idPlayer}});return false;" id="removePlayer"> 
                                <span class="glyphicon glyphicon-trash"></span>
                            </a>
                        </span>
                    <a href="#" onclick="populateFormPlayer({{idPlayer}});return false;">
                        <h4 class="title">
                            {{name}}  <span class="glyphicon glyphicon-pencil"></span>
                            <span class="pull-right codename">{{codename}}</span>
                        </h4>
                    
                        <p class="summary">
                            {{email}} {{phone}}
                            <span class="pull-right playerGoup"> 
                            {{playerGroupName}}
                            </span>
                        </p>
                    </a>
                </div>
            </div>
        </td>
      </tr>
      {{/each}}
    </script>    
        
    <div class="container">
        <div class="row">

            <section class="content">
                <h1>Jogadores Cadastrados</h1>
                <div class="col-md-8 col-md-offset-2">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="table-container">
                                <table class="table table-filter">
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="content-footer">
                        <label><button type="button" data-toggle="modal" data-target="#myModalHorizontal" id="loadModalPlayerForm" class="btn btn-default">Cadastrar jogador</button></label>
                        
                        <div class="alert alert-danger">
                            <strong></strong>.
                        </div>
                        <div class="alert alert-success">
                            <strong></strong>
                        </div>
                        <div class="alert alert-info">
                            <strong></strong>
                        </div>
                        <div class="alert alert-warning">
                            <strong></strong> 
                        </div>
                    </div>
                </div>
            </section>

        </div>

    <!-- Modal do cadstro de jogador -->
    <div class="modal fade" id="myModalHorizontal" tabindex="-1" role="dialog" 
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <button type="button" class="close" 
                       data-dismiss="modal">
                           <span aria-hidden="true">&times;</span>
                           <span class="sr-only">Fechar</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        Cadastro de Jogador
                    </h4>
                </div>
                
                <!-- Modal Body -->
                <div class="modal-body">
                    
                    <form class="form-horizontal" role="form" action="POST" id="form-player">
                    <input type="hidden" id ="inputIdpPlayer"  name="idPlayer" value=""/>  
                    <input type="hidden" id ="inputCodename"  name="codename" value=""/> 
                    
                    <div class="form-group">
                        <label  class="col-sm-2 control-label"
                                  for="inputName">Nome</label>
                        <div class="col-sm-10">
                            <input type="text" name="name" class="form-control" 
                            id="inputName" placeholder="Nome"/>
                        </div>
                      </div>
                      <div class="form-group">
                        <label  class="col-sm-2 control-label"
                                  for="inputEmail">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" 
                            id="inputEmail" name="email" placeholder="Email"/>
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="col-sm-2 control-label"
                              for="inputPhone" >Telefone</label>
                        <div class="col-sm-10">
                            <input type="phone" name="phone" class="form-control"
                                id="inputPhone" placeholder="Telefone"/>
                        </div>
                      </div>
                     <div class="form-group">
                        <label class="col-sm-2 control-label"
                              for="inputPlayerGroup1" >Liga da Justiça</label>
                        <div class="col-sm-10">
                            <input type="radio" id="inputPlayerGroup1" name="playerGroup" value="JUSTICE_LEAGUE" checked="" />
                        </div>
                      </div>
                       <div class="form-group">
                        <label class="col-sm-2 control-label"
                              for="inputPlayerGroup2" >Os Vingadores</label>
                        <div class="col-sm-10">
                            <input id="inputPlayerGroup2" type="radio" name="playerGroup" value="AVANGERS"/>
                        </div>
                      </div>
                </div>
                <div class="alert form alert-danger">
                    <strong></strong>.
                </div>
                <div class="alert form alert-success">
                    <strong></strong>
                </div>
                <div class="alert form alert-info">
                    <strong></strong>
                </div>
                <div class="alert form alert-warning">
                    <strong></strong> 
                </div>

                <!-- Modal Footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="back-grid"
                            data-dismiss="modal">
                                Voltar
                    </button>
                    <button type="submit" class="btn btn-primary" id ="cadastrar">
                        Cadastra
                    </button>
                </div>
            </div>
        </div>
    </div>
 
</body>
</html>