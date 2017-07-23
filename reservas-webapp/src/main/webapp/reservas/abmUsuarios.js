/**
 * 
 */
$(function() {
	'use strict';
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
					$("#id").val(usuario.id);
					$("#apellido").val(usuario.apellido);
					$("#nombre").val(usuario.nombre);
					$("#dni").val(usuario.nroDocumento);
					$("#telefono").val(usuario.telefonoParticular);
					$("#email").val(usuario.email);
					
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
});