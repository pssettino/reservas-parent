<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Eventos Online</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="css/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Por favor inicie sesión</h3>
					</div>
					<div class="panel-body">
						<c:if test="${not empty errorMessage}">
							<div class="alert alert-danger">${errorMessage}</div>
						</c:if>
						<form role="form" name="loginForm" id="loginForm" action="./login"
							method="post">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="UserName"
										name="username" type="text" autofocus>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password"
										name="password" type="password" value="">
								</div>
								<!-- 								<div class="checkbox"> -->
								<!-- 									<label> <input name="remember" type="checkbox" -->
								<!-- 										value="Remember Me">Remember Me -->
								<!-- 									</label> -->
								<!-- 								</div> -->
								<!-- Change this to a button or input when using this as a form -->
								<div class="row">
									<div class="col-sm-4">
										<div class="form-group">
											<input type="submit" id="login" name="login"
												class="btn btn-primary" value="Iniciar sessión"
												onclick="this.submit();">
										</div>
									</div>
									<div class="col-sm-8">
										<div class="form-group">
											<a href="#" id="linkRegUsurio" name="linkRegUsurio"
												onclick="showModal();" >Registrar Usuario</a>
										</div>
									</div>
							</fieldset>
						</form>

						<div id="modaRegUsuario" class="modal fade" role="dialog">
							<div class="modal-dialog-center modal-lg">

								<!-- Modal content-->
								<div class="modal-content" style="left: 235px;">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Registrar Usuario</h4>
									</div>
									<div class="modal-body">
										<div class="clearfix"></div>
										<div class="panel panel-default">
											<div class="panel-body">
												<form method="POST" id="frmRegUsuario" action="./registrar">
													<div class="col-sm-6 col-xs-12">
														<div class="form-group">
															<label path="userName">Nombre de Usuario</label> <input
																id="userName" class="form-control"
																placeholder="Ingrese Nombre de Usuario" />
														</div>
														<div class="form-group">
															<label path="apellido">Apellido</label>
															<hidden path="idUsuario" name="idUsuario" id="idUsuario" />
															<input id="apellido" class="form-control" name="apellido"
																placeholder="Ingrese Apellido" />
														</div>
														<div class="form-group">
															<label path="nombre">Nombre</label> <input id="nombre" name="nombre"
																class="form-control" placeholder="Ingrese Nombre" />
														</div>
														<div class="form-group">
															<label path="nroDocumento">DNI</label> <input
																id="nroDocumento" class="form-control"
																placeholder="Ingrese DNI" />
														</div>

													</div>
													<div class="col-sm-6 col-xs-12">
														<div class="form-group">
															<label path="telefonoParticular">Telefono
																Particular</label> <input id="telefonoParticular"
																class="form-control" placeholder="Ingrese Telefono" />
														</div>
														<div class="form-group">
															<label path="telefonoLaboral">Telefono Laboral</label> <input
																id="telefonoLaboral" class="form-control"
																placeholder="Ingrese Telefono" />
														</div>
														<div class="form-group">
															<label path="email">Email</label> <input id="email"
																class="form-control" placeholder="Ingrese Email" />
														</div>
														<div class="modal-footer">

															<button type="button" id="btnRegUsuario"
																name="btnRegUsuario" class="btn btn-success" onclick="registrarUsuario();">Guardar</button>

															<button type="button" class="btn btn-default"
																data-dismiss="modal">Close</button>

														</div>
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>
	
	<script type="text/javascript">
		
	function showModal() {
			$("#modaRegUsuario").modal("show");
	}			
	
	function registrarUsuario() {
		var usuarioDTO = {
				idUsuario:$("#idUsuario").val(),		
				apellido : $("#apellido").val(),
				nombre : $("#nombre").val(),
				userName : $("#userName").val(),				
				nroDocumento : $("#nroDocumento").val(),				 
				estado : true,			
				telefonoParticular : $("#telefonoParticular").val(),
				telefonoLaboral : $("#telefonoLaboral").val(),
				email : $("#email").val()
		};
				
		
		
		$.ajax({
			url : "./registrar",
			type : "POST",
			data : JSON.stringify(usuarioDTO),
			contentType: 'application/json',		    
			dataType : "json",		
			success : function(data) {
				if(data.success){
					$("#modaRegUsuario").modal("hide");					
				}
			},
			error : function(data) {

			}
		});


	}	
 
	</script>
</body>

</html>
