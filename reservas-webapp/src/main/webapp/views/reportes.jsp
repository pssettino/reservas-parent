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
</head>
<body>

	<div id="wrapper">

		<jsp:include page="navbar.jsp"></jsp:include>

		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Blank</h1>

						<div class="eventcontrol"></div>
						<div class="eventcontrol-target"></div>
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


	
	<jsp:include page="commons.jsp"></jsp:include>
	<script src="js/hammer.min.js"></script>
	<script src="js/moment.min.js"></script>
	<script src="js/eventcontrol.js"></script>
 <script src="reservas/reportes.js"></script>
	 </body>

</html>
