/**
 * 
 */

$(function() {
	'use strict';
	
	$("#btnGuardarCliente").click(function() {				
		var clienteDTO = {
				id:$("#id").val(),		
				apellido : $("#apellido").val(),
				nombre : $("#nombre").val(),
				dni : $("#dni").val(),								
				eliminado : false,		
				localidad: $("#localidad").val(),
				provincia: $("#provincia").val(),
				telefono : $("#telefono").val(),
				email : $("#email").val()		
		};				
		
		$.ajax({
			url : "./saveCliente",
			type : "POST",
			data : JSON.stringify(clienteDTO),
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
	
	$("#provincia").change(function() {
		 var id = parseInt(this.value);
		$.ajax({
			url : "./findByProvinciaId/"+id,
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