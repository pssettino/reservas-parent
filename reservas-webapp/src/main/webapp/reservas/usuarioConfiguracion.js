/**
 * 
 */
$(function() {
	'use strict';
	
	$("#btnGuardarUsuConf").click(function() {	
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
				email : $("#email").val()		
		};
		
		
		
	});
	
	$("#btnCambiarContraseña").click(function() {	
		
	});
});
