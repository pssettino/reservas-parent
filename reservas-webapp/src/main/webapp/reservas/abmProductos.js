
$(function() {
	'use strict';
	
	$("#btnGuardarProducto").click(function() {				
		var productoDTO = {
				id:$("#id").val(),		
				nombre : $("#nombre").val(),
				precio : $("#precio").val(),
				cantidad : $("#cantidad").val(),								
				categoria: $("#categoria").text(),
				categoriaId: $("#categoria").val(),
		};				
		$('#frmProducto').data('formValidation').validate();
		$.ajax({
			url : "./saveProducto",
			type : "POST",
			data : JSON.stringify(productoDTO),
			contentType: 'application/json',		    
			dataType : "json",		
			success : function(data) {
				if(data.success){
					
					$("#modalProducto").modal("hide");
				}
			},
			error : function(data) {

			}
		});

	});
	
	
	$(".btnEditarProducto").click(function() {
		var form = {"id": $(this).data("idproducto")};
		$('#frmProducto').data('formValidation').validate();
		$.ajax({
			url : "./editarProducto",
			type : "POST",
			data : form ,			
			dataType : "json",		
			success : function(data) {
				if(data.success){
					var producto = data.item;
					$("#id").val(producto.id);
					$("#nombre").val(producto.nombre);
					$("#precio").val(producto.precio);
					$("#cantidad").val(producto.cantidad);
					
					$("#modalProducto").modal("show");
				}
			},
			error : function(data) {

			}
		});

	});
	$('[data-toggle=confirmation]').confirmation({
		  rootSelector: '[data-toggle=confirmation]',
		  onConfirm: function() {
			  var form = {"id": $(this).data("idproducto")};
				$.ajax({
					url : "./eliminarProducto",
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