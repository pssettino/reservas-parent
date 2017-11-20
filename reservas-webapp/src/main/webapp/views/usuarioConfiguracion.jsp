<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html lang="en">

<head>
<title>Reservas Online</title>

<jsp:include page="header.jsp"></jsp:include>

<link rel="stylesheet"
	href="components/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="components/bootstrap3/css/bootstrap-theme.min.css">

<!-- DataTables CSS -->
<link href="css/dataTables.bootstrap.css" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="css/dataTables.responsive.css" rel="stylesheet">

<style type="text/css">
.btn-twitter {
	padding-left: 30px;
	background: rgba(0, 0, 0, 0)
		url(https://platform.twitter.com/widgets/images/btn.27237bab4db188ca749164efd38861b0.png)
		-20px 9px no-repeat;
}

.btn-twitter:hover {
	background-position: -21px -16px;
}
</style>

</head>
<body>



	<div id="wrapper">

		<jsp:include page="navbar.jsp"></jsp:include>

		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<br>
						<div class="panel panel-default">
							<div class="panel-heading">Usuario:</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
							
								<form id="frmUsrConfiguracion">
									<div class="col-sm-8 mtop15">
										<fieldset>
											<div class="form-group col-sm-4 col-xs-4">
												<div class="form-group">
													<label path="userName">Nombre de Usuario</label> <input
														disabled="disabled" id="userName" name="userName"
														value="${userName}" class="form-control" />
												</div>
											</div>
											<div class="form-group col-sm-4 col-xs-4">
												<label path="apellido">Apellido</label> <input
													id="idUsuario" name="idUsuario" class="form-control"
													type="hidden" value="${id}"> <input id="apellido"
													name="apellido" class="form-control" value="${apellido}" />
											</div>
											<div class="form-group col-sm-4 col-xs-4">
												<label path="nombre">Nombre</label> <input id="nombre"
													name="nombre" class="form-control" value="${nombre}" />
											</div>
											<div class="clearfix"></div>
											<div class="form-group col-sm-4 col-xs-4">
												<label for="nroDocumento">Nro. Documento</label> <input
													id="nroDocumento" name="nroDocumento" class="form-control"
													type="text" value="${nroDocumento}">
											</div>

											<div class="col-sm-4 col-xs-4 form-group"
												id="sandbox-container">
												<label for="fechaNacimiento">Fecha Nacimiento</label>
												<div class="input-group date">
													<input id="fechaNacimiento" name="fechaNacimiento"
														class="form-control" type="text"
														value="${fechaNacimiento}"> <span
														class="input-group-addon"><i
														class="glyphicon glyphicon-calendar"></i></span>
												</div>
											</div>

											<div class="form-group col-sm-4 col-xs-4">
												<label for="telefonoParticular">Teléfono personal</label> <input
													id="telefonoParticular" name="telefonoParticular"
													class="form-control" type="text"
													value="${telefonoParticular}" maxlength="25">
											</div>

											<div class="clearfix"></div>
											<div class="form-group col-sm-4 col-xs-4">
												<label for="telefonoLaboral">Teléfono laboral</label> <input
													id="telefonoLaboral" name="telefonoLaboral"
													class="form-control" type="text" value="${telefonoLaboral}"
													maxlength="25">
											</div>

											<div class="form-group col-sm-4 col-xs-4">
												<label for="email">Email</label> <input id="email"
													name="email" class="form-control" type="text"
													value="${email}" />
											</div>
											<div class="form-group col-sm-4 col-xs-4">
												<label path="">Provincia</label> <select id="provincia"
													class="form-control" name="provincia">
													<option value="-1">-Seleccionar-</option>
													<c:forEach items="${provincias}" var="provincia">
														<option value="${provincia.id}">${provincia.descripcion}</option>
													</c:forEach>

												</select>
											</div>
											<div class="clearfix"></div>
											<div class="form-group col-sm-4 col-xs-4">
												<label path="">Localidad</label> <select id="localidad"
													class="form-control" name="localidad">
													<option value="-1">-Seleccionar-</option>
												</select>
											</div>
											<div class="form-group col-sm-4 col-xs-4">
												<label path="">Perfil</label> <select id="perfil"
													class="form-control" name="perfil" disabled="">
													<option value="1">Administrador</option>
												</select>
											</div>
											<div class="form-group col-sm-4 col-xs-4">
												<label for="estado">Estado</label><br> <select
													id="estado" name="estado" class="form-control"
													disabled="disabled">
													<option value=""></option>
													<option value="true" selected="selected">Activo</option>
													<option value="false">Bloqueado</option>
												</select>
											</div>

											<div class="clearfix"></div>
											<div class="form-group col-sm-4 col-xs-4">
												<button type="button"
													class="btn btn-primary mtop10 f-right btnGuardarUsuConf">Guardar</button>
											</div>
										</fieldset>
									</div>
								</form>

								<div class="col-sm-4 mtop15">
									<form id="password" method="post" novalidate="novalidate"
										class="fv-form fv-form-bootstrap">
										<div class="form-group col-sm-9 col-xs-4">
											<label for="disabledTextInput">Contraseña actual</label> <input
												type="password" id="inputOldPassword" class="form-control"
												placeholder="" name="oldpassword">
										</div>
										<div class="form-group col-sm-9 col-xs-4">
											<label for="disabledTextInput">Nueva contraseña</label> <input
												type="password" id="inputPassword" class="form-control"
												name="password" data-fv-field="password"> 
										</div>

										<div class="col-sm-9 col-xs-9 form-group">
											<label for="disabledTextInput">Repetir contraseña</label> <input
												type="password" id="inputPassword2" class="form-control"
												name="password2">
										</div>

										<div class="col-sm-9 col-xs-12">
											<button type="button"
												class="btn btn-primary f-right btnCambiarContraseña">
												<i id="spinner-enviar"
													class="fa fa-spinner fa-pulse btn-spinner hide"></i>
												Guardar
											</button>
											<button type="reset" class="btn btn-md">Borrar</button>
										</div>
									</form>

								</div>
								<div class="clearfix"></div>


							</div>
							<!-- /.panel-body -->
						</div>



						<!-- /.col-lg-12 -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /#page-wrapper -->

		</div>
		<!-- /#wrapper -->
	</div>


	<jsp:include page="commons.jsp"></jsp:include>
	<!-- DataTables JavaScript -->
	<script src="reservas/usuarioConfiguracion.js"></script>

</body>

</html>
