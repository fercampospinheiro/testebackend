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
                        <label><button type="button" onclick="location.href = '/player/form'" class="btn btn-default">Cadastrar jogador</button></label>
                        
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
    </div>
</body>
</html>