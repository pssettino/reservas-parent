/**
 * 
 */
$(function() {
	'use strict';
	$("#btnGuardarEvento").click(function() {				
		var eventoDTO = {
				id:$("#id").val(),		
				titulo : $("#titulo").val(),
				descripcion : $("#descripcion").val(),
				fechaDesde : $("#fechaDesde").val(),				
				fechaHasta : $("#fechaHasta").val()								
		};
				
		$('#frmEvento').data('formValidation').validate();
		
		$.ajax({
			url : "./saveEvento",
			type : "POST",
			data : JSON.stringify(eventoDTO),
			contentType: 'application/json',		    
			dataType : "json",		
			success : function(data) {
				if(data.success){
					$("#modalEvento").modal("hide");
				}
			},
			error : function(data) {

			}
		});

	});
	$(".btnEditarEvento").click(function() {
		var form = {"id": $(this).data("idevento")};
		$('#frmEvento').data('formValidation').validate();
		$.ajax({
			url : "./editarEvento",
			type : "POST",
			data : form ,			
			dataType : "json",		
			success : function(data) {
				if(data.success){
					var evento = data.item;
					$("#id").val(evento.id);
					$("#titulo").val(evento.titulo);
					$("#descripcion").val(evento.descripcion);
					$("#fechaDesde").val(evento.fechaDesde);
					$("#fechaHasta").val(evento.fechaHasta);
					$("#modalEvento").modal("show");
				}
			},
			error : function(data) {

			}
		});

	});
	$('[data-toggle=confirmation]').confirmation({
		  rootSelector: '[data-toggle=confirmation]',
		  onConfirm: function() {
			  var form = {"id": $(this).data("idevento")};
				$.ajax({
					url : "./eliminarEvento",
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