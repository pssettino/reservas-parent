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
							<div class="panel-heading">Clientes</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#modalCliente">Nuevo</button>
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
										<c:forEach items="${clientes}" var="cliente" varStatus="index">
											<tr>
												<td>${cliente.apellido }</td>
												<td>${cliente.nombre }</td>
												<td>${cliente.dni }</td>
												<td>${cliente.telefono }</td>
												<td>${cliente.email }</td>
												<td>
													<button type="button" data-idcliente="${cliente.id }" id="btnEditarCliente"
														name="btnEditarCliente" class="btn btn-info btnEditarCliente">
														<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
													</button>
													<button type="button" data-idcliente="${cliente.id }" data-toggle="confirmation" 
													data-singleton="true"
													id="btnEliminarCliente"
														name="btnEliminarCliente" class="btn btn-danger btnEliminarCliente">
														<i class="fa fa-trash" aria-hidden="true"></i>
													</button>
												</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>


								<div class="clearfix"></div>

								<!-- Modal -->
								<div id="modalCliente" class="modal fade" role="dialog">
									<div class="modal-dialog-center modal-lg">

										<!-- Modal content-->
										<div class="modal-content" style="left: 235px;">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Administrar Cliente</h4>
											</div>
											<div class="modal-body">
												<form:form method="POST"
													action="/reservas-webapp/saveCliente"
													modelAttribute="clienteDTO">
													<div class="form-group">
														<form:label path="apellido">Apellido</form:label>
														<form:hidden path="id"/>
														<form:input path="apellido" class="form-control"
															placeholder="Ingrese Apellido" />
													</div>
													<div class="form-group">
														<form:label path="nombre">Nombre</form:label>
														<form:input path="nombre" class="form-control"
															placeholder="Ingrese Nombre"  />
													</div>
													<div class="form-group">
														<form:label path="dni">DNI</form:label>
														<form:input path="dni" class="form-control"
															placeholder="Ingrese DNI"   />
													</div>
													<div class="form-group">
														<form:label path="telefono">Telefono</form:label>
														<form:input path="telefono" class="form-control"
															placeholder="Ingrese Telefono" />
													</div>
													<div class="form-group">
														<form:label path="email">Email</form:label>
														<form:input path="email" class="form-control"
															placeholder="Ingrese Email"   />
													</div>

													<div class="modal-footer">
														<button type="submit" id="btnGuardarCliente"
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
	<script src="reservas/abmClientes.js"></script>

	<script>
		$(document).ready(function() {
			$('#dataTables-example').DataTable({
				responsive : true,
				language: {
                    processing:     "Procesando...",
                    search:         "Buscar:",
                    lengthMenu:    "Mostrar _MENU_ registros",
                    info:           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                    infoEmpty:      "Mostrando registros del 0 al 0 de un total de 0 registros",
                    infoFiltered:   "((filtrado de un total de _MAX_ registros)",
                    infoPostFix:    "",
                    loadingRecords: "Cargando...",
                    zeroRecords:    "No se encontraron resultados",
                    emptyTable:     "Ningún dato disponible en esta tabla",
                    paginate: {
                        first:      "Primero",
                        previous:   "Ultimo",
                        next:       "Siguiente",
                        last:       "Anterior"
                    },
                    aria: {
                        sortAscending:  ": Activar para ordenar la columna de manera ascendente",
                        sortDescending: ": Activar para ordenar la columna de manera descendente"
                    }
                }
			});
		});
		
		
	</script>

</body>

</html>
