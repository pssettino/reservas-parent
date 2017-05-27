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


						<div class="page-header">

							<div class="pull-right form-inline">
								<div class="btn-group">
									<button class="btn btn-primary" data-calendar-nav="prev">
										Prev</button>
									<button class="btn btn-default" data-calendar-nav="today">Today</button>
									<button class="btn btn-primary" data-calendar-nav="next">Next
									</button>
								</div>
								<div class="btn-group">
									<button class="btn btn-warning" data-calendar-view="year">Year</button>
									<button class="btn btn-warning active"
										data-calendar-view="month">Month</button>
									<button class="btn btn-warning" data-calendar-view="week">Week</button>
									<button class="btn btn-warning" data-calendar-view="day">Day</button>
								</div>
							</div>

							<h3></h3>
							<small></small>
						</div>

						<div class="row">
							<div class="col-md-9">
								<div id="calendar"></div>
								<br> <br>
							</div>
							<div class="col-md-3">
								<br> <br>
							</div>
						</div>
						<br> <br>
						<div class="clearfix"></div>

						<div class="modal fade" id="events-modal" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="modal-title">Event</h3>
									</div>
									<div class="modal-body" style="height: 400px"></div>
									<div class="modal-footer">
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

	<script type="text/javascript"
		src="components/underscore/underscore-min.js"></script>
	<script type="text/javascript"
		src="components/jstimezonedetect/jstz.min.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>	
	<script type="text/javascript" src="js/app.js"></script>
</body>

</html>
