/**
 * 
 */
$(function() {
	'use strict';
	$("#btnGuardarUsuario").click(function() {				
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
			url : "./saveUsuario",
			type : "POST",
			data : JSON.stringify(usuarioDTO),
			contentType: 'application/json',		    
			dataType : "json",		
			success : function(data) {
				if(data.success){
					
					$("#modalUsuario").modal("hide");
				}
			},
			error : function(data) {

			}
		});

	});
	$(".btnEditarUsuario").click(function() {
		var form = {"id": $(this).data("idusuario")};
		$.ajax({
			url : "./editarUsuario",
			type : "POST",
			data : form ,			
			dataType : "json",		
			success : function(data) {
				if(data.success){
					var usuario = data.item;
					$("#idUsuario").val(usuario.idUsuario);
					$("#id").val(usuario.id);
					$("#apellido").val(usuario.apellido);
					$("#nombre").val(usuario.nombre);
					$("#nroDocumento").val(usuario.nroDocumento);
					$("#telefonoParticular").val(usuario.telefonoParticular);
					$("#telefonoLaboral").val(usuario.telefonoLaboral);
					$("#fechaNacimiento").val(usuario.fechaNacimiento);
					$("#email").val(usuario.email);
					$("#userName").val(usuario.userName);
					$("#modalUsuario").modal("show");
				}
			},
			error : function(data) {

			}
		});

	});
	$('[data-toggle=confirmation]').confirmation({
		  rootSelector: '[data-toggle=confirmation]',
		  onConfirm: function() {
			  var form = {"id": $(this).data("idusuario")};
				$.ajax({
					url : "./eliminarUsuario",
					type : "POST",
					data : form ,			
					dataType : "json",		
					success : function(data) {
						if(data.success){
							
						}
					},
					error : function(data) {

					}
				});
			  }
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