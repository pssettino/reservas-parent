/**
 * 
 */
$(document).ready(function() {
						getAllEvents();
						
						function getAllEvents() {
							var eventDataList = [];
							$.ajax({
							url : "./getAllEvents",
							type : "POST",
							contentType : 'application/json',
							dataType : "json",
							success : function(data) {
								renderFullCalendar(data);										
								},
								error : function(e) {
									console.log(e);
								}
							});		
						}
						
						function renderFullCalendar(data){
							var finalData = [];
							$.each(data, function(index, value) {
								var obj = {};
								if (value.end != null) {
									obj = {
										id : value.id,
										title : value.title,
										start : value.start,
										end : value.end,
										estadoId: value.estadoId
									};
						
								} else {
									var startStr = "";
									if(value.start!=null){
										startStr = value.start.substring(0, 10);
									}
									obj = {
										id : value.id,
										title : value.title,
										start : startStr,
										estadoId: value.estadoId
									};
						
								}
								finalData.push(obj);
								$('#calendar').fullCalendar( 'removeEvents');
								 $('#calendar').fullCalendar( 'addEventSource', finalData);
								 $('#calendar').fullCalendar( 'rerenderEvents' );
							});
						
							eventDataList = finalData;
							var today = new Date();
							$('#calendar').fullCalendar(
								{
									header : {
										left : 'prev,next today',
										center : 'title',
										right : 'month,agendaWeek,agendaDay,listWeek'
									},
									eventClick: function (calEvent, jsEvent, view) {
							            $("#evtId").val(calEvent.id);

							            $("#evtTitle").val(calEvent.title);
							            
							            if(calEvent.start!=null && calEvent.start!=''){
							            	$("#evtFechaDesde").val(calEvent.start.toISOString());
							            } 
							            if(calEvent.end!=null && calEvent.end!=''){							            	
							            	$("#evtFechaHasta").val(calEvent.end.toISOString());
							            }
							            $("#evtAllDay").val(calEvent.allDay);
							            $("#evtEstado").prop("checked",calEvent.estadoId > 1);
							            $("#evtModal").modal("show");
							        },
									locale:'es',
									defaultDate : today.toISOString(),
									navLinks : true,
									selectable : true,
									selectHelper : true,
									select : function(start, end) {
										var title = prompt('Titulo del Evento:');
										var eventData;
										if (title) {
											if (end != "") {
												eventData = {
													title : title,
													start : start,
													end : end,
													estadoId:0
												};
											} else {
												eventData = {
													title : title,
													start : start,
													estadoId:false
												};
											}
						
											$.ajax({
														url : "./saveEvent",
														type : "POST",
														data : JSON.stringify(eventData),
														contentType : 'application/json',
														dataType : "json",
														success : function(data) {
															$('#calendar').fullCalendar('renderEvent',data,true);
															$('#calendar').fullCalendar('updateEvent',data);
														},
														error : function(e) {
															console.log(e);
														}
													});
										}
										$('#calendar').fullCalendar('unselect');
									},
									eventResize : function(
											event, delta,
											revertFunc) {
						
										var eventData = {
											id : event.id,
											title : event.title,
											start : event.start,
											end : event.end,
											allDay : event.allDay,
											estadoId:event.estadoId
										};
						
										$.ajax({
													url : "./updateEvent",
													type : "POST",
													data : JSON.stringify(eventData),
													contentType : 'application/json',
													dataType : "json",
													success : function(data) {
														$('#calendar').fullCalendar('updateEvent',data);
													},
													error : function(e) {
														console.log(e);
													}
												});
						
									},
									eventDrop : function(event, delta,revertFunc) {
										var eventData = {
											id : event.id,
											title : event.title,
											start : event.start,
											end : event.end,
											allDay : event.allDay,
											estadoId:event.estadoId
										};
						
										$.ajax({
													url : "./updateEvent",
													type : "POST",
													data : JSON.stringify(eventData),
													contentType : 'application/json',
													dataType : "json",													
													success : function(data) {
														$('#calendar').fullCalendar(
																		'updateEvent',
																		data);
													},
													error : function(
															e) {
														console
																.log(e);
													}
												});
						
									},
									editable : true,
									eventLimit : true, 
													events : eventDataList
												});

						}
						
						$("#btnAceptarEvt").click(function() {																				
							var eventData = {
									id : $("#evtId").val(),
									title : $("#evtTitle").val(),
									start : $("#evtFechaDesde").val(),
									end : $("#evtFechaHasta").val(),
									allDay :$("#evtAllDay").val(),
									estadoId:$("#evtEstado").prop("checked") ?1:0
								};
				
								$.ajax({
											url : "./updateEvent",
											type : "POST",
											data : JSON.stringify(eventData),
											contentType : 'application/json',
											dataType : "json",
											success : function(data) {
												$("#evtModal").modal("hide");
												getAllEvents();
											
											},
											error : function(e) {
												console.log(e);
											}
										});
						});
						
						
					
				});
