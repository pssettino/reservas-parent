/**
 * 
 */

$(function() {
	'use strict';
	
	$("#btnGuardarCombo").click(function() {				
		var comboDTO = {
				id:$("#id").val(),		
				descripcion : $("#descripcion").val(),
				descuento : $("#descuento").val(),
				productos: $("#producto").val()
		};				
		
		$.ajax({
			url : "./saveCombo",
			type : "POST",
			data : JSON.stringify(comboDTO),
			contentType: 'application/json',		    
			dataType : "json",		
			success : function(data) {
				if(data.success){
					
					$("#modalCombo").modal("hide");
				}
			},
			error : function(data) {

			}
		});

	});
	
	
	$(".btnEditarCombo").click(function() {
		var form = {"id": $(this).data("idcombo")};
		$.ajax({
			url : "./editarCombo",
			type : "POST",
			data : form ,			
			dataType : "json",		
			success : function(data) {
				if(data.success){
					var combo = data.item;
					$("#id").val(combo.id);
					$("#descripcion").val(combo.descripcion);
					$("#descuento").val(combo.descuento);
					
					$("#modalCombo").modal("show");
				}
			},
			error : function(data) {

			}
		});

	});
	$('[data-toggle=confirmation]').confirmation({
		  rootSelector: '[data-toggle=confirmation]',
		  onConfirm: function() {
			  var form = {"id": $(this).data("idcombo")};
				$.ajax({
					url : "./eliminarCombo",
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