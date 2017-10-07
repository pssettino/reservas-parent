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
<link rel="stylesheet" href="css/calendar.css">

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
							<div class="panel-heading">Usuario</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#modalUsuario">Nuevo</button>
								<br> <br>
								<table width="100%"
									class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>Nombre de Usuario</th>
											<th>Apellido</th>
											<th>Nombre</th>
											<th>DNI</th>
											<th>Telefono</th>
											<th>Email</th>
											<th>Acciones</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${usuarios}" var="user" varStatus="index">
											<tr>
												<td>${user.userName }</td>
												<td>${user.apellido }</td>
												<td>${user.nombre }</td>
												<td>${user.nroDocumento }</td>
												<td>${user.telefonoParticular }</td>
												<td>${user.email }</td>
												<td>
													<button type="button" data-idusuario="${user.idUsuario }"
														id="btnEditarUsuario" name="btnEditarUsuario"
														class="btn btn-info btnEditarUsuario">
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													</button>
													<button type="button" data-idusuario="${user.idUsuario }"
														data-toggle="confirmation" data-singleton="true"
														id="btnEliminarUsuario" name="btnEliminarUsuario"
														class="btn btn-danger btnEliminarUsuario">
														<i class="fa fa-trash" aria-hidden="true"></i>
													</button>
												</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>

								<div id="modalUsuario" class="modal fade" role="dialog">
									<div class="modal-dialog-center modal-lg">

										<!-- Modal content-->
										<div class="modal-content" style="left: 235px;">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Administrar Usuario</h4>
											</div>
											<div class="modal-body">
												<div class="clearfix"></div>
												<div class="panel panel-default">													
													<div class="panel-body">
														<form:form method="POST"															
															modelAttribute="usuarioDTO">
															<div class="col-sm-6 col-xs-12">
																<div class="form-group">
																	<form:label path="userName">Nombre de Usuario</form:label>
																	<form:input path="userName" class="form-control"
																		placeholder="Ingrese Nombre de Usuario" />
																</div>
																<div class="form-group">
																	<form:label path="apellido">Apellido</form:label>
																	<form:hidden path="idUsuario" name="idUsuario" id="idUsuario"/>
																	<form:input path="apellido" class="form-control"
																		placeholder="Ingrese Apellido" />
																</div>
																<div class="form-group">
																	<form:label path="nombre">Nombre</form:label>
																	<form:input path="nombre" class="form-control"
																		placeholder="Ingrese Nombre" />
																</div>
																<div class="form-group">
																	<form:label path="nroDocumento">DNI</form:label>
																	<form:input path="nroDocumento" class="form-control"
																		placeholder="Ingrese DNI" />
																</div>
																
																<div class="form-group">
																	<form:label path="">Fecha de Nacimiento</form:label>
																	<div class="input-group date">
																		<input type="text" id="fechaNacimiento"
																			name="fechaNacimiento" class="form-control"
																			value=""> <span class="input-group-addon"><i
																			class="glyphicon glyphicon-calendar"></i></span>
																	</div>
																</div>

																<div class="form-group">
																	<form:label path="">Estado</form:label>
																	<select id="detalleEstado" class="form-control"
																		name="detalleEstado" disabled="">
																		<option value="1">Activo</option>
																	</select>
																</div>
																
															</div>
															<div class="col-sm-6 col-xs-12">
																<div class="form-group">
																	<form:label path="">Perfil</form:label>
																	<select id="perfil" class="form-control"
																		name="perfil" disabled="">
																		<option value="1">Administrador</option>
																	</select>
																</div>
																<div class="form-group">
																	<form:label path="telefonoParticular">Telefono Particular</form:label>
																	<form:input path="telefonoParticular"
																		class="form-control" placeholder="Ingrese Telefono" />
																</div>
																<div class="form-group">
																	<form:label path="telefonoLaboral">Telefono Laboral</form:label>
																	<form:input path="telefonoLaboral" class="form-control"
																		placeholder="Ingrese Telefono" />
																</div>
																<div class="form-group">
																	<form:label path="email">Email</form:label>
																	<form:input path="email" class="form-control"
																		placeholder="Ingrese Email" />
																</div>
																<div class="modal-footer">
																	<button type="button" id="btnGuardarUsuario" name ="btnGuardarUsuario"
																		class="btn btn-success">Guardar</button>
																	<button type="button" class="btn btn-default"
																		data-dismiss="modal">Close</button>
																</div>
															</div>
														</form:form>
													</div>
												</div>
											</div>
										</div>

									</div>
								</div>

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
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/dataTables.bootstrap.min.js"></script>
	<script src="js/dataTables.responsive.js"></script>
	<script src="reservas/abmUsuarios.js"></script>
	<script>
		$(document)
				.ready(
						function() {
							$('#dataTables-example')
									.DataTable(
											{
												responsive : true,
												language : {
													processing : "Procesando...",
													search : "Buscar:",
													lengthMenu : "Mostrar _MENU_ registros",
													info : "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
													infoEmpty : "Mostrando registros del 0 al 0 de un total de 0 registros",
													infoFiltered : "((filtrado de un total de _MAX_ registros)",
													infoPostFix : "",
													loadingRecords : "Cargando...",
													zeroRecords : "No se encontraron resultados",
													emptyTable : "Ningún dato disponible en esta tabla",
													paginate : {
														first : "Primero",
														previous : "Ultimo",
														next : "Siguiente",
														last : "Anterior"
													},
													aria : {
														sortAscending : ": Activar para ordenar la columna de manera ascendente",
														sortDescending : ": Activar para ordenar la columna de manera descendente"
													}
												}

											});
						});
	</script>

</body>

</html>
