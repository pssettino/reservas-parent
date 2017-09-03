<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">Eventos OnLine</a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">

				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">${usuarioBO.userName } <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
<!-- 						<li><a href="#"><i class="fa fa-user fa-fw"></i> User -->
<!-- 								Profile</a></li> -->
						<li><a href="usuarioConfiguracion"><i class="fa fa-gear fa-fw"></i> Configuración</a>
						</li>
						<li class="divider"></li>
						<li><a href="./logout"><i class="fa fa-sign-out fa-fw"></i>
								Cerrar sesión</a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="login"><i class="fa fa-dashboard fa-fw"></i>
								Eventos</a></li>
<!-- 						<li><a href="reportes"><i -->
<!-- 								class="fa fa-bar-chart-o fa-fw"></i> Reportes</a></li> -->
						<li><a href="#"><i class="fa fa-wrench fa-fw"></i>
								Administracion</a>
								 <ul class="nav nav-second-level">
                                <li>
                                    <a href="admClientes">Adm Clientes</a>
                                </li>
                                <li>
                                    <a href="admUsuarios">Adm Usuarios</a>
                                </li>
                            </ul>
								</li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>