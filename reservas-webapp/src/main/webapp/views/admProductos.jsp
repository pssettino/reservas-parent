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
<link rel="stylesheet"
	href="css/bootstrap-datetimepicker.css">
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
							<div class="panel-heading">Productos</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#modalProducto">Nuevo</button>
								<br> <br>
								<table width="100%"
									class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>Nombre</th>
											<th>Precio</th>
											<th>Categoria</th>
											<th>Stock</th>
											<th>Acciones</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${productos}" var="producto" varStatus="index">
											<tr>
												<td>${producto.nombre }</td>
												<td>${producto.precio }</td>
												<td>${producto.categoria }</td>
												<td>${producto.stock }</td>
												<td>
													<button type="button" data-idproducto="${producto.id }"
														id="btnEditarProducto" name="btnEditarProducto"
														class="btn btn-info btnEditarProducto">
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													</button>
													<button type="button" data-idproducto="${producto.id }"
														data-toggle="confirmation" data-singleton="true"
														id="btnEliminarProducto" name="btnEliminarProducto"
														class="btn btn-danger btnEliminarProducto">
														<i class="fa fa-trash" aria-hidden="true"></i>
													</button>
												</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>


								<div class="clearfix"></div>

								<!-- Modal -->
								<div id="modalProducto" class="modal fade" role="dialog">
									<div class="modal-dialog-center modal-lg">

										<!-- Modal content-->
										<div class="modal-content" style="left: 235px;">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Administrar Producto</h4>
											</div>
											<div class="modal-body">
											<div class="clearfix"></div>
												<div class="panel panel-default">													
													<div class="panel-body">
												<form:form method="POST"											
													modelAttribute="productoDTO">
													<div class="col-sm-6 col-xs-12">
														<div class="form-group">
															<form:label path="nombre">Producto</form:label>
															<form:hidden path="id" />
															<form:input path="nombre" class="form-control"
																placeholder="Ingrese Producto" />
														</div>
														 
														<div class="form-group">
															<form:label path="precio">Precio</form:label>
															<form:input path="precio" class="form-control"
																placeholder="Ingrese Precio" />
														</div>
														<div class="form-group">
																	<form:label path="">Categoria</form:label>
																	<select id="categoria" class="form-control"
																		name="categoria" >
																		<option value="-1">-Seleccionar-</option>
																		<c:forEach items="${categorias}" var="categoria">
																		<option value="${categoria.id}">${categoria.descripcion}</option>
																		</c:forEach>
																	</select>
  																</div> 
													</div>
													<div class="col-sm-6 col-xs-12">
														<div class="form-group">
															<form:label path="stock">Stock</form:label>
															<form:input path="stock" class="form-control"
																placeholder="Ingrese Stock" />
														</div>
													</div>
													<div class="modal-footer">
																	<button type="button" id="btnGuardarProducto" name ="btnGuardarProducto"
																		class="btn btn-success">Guardar</button>
																	<button type="button" class="btn btn-default"
																		data-dismiss="modal">Close</button>
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
	<script src="reservas/abmProductos.js"></script>

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
