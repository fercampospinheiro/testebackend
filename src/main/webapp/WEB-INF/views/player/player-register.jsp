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

        var OnSuccess = function (data) {
            if(data && data.status !== 200 ) return;
        
            $(".success-register").show();
            $("#form-player").trigger("reset");
        }
        
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
                                            <label for="email">Email:</label>
                                            <input type="email" name="email" class="form-control" id="email">
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
                            <strong>Ops! houve um erro :( Não podemos registrar você. </strong>.
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