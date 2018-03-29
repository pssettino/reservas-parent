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
							<div class="panel-heading">Combos</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#modalCombo">Nuevo</button>
								<br> <br>
								<table width="100%"
									class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>Apellido</th>
											<th>Nombre</th>
											<th>DNI</th>
											<th>Telefono</th>
											<th>Email</th>
											<th>Acciones</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${combos}" var="combo" varStatus="index">
											<tr>
												<td>${combo.descripcion }</td>
												<td>${combo.descuento }</td>
												<td>
													<button type="button" data-idcombo="${combo.id }"
														id="btnEditarCombo" name="btnEditarCombo"
														class="btn btn-info btnEditarCombo">
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													</button>
													<button type="button" data-idcombo="${combo.id }"
														data-toggle="confirmation" data-singleton="true"
														id="btnEliminarCombo" name="btnEliminarCombo"
														class="btn btn-danger btnEliminarCombo">
														<i class="fa fa-trash" aria-hidden="true"></i>
													</button>
												</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>


								<div class="clearfix"></div>

								<!-- Modal -->
								<div id="modalCombo" class="modal fade" role="dialog">
									<div class="modal-dialog-center modal-lg">

										<!-- Modal content-->
										<div class="modal-content" style="left: 235px;">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Administrar Combo</h4>
											</div>
											<div class="modal-body">
											<div class="clearfix"></div>
												<div class="panel panel-default">													
													<div class="panel-body">
												<form:form method="POST"											
													modelAttribute="comboDTO">
													<div class="col-sm-6 col-xs-12">
														<div class="form-group">
															<form:label path="descripcion">Descripcion</form:label>
															<form:hidden path="id" />
															<form:input path="descripcion" class="form-control"
																placeholder="Ingrese Descripcion" />
														</div>
														<div class="form-group">
															<form:label path="descuento">Descuento</form:label>
															<form:input path="descuento" class="form-control"
																placeholder="Ingrese Descuento" />
														</div>
														 
														<div class="form-group">
																	<form:label path="">Productos</form:label>
																	<select id="producto" class="form-control selectpicker" data-live-search="true" 
																		name="producto" multiple>
																		<option value="-1">-Seleccionar-</option>
																		<c:forEach items="${productos}" var="producto">
																		<option data-tokens="${producto.id}" value="${producto.id}">${producto.descripcion}</option>
																		</c:forEach>
																	</select>
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
	<!-- DataTables JavaScript -->

	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/dataTables.bootstrap.min.js"></script>
	<script src="js/dataTables.responsive.js"></script>
	<script src="reservas/abmCombos.js"></script>

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
