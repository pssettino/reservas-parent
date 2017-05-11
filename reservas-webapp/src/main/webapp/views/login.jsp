<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<body class="loginhome">
    <section>
    	<div class="container">
            <div class="row">
            	<div class="col-lg-12">
            		<!--<img src="img/img-logo-puestovendedor.png" alt="" class="img-responsive f-right" style="position:absolute;right:0; width:250px;">-->
                </div>            
            
                <form id="formulario" method="post">
                    <div class="col-lg-5 col-lg-offset-7 col-sm-6 col-sm-offset-6 box-loginhome" style="padding: 25px;">

                        <h3 class="col-xs-12 mbott20">
                        	<b>Bienvenido a </b><br />
                        	<img src="img/img-logo-puestovendedor.png" alt="" class="mtop20" style="width:250px;">
                        </h3>
                        <div class="clearfix"></div>
                        <div class="col-xs-5">
                        	<label>Tipo</label>
                            <select id="checkedTipoDoc" name="tipoDocumento" class="form-control" onBlur="validar()" onChange="validar()">
                            	<option value="-1" selected>Seleccionar</option>                               
                            </select>             
                        </div>                    
                        <div class="col-xs-7">
                        	<label>Nro. de documento</label>
                            <input type="number" id="inputNroDocumento" name="nroDocumento" class="form-control"
                            onkeypress="return isNumber(event)" 
                            placeholder="Número de documento" onKeyUp="validar()">
                        </div>
                        <div class="clearfix"></div>
                        <div class="col-xs-12">
                        	<label>Contraseña</label>
                            <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Contraseña" onKeyUp="validar()">
                            <p><button type="button" class="btn-notbtn" data-toggle="modal" data-target="#myModal">Olvidaste la contrase&ntilde;a?</button></p>                            
                            <button type="button" id="continue" class="btn btn-md btn-primary btn-block mtop10 f-right" onClick="login();" disabled><i id="spinner-login" class="fa fa-spinner fa-pulse btn-spinner hide"></i> Login</button>
                            <div class="clearfix"></div>
                            <!-- <div id="alerta" class="alert alert-danger mtop10" role="alert" style="display:none;">Verificar los datos ingresados.</div> -->
						</div>
                        <div class="clearfix"></div>
                    </div>                    
                </form>
        	</div>
        </div>
    </section>
    
    <!-- Modal -->
    <div class="modal bs-reseteo-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="top:15%;" >
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button id="bs-reseteo-modal-close" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Olvidé mi contraseña</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <form id="reseteoPassword">
                            <div class="col-md-12">
                                <input type="email" id="inputEmail" class="form-control" placeholder="E-mail" autofocus>
                                <button type="submit" id="inputSubmit" class="btn btn-sm btn-primary btn-block mtop10 width100"><i id="spinner-enviar" class="fa fa-spinner fa-pulse btn-spinner hide"></i> Recuperar contraseña</button>
                            </div>
                        </form>

                        <div class="col-md-12 text-center mtop20">
                            <div id="btn-success" class="alert alert-success hide-display" role="alert">La contrase&ntilde;a fue enviada a la cuenta de e-mail.
                            </div>    
                            <div id="btn-danger" class="alert alert-danger hide-display" role="alert">Ingresar una cuenta de e-mail v&aacute;lida.
                            </div>                        
                            <div id="btn-error" class="alert alert-danger hide-display" role="alert">Se ha producido un error, intentar nuevamente.
                            </div>                        
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>    
    
    <!-- Modal -->
    <div class="modal fade bs-example-modal-sm" id="mensajes" tabindex="-1" role="dialog" aria-labelledby="mensajesModalLabel" style="top:15%;" >
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="mensajesModalLabel">Login</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                            <div id="alerta" class="alert alert-danger mtop10 show-display" role="alert" style="display:none;">Verificar los datos ingresados.</div>
                     </div>
                </div>
                <div class="modal-footer">
                	<div align="center">
	                    <button type="button" class="btn btn-primary" data-dismiss="modal">continuar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>    
    
    <!-- FOOTER -->
	<!--
	<footer id="footer" class="footer-login">
        <div class="foot-2">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12" align="right">
                    	<img src="img/logo-renaultCredit-footer.png" class="img-responsive">
                    </div>
                </div>            
            </div>    
        </div>
    </footer>
    -->
    
<!-- Post -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/formateos.js"></script> 
<script type="text/javascript">

$("input#inputPassword").keypress(function(event) {
    if (event.which == 13) {
        event.preventDefault();
        login();
    }
});


	function login(){
		ocultarMensaje('alerta');
		ocultarMensaje('modificada');
	    var formData = {
			'tipoDocumento'				  : document.getElementById('checkedTipoDoc').value, 
			'nroDocumento'				  : document.getElementById('inputNroDocumento').value,
			'password'					  : document.getElementById('inputPassword').value,
			'_spring_security_remember_me': true
        };
		$('#spinner-login').removeClass('hide');
        $.ajax({
        	type        : 'POST',
            url         : 'login',
            data        : formData,
            dataType    : 'json',
            success     : function(data) {
				location.href = "login";
            },
            error       : function(data){
            	var texto = JSON.parse(data.responseText);
            	$('#alerta').text(texto);
				mostrarMensaje('alerta');
        		$('#spinner-login').addClass('hide');
            }
        });
	}
 
