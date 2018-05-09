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

    </style><title>Jogadores UOL</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/1.0.0/handlebars.js"></script>
  
    <script>
        
        var hideMessage = function () {
            $(".error-register").hide();
            $(".info-register").hide();
        };
    
    
    $(document).ready(function () {
        hideMessage();
        
        
        $('.ckbox label').on('click', function () {
            $(this).parents('tr').toggleClass('selected');
        });

        $('.btn-filter').on('click', function () {
            var $target = $(this).data('target');
            if ($target != 'all') {
                $('.table tr').css('display', 'none');
                $('.table tr[data-status="' + $target + '"]').fadeIn('slow');
            } else {
                $('.table tr').css('display', 'none').fadeIn('slow');
            }
        });
            
        var OnSuccess = function(data){
            populateData("#entry-template","tbody",data.List);
        }
       
       var OnSuccess = function (data) {
            if(data && data.status !== 200 ) return;
        
            $(".success-register").show();
            $("#form-player").trigger("reset");
        }


        var OnError = function (data) { 
            $("tbody").append("<tr><td class='alert alert-info'><strong>Não foi possivel carregar a lista de jogadores!</strong></td><tr>")
        }

        var populateData =  function(template,tag,data){
            var source   = $(template).html();
            var template = Handlebars.compile(source);
            var html = template(data);
            $(tag).append(html);
        }
            
        var callUrl = function (url, httpMethod) {
            $.ajax(url, {
                type: httpMethod,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                error: OnError,
                statusCode: {
                    204: function (){
                        var $info =  $(".info-register");
                        var $strong = $info.find("strong");
                        $strong.append("Ops! :/ Não possui nenhum jogador cadastrado");
                        $info.show();
                    },
                    200: function (data){
                        populateData("#entry-template","tbody",data.List);
                    }
                }
                });
        }
        
            callUrl("/api/v1/players","Get");
        
    });

       
        var OnError = function (data) {
            $(".error-register").show();
        };

        var populateData = function (template, tag, data) {
            var source = $(template).html();
            var template = Handlebars.compile(source);
            var html = template(data);
            $(tag).append(html);
        };

        var callUrl = function (url, json, httpMethod) {
            $.ajax(url, {
                type: httpMethod,
                dataType: 'json',
                data: '{"Player":' + JSON.stringify(json) + '}',
                contentType: 'application/json; charset=utf-8',
                error: OnError,
                statusCode: {
                    201: function () {
                        $(".success-register").show();
                    },
                    208: function () {
                        var $info =  $(".info-register");
                        var $strong = $info.find("strong");
                        $strong.empty();
                        $strong.append("Ops! :? Já existe um jogador com este email");
                        $info.show();   
                    }
                }
            });
        }



        var hideMessage = function () {
            $(".error-register").hide();
            $(".success-register").hide();
            $(".info-register").hide();
        };

        function getFormData(data) {
            var unindexed_array = data;
            var indexed_array = {};

            $.map(unindexed_array, function (n, i) {
                indexed_array[n['name']] = n['value'];
            });

            return indexed_array;
        }

        $(document).ready(function () {

            hideMessage();

            $("#form-player").submit(function (e) {
                e.preventDefault();
                var form = $(this);
                var action = form.attr("action");
                var data = form.serializeArray();
                var url = "/api/v1/player";
                callUrl(url, getFormData(data), "POST");
            });
        });        

        
    </script>
    </head>
    <body>
    
    <script id="entry-template" type="text/x-handlebars-template">
        {{#each this}}
        <tr>
            <td>
            <div class="media">
                <a href="#" class="pull-left">
                    <img src="https://s3.amazonaws.com/uifaces/faces/twitter/fffabs/128.jpg" class="media-photo">
                </a>
                <div class="media-body">
                    <span class="pull-right glyphicon glyphicon-trash"><a class="" href=""><a></span>
                    <h4 class="title">
                        {{name}}
                        <span class="pull-right codename">{{codename}}</span>
                    </h4>
                    <p class="summary">{{email}} {{phone}} <span class="pull-right playerGoup"> {{playerGroupName}}</span></p>
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
                        <label><button type="button" data-toggle="modal" data-target="#myModalHorizontal" class="btn btn-default">Cadastrar jogador</button></label>
                        
                        <div class="alert alert-danger error-register">
                            <strong>Ops! houve um erro :( Não conseguimos carregar os jogadores </strong>.
                        </div>
                        <div class="alert alert-info info-register">
                            <strong></strong>
                        </div>
                        
                    </div>
                </div>
            </section>

        </div>

        <!-- Modal -->
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
                    
                    <form class="form-horizontal" role="form" id="form-player">
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
                                  for="inputEmail3">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" 
                            id="inputEmail3" placeholder="Email"/>
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
                              for="inputPlayerGroup1" >Os Vingadores</label>
                        <div class="col-sm-10">
                            <input type="radio" id="inputPlayerGroup1" name="playerGroup" value="JUSTICE_LEAGUE" checked="" />
                        </div>
                      </div>
                       <div class="form-group">
                        <label class="col-sm-2 control-label"
                              for="inputPlayerGroup2" >Liga da Justiça</label>
                        <div class="col-sm-10">
                            <input id="inputPlayerGroup2" type="radio" name="playerGroup" value="AVANGERS"/>
                        </div>
                      </div>
                </div>
                
                <!-- Modal Footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
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