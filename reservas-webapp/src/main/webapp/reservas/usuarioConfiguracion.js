/**
 * 
 */
$(function() {
	'use strict';
	$(".alert-success").hide();
	$(".alert-danger").hide();
	
	$(".btnGuardarUsuConf").click(function() {	
		var usuarioDTO = {
				idUsuario:$("#idUsuario").val(),		
				apellido : $("#apellido").val(),
				nombre : $("#nombre").val(),
				userName : $("#userName").val(),				
				nroDocumento : $("#nroDocumento").val(),				
				fechaNacimiento : $("#fechaNacimiento").val(),
				estado : true,			
				telefonoParticular : $("#telefonoParticular").val(),
				telefonoLaboral : $("#telefonoLaboral").val(),
				perfil: $("#perfil").val(),
				email : $("#email").val(),
				localidad: $("#localidad").val(),
				provincia: $("#provincia").val()
		};
				
		
		
		$.ajax({
			url : "./usuarioConfiguracion/saveUsuario",
			type : "POST",
			data : JSON.stringify(usuarioDTO),
			contentType: 'application/json',		    
			dataType : "json",		
			success : function(data) {
				if(data.success){
					$("#okMsg").text("Los datos fueron actualizados correctamente");
					$(".alert-success").show();
					$(".alert-danger").hide();					
				}else{
					$("#errorMsg").text(data.message);
					$(".alert-danger").show();
					$(".alert-success").hide();
				}
			},
			error : function(data) {
				$("#errorMsg").text(data.responseText);
				$(".alert-danger").show();
				$(".alert-success").hide();
			}
		});

		
		
		
		
	});
	
	$(".btnCambiarContraseña").click(function() {	
		var passwordDTO = {
				oldPassword:$("#inputOldPassword").val(),		
				inputPassword : $("#inputPassword").val(),
				inputPassword2 : $("#inputPassword2").val()
		};
				
		
		
		$.ajax({
			url : "./usuarioConfiguracion/changePassword",
			type : "POST",
			data : JSON.stringify(passwordDTO),
			contentType: 'application/json',		    
			dataType : "json",		
			success : function(data) {
				if(data.success){
					$("#okMsg").text("Los datos fueron actualizados correctamente");
					$(".alert-success").show();
					$(".alert-danger").hide();
				}else{
					$("#errorMsg").text(data.message);
					$(".alert-danger").show();
					$(".alert-success").hide();
				}
			},
			error : function(data) {
				$("#errorMsg").text(data.responseText);
				$(".alert-danger").show();
				$(".alert-success").hide();
			}
		});				
	});
	
	$("#provincia").change(function() {
		 var id = parseInt(this.value);
		$.ajax({
			url : "./usuario/findByProvinciaId/"+id,
			type : "GET",			
			contentType: 'application/json',		    
			dataType : "json",		
			success : function(data) {
				if(data.success){
					var items = data.rows;
					$.each(items, function (i, item) {
					    $('#localidad').append($('<option>', { 
					        value: item.id,
					        text : item.descripcion 
					    }));
					});		
				}
			},
			error : function(data) {

			}
		});
	});
});
