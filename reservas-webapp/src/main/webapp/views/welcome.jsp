<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>

<head>
<title>Reservas Online</title>

<jsp:include page="header.jsp"></jsp:include>

<link rel="stylesheet"
	href="components/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="components/bootstrap3/css/bootstrap-theme.min.css">
<!-- <link rel="stylesheet" href="css/calendar.css"> -->



<link href='calendar/fullcalendar.min.css' rel='stylesheet' />
<link href='calendar/fullcalendar.print.min.css' rel='stylesheet'
	media='print' />

<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
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


						<div class="clearfix"></div>

						<div id='calendar'></div>

						<div class="clearfix"></div>

						<div id="evtModal" class="modal fade" role="dialog">
							<div class="modal-dialog-center modal-lg">

								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>
									<div class="modal-body">
										<form>
											<fieldset>
												<label for="Id">Id</label> <input type="hidden" name="evtId"
													id="evtId" class="form-control"> <label for="Id">Title</label>
												<input type="text" name="evtTitle" id="evtTitle"
													class="form-control">
													<input type="hidden" name="evtFechaDesde" id="evtFechaDesde"
													class="form-control">
													<input type="hidden" name="evtFechaHasta" id="evtFechaHasta"
													class="form-control">
													<input type="hidden" name="evtAllDay" id="evtAllDay"
													class="form-control">
													
													<label><input type="checkbox" value="2" id="evtEstado" name="evtEstado">Confirmar</label>
											</fieldset>
										</form>
									</div>
									<div class="modal-footer">
										<button id="btnAceptarEvt" name="btnAceptarEvt" type="button"
											class="btn btn-default">Aceptar</button>
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
									</div>
								</div>

							</div>
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

	<script src="js/bootstrap-confirmation.min.js"></script>
	<script src='calendar/lib/moment.min.js'></script>
	<script src='calendar/fullcalendar.min.js'></script>
	<script src='calendar/locale-all.js'></script>
	<script src='reservas/welcome.js'></script>

	<script type="application/javascript">
					  
	
		$('#sandbox-container input').datetimepicker({
			language: "es",
			autoclose: true,
			clearBtn: true
		});  
	
	
	</script>

</body>

</html>