// Indica si esta la ventana abierta enviando info
var sending = false;
$("#myModal").on("hidden.bs.modal", function(e){
	$('#btn-success').removeClass('show-display').addClass('hide-display');
    // Blanqueamos el email
    $('#reseteoPassword input[type=email]').val('');
    // Desabilitamos el boton de enviar
    $('#reseteoPassword button[type=submit]').attr('disabled', 'disabled');
	ocultarMensaje('alerta');
	ocultarMensaje('modificada');
    sending = false;
});

$("#reseteoPassword").on("submit", function(e){
    var result = false;
    var secureSession = "123";
    if (!$('#reseteoPassword button[type=submit]').prop('disabled')) {
    $('#reseteoPassword button[type=submit]').attr('disabled', 'disabled');
    $('#reseteoPassword input[type=email]').each(function(e) {
       email_address = $(this);
       email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
       if(!email_regex.test(email_address.val())){ 
            $('#btn-danger').slideDown( 300 ).delay(3000).slideUp(300);
        } else {
             var formData = {
                     'email'                       : $('#inputEmail').val(),
                     '_spring_security_remember_me': true,
                     'secure_session': secureSession,
                 };
                sending = true;
                
                $('#btn-success').
	            	removeClass('show-display').
	            	addClass('hide-display').
	            	delay(3000, function() {
	            });
                $('#btn-error').
	            	removeClass('show-display').
	            	delay(3000).
	            	addClass('hide-display').
	            	delay(3000, function() {
	            });
                
        		$('#spinner-enviar').removeClass('hide');
                 $.ajax({
                     type: 'POST',
                     url: 'olvidoPassword',
                     data: formData,
                     success: function (data) {
                        result = JSON.parse(data);
                        
                        if (result) {
                            $('#btn-success').
                            	removeClass('hide-display').
                            	addClass('show-display').
                            	delay(3000, function() {
                            });
           					
                            $('#reseteoPassword button[type=submit]').removeAttr('disabled');
                        } else {
                            $('#btn-error').
                            	removeClass('hide-display').
                            	delay(3000).
                            	addClass('show-display').
                            	delay(3000, function() {
                            });
                        }
                		$('#spinner-enviar').addClass('hide');
                    },
                     error: function (data) {
                         $('#btn-error').slideDown(300).delay(3000).slideUp(300).delay(3000, function() {
                         	$('#myModal').modal('hide');
                        });
                        sending = false;
                		$('#spinner-enviar').addClass('hide');
                     }
                 })
        }
     }); 
    } else {
        alert('esta deshabilitado');
    }
     return false;
 
});


function mostrarMensaje(mensaje) {
	$('#mensajes').modal('show');
	$('#' + mensaje).slideDown(300);
}
function ocultarMensaje(mensaje) {
	$('#' + mensaje).slideUp(300);
	$('#mensajes').modal('hide');
}


	function limpiarEmail(){
		document.getElementById('exitoResetearPass').style.display = 'none';
        document.getElementById('falloResetearPass').style.display = 'none';
    }
		
	$(document).ready(function() {
//		$('#btn-success').slideUp(300);
		
		
// 		$('#spinner-enviar').change(function() {
// 			  alert( "Handler for .change() called." );
// 		});
		
		// Desabilitamos el boton de enviar
		$('#reseteoPassword button[type=submit]').attr('disabled', 'disabled');
		// Actualizamos el estado del boton de enviar segun se haya ingresado un email
		$('#inputEmail').on('keyup keypress blur change input', function() {
			if ($('#inputEmail').val().length>0) {
				$('#reseteoPassword button[type=submit]').removeAttr('disabled');
			} else {
				$('#reseteoPassword button[type=submit]').attr('disabled', 'disabled');
			}
		});
		
		$.ajax({
			 type: 'GET',
			 url: 'tipoDocumento',
			 dataType: 'json',
			 success: function (data) {
				for (i = 0; i< data.length ;  i++) {
					$('#checkedTipoDoc').append($('<option>', {
						value: data[i].id,
						text: data[i].descripcionCorta
					}));
				}
			 },
			 error: function (data) {
				 document.getElementById('inputEmail').value ='';
			 }
		 })
		
	});
	
	function validar(){
		var tipoDocumento = document.getElementById("checkedTipoDoc").value;
		var	nroDocumento = document.getElementById("inputNroDocumento").value;
		var password = document.getElementById("inputPassword").value;
		if ((tipoDocumento != "-1")  && (nroDocumento.length >= 6 && nroDocumento.length <= 10) && (password.length == 8)) {
			document.getElementById('continue').disabled = false;
		}else{
			document.getElementById('continue').disabled = true;
		}
	}
	
</script>

</body>
</html>
