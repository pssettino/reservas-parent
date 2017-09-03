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
							<div class="panel-heading">Usuario: </div>
							<!-- /.panel-heading -->
							<div class="panel-body">

								<div class="col-sm-6 mtop15">
								<fieldset>                                	
                                    <div class="form-group col-sm-3 col-xs-3">
                                        <label for="idTipoDeDoc">Tipo *</label><br>
                                        <select id="idTipoDeDoc" name="idTipoDeDoc" class="form-control" disabled="disabled">
                                            <option value=""></option>
                                            <option value="0" selected="selected">DNI</option><option value="25">LE</option><option value="26">LC</option><option value="31">DNI EXT</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-sm-5 col-xs-4">
                                        <label for="nroDocumento">Nro. Documento</label>
                                        <input id="nroDocumento" name="nroDocumento" class="form-control" disabled="disabled" type="text" value="1000001">
                                    </div> 
                                    <div class="form-group col-sm-4 col-xs-5">
                                        <label for="CUIT">CUIL/CUIT</label>
                                        <input id="CUIT" name="CUIT" class="form-control" disabled="disabled" type="text" value="">
                                    </div>
                                    <div class="clearfix"></div>
                                    
                                    <div class="form-group col-sm-4 col-xs-4">
                                        <label for="idEstadoCivil">Estado Civil</label><br>
                                        <select id="idEstadoCivil" name="idEstadoCivil" class="form-control">
                                            <option value="">- Seleccionar -</option>
                                            <option value="1">CASADO/A</option><option value="2">VIUDO/A</option><option value="4">DIVORCIADO</option><option value="5" selected="selected">SOLTERO/A</option><option value="6">CONVEVI</option><option value="8">CONV</option>
                                        </select>
                                     </div>             
                                    <div class="col-sm-4 col-xs-4 form-group" id="sandbox-container">
                                        <label for="fechaNacimiento">Fecha Nacimiento</label>
                                        <div class="input-group date">
                                            <input id="fechaNacimiento" name="fechaNacimiento" class="form-control" type="text" value="10/11/1982">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group col-sm-4 col-xs-4">
                                        <label for="estado">Estado</label><br>
                                        <select id="estado" name="estado" class="form-control" disabled="disabled">
                                            <option value=""></option>
                                            <option value="true" selected="selected">Activo</option><option value="false">Bloqueado</option>
                                        </select>
                                    </div>       
                                    <div class="clearfix"></div>
                                                                        <div class="form-group col-sm-6 col-xs-6">
                                        <label for="telefonoParticular">Teléfono personal</label>
                                        <input id="telefonoParticular" name="telefonoParticular" class="form-control" type="text" value="" maxlength="25">
                                    </div>
                                    <div class="form-group col-sm-6 col-xs-6">
                                        <label for="telefonoLaboral">Teléfono laboral</label>
                                        <input id="telefonoLaboral" name="telefonoLaboral" class="form-control" type="text" value="" maxlength="25">
                                    </div>
                                    <button type="submit" class="btn btn-primary mtop10 f-right">Guardar</button>
                                    
                                </fieldset>
								</div>

								<div class="col-sm-6 mtop15">
								  <form id="password" method="post" novalidate="novalidate" class="fv-form fv-form-bootstrap"><button type="submit" class="fv-hidden-submit" style="display: none; width: 0px; height: 0px;"></button>
                                	<input type="hidden" id="checkedTipoDoc" name="checkedTipoDoc" value="0">
                                	<input type="hidden" id="inputNroDocumento" name="inputNroDocumento" value="1000001">
                                	<input type="hidden" id="inputToken" name="inputToken" value="f25a4a69b04aff15e41036c26b7ff708">
                                    <div class="form-group col-sm-12 col-xs-4">
                                        <label for="disabledTextInput">Contraseña actual</label>
                                        <input type="password" id="inputOldPassword" class="form-control" placeholder="" name="oldpassword">
                                    </div>
                                    <div class="form-group col-sm-12 col-xs-4">
                                        <label for="disabledTextInput">Nueva contraseña</label>
                                        <input type="password" id="inputPassword" class="form-control" name="password" data-fv-field="password">
                                    <small class="help-block" data-fv-validator="notEmpty" data-fv-for="password" data-fv-result="NOT_VALIDATED" style="display: none;">Este campo es requerido</small></div>
                                    
                                    <div class="col-sm-12 col-xs-4 form-group">
                                        <label for="disabledTextInput">Repetir contraseña</label>
                                        <input type="password" id="inputPassword2" class="form-control" name="password2">
                                    </div>
                                    
                                    <div class="col-sm-12 col-xs-12">
                                        <button type="button" class="btn btn-primary f-right" onclick="actualizar(callback)"><i id="spinner-enviar" class="fa fa-spinner fa-pulse btn-spinner hide"></i> Guardar</button> 
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
	

</body>

</html>
