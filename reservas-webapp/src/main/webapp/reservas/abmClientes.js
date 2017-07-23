/**
 * 
 */

$(function() {
	'use strict';
	$(".btnEditarCliente").click(function() {
		var form = {"id": $(this).data("idcliente")};
		$.ajax({
			url : "./editarCliente",
			type : "POST",
			data : form ,			
			dataType : "json",		
			success : function(data) {
				if(data.success){
					var cliente = data.item;
					$("#id").val(cliente.id);
					$("#apellido").val(cliente.apellido);
					$("#nombre").val(cliente.nombre);
					$("#dni").val(cliente.dni);
					$("#telefono").val(cliente.telefono);
					$("#email").val(cliente.email);
					
					$("#modalCliente").modal("show");
				}
			},
			error : function(data) {

			}
		});

	});
	$('[data-toggle=confirmation]').confirmation({
		  rootSelector: '[data-toggle=confirmation]',
		  onConfirm: function() {
			  var form = {"id": $(this).data("idcliente")};
				$.ajax({
					url : "./eliminarCliente",
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