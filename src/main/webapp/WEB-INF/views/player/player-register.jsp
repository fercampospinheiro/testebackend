<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="/js/player-register.css"/>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.amd.min.js"></script>

<!------ Include the above in your HEAD tag ---------->
<script>
    
    var messages = {};
    
    message.code204= function(message) {
        alert(message);
    }
    
    message.code200 = function(message) {
        alert(message);
    }
    
    message.code400 = function(message) {
        alert(message);
    }
    
    message.code404 = function(message) {
        alert(message);
    }

   var callUrl = function(url,json, httpMethod){   
        $.ajax(url, {
        type: httpMethod,
        data: json,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function(data){
            alert('200 status code!');
        },
        statusCode: {
             204: message.code204("200 - Sucesso"),   
             400: message.code400("404 - nao encontrado"),
             404: message.code404("400 - nao encosasaso")
          }
     });
   }
   
    $(document).ready(function () {

	$('.star').on('click', function () {
      $(this).toggleClass('star-checked');
    });

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

    callUrl("http://localhost:8080/players","","GET");

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
                                                            <form action="#">
                                                                <div class="form-group">
                                                                  <label for="name">Nome:</label>
                                                                  <input type="text" class="form-control" id="name">
                                                                </div>
                                                                <div class="form-group">
                                                                  <label for="email">Email:</label>
                                                                  <input type="email" class="form-control" id="email">
                                                                </div>
                                                                <div class="form-group">
                                                                  <label for="phone">Telefone;</label>
                                                                  <input type="text" class="form-control" id="phone">
                                                                </div>
                                                                <div class="checkbox">
                                                                    <label><input type="radio" name="playerGroup" value="AVANGERS" checked>Liga da Justiça</label>
                                                                    
                                                                </div>
                                                                <div class="checkbox">
                                                                    <label><input type="radio" name="playerGroup" value="JUSTICE_LEAGUE">Os Vingadores</label>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label><button type="submit" class="btn btn-default cadastrar">Cadastrar</button></label>
                                                                </div>
                                                                <div class="form-group"
                                                                     <label><button type="button" onclick="location.href='/players/form'" class="btn btn-default">Listar Jogadores</button></label>
                                                                </div>
                                                            </form>
							</table>
						</div>
					</div>
				</div>
				<div class="content-footer">
                                    
				</div>
			</div>
		</section>
		
	</div>
</div></body>
</html>