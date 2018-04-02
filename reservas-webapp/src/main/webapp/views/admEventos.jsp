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
<link rel="stylesheet" href="css/bootstrap-datetimepicker.css">
<link rel="stylesheet" href="css/calendar.css">
	<link rel="stylesheet"
	href="css/bootstrap-select.css">s
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
							<div class="panel-heading">Eventos</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#modalEvento">Nuevo</button>
								<br> <br>
								<table width="100%"
									class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>Titulo</th>
											<th>Descripcion</th>
											<th>Desde</th>
											<th>Hasta</th>
											<th>Usuario</th>
											<th>Acciones</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${eventos}" var="evt" varStatus="index">
											<tr>
												<td>${evt.titulo }</td>
												<td>${evt.descripcion }</td>
												<td>${evt.fechaDesde }</td>
												<td>${evt.fechaHasta }</td>
												<td>${evt.usuario }</td>
												<td>
													<button type="button" data-idevento="${evt.id }"
														id="btnEmitirReciboEvento" name="btnEmitirReciboEvento"
														class="btn btn-success btnEmitirReciboEvento">
														<i class="fa fa-copy" aria-hidden="true"></i>
													</button>	
													<button type="button" data-idevento="${evt.id }"
														id="btnEditarEvento" name="btnEditarEvento"
														class="btn btn-info btnEditarEvento">
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													</button>
													<button type="button" data-idevento="${evt.id }"
														data-toggle="confirmation" data-singleton="true"
														id="btnEliminarEvento" name="btnEliminarEvento"
														class="btn btn-danger btnEliminarEvento">
														<i class="fa fa-trash" aria-hidden="true"></i>
													</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

								<div id="modalEvento" class="modal fade" role="dialog">
									<div class="modal-dialog-center modal-lg">
										<!-- Modal content-->
										<div class="modal-content" style="left: 235px;">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Administrar Evento</h4>
											</div>
											<div class="modal-body">
												<div class="panel panel-default">
													<div class="panel-body">
														<form:form method="POST" id="frmEvento" name="frmEvento"
															modelAttribute="eventoDTO">
															<div class="col-sm-6 col-xs-6">
																<div class="form-group">
																	<form:label path="titulo">Titulo</form:label>
																	<form:input path="titulo" class="form-control"
																		placeholder="Ingrese Titulo" />
																</div>
																<div class="form-group">
																	<form:label path="descripcion">Descripcion</form:label>
																	<form:hidden path="id" name="id" id="id" />
																	<form:input path="descripcion" class="form-control"
																		placeholder="Ingrese Descripcion" />
																</div>
																<div class="form-group">
																	<form:label path="">Fecha Desde</form:label>
																	<div class="input-group date formDatetime">
																		<input type="text" id="fechaDesde" name="fechaDesde"
																			class="form-control" value=""> <span
																			class="input-group-addon"><i
																			class="glyphicon glyphicon-calendar"></i></span>
																	</div>

																</div>
																<div class="form-group">
																	<form:label path="">Fecha Hasta</form:label>
																	<div class="input-group date formDatetime">
																		<input type="text" id="fechaHasta" name="fechaHasta"
																			class="form-control" value=""> <span
																			class="input-group-addon"><i
																			class="glyphicon glyphicon-calendar"></i></span>
																	</div>
																</div>
																</div>
																<div class="col-sm-6 col-xs-6">
																<div class="form-group">
																	<form:label path="">Usuario</form:label>
																	<select id="usuario" class="form-control " 	name="usuario" >
																		<option value="-1">-Seleccionar-</option>
																		<c:forEach items="${usuarios}" var="usuario">
																		<option value="${usuario.idUsuario}">${usuario.userName}</option>
																		</c:forEach>
																	</select>
  																</div>
  																<div class="form-group">
																	<form:label path="">Combos</form:label>
																	<select id="combo" class="form-control " 	name="combo" >
																		<option value="-1">-Seleccionar-</option>
																		<c:forEach items="${combos}" var="combo">
																		<option value="${combo.id}">${combo.descripcion}</option>
																		</c:forEach>
																	</select>
  																</div>
																<div class="form-group">
																	<form:label path="">Estado</form:label>
																	<select id="detalleEstado" class="form-control"
																		name="detalleEstado" disabled="">
																		<option value="1">Activo</option>
																	</select>
																</div>
															</div>
														 
																
														</form:form>
													</div>
												</div>
											</div>
											
										</div>
										<div class="modal-footer">
																	<button type="button" id="btnGuardarEvento"
																		name="btnGuardarEvento" class="btn btn-success">Guardar</button>
																	<button type="button" class="btn btn-default"
																		data-dismiss="modal">Close</button>
																</div>
									</div>
								</div>

								<div id="modalReciboEmitido" class="modal fade" role="dialog">
									<div class="modal-dialog-center modal-lg">
										<!-- Modal content-->
										<div class="modal-content" style="left: 235px;">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Presupuesto del Evento</h4>
											</div>
											<div class="modal-body">
												<div class="panel panel-default">
													<div class="panel-body">
														<form:form method="POST" id="frmReciboEvento" name="frmReciboEvento"
															modelAttribute="eventoDTO">
															 
																 <p id="mensaje"></p>
																<a href="" id="descargar"><i class="fa fa-download"></i>Descargar</a>														 			
																
														</form:form>
													</div>
												</div>
												
											</div>
											<div class="modal-footer">
																	<button type="button" class="btn btn-default"
																		data-dismiss="modal">Close</button>
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
	<script src="reservas/abmEventos.js"></script>
	<script>
		$(document)
				.ready(
						function() {

							$('.formDatetime').datetimepicker({
								language : "es",
								autoclose : true,
								clearBtn : true,
								format : "dd/mm/yyyy hh:ii"
							});

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

							$('#frmEvento')
									.formValidation(
											{
												framework : 'bootstrap',
												icon : {
													//valid: 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												// This option will not ignore invisible fields which belong to inactive panels
												excluded : [ ':disabled',
														':hidden',
														':not(:visible)' ],
												fields : {
													titulo : {
														validators : {
															notEmpty : {
																message : 'Campo requerido'
															}
														}
													},
													descripcion : {
														validators : {
															notEmpty : {
																message : 'Campo requerido'
															}
														}
													},
													fechaDesde : {
														validators : {
															notEmpty : {
																message : 'Campo requerido'
															}
														}
													},
													fechaHasta : {
														validators : {
															notEmpty : {
																message : 'Campo requerido'
															}
														}
													},
													usuario: {
														validators : {
															notEmpty : {
																message : 'Campo requerido'
															},
															callback : {
																message : 'Campo requerido',
																callback : function(value, validator) {													
																	return value!='-1';
																}
															}
														}
													},
													combo: {
														validators : {
															notEmpty : {
																message : 'Campo requerido'
															},
															callback : {
																message : 'Campo requerido',
																callback : function(value, validator) {													
																	return value!='-1';
																}
															}
														}
													}
												}
											});

						});
	</script>

</body>

</html>
