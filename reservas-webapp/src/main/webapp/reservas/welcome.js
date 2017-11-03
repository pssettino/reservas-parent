/**
 * 
 */
$(document).ready(function() {
						var eventDataList = [];
						$.ajax({
						url : "./getAllEvents",
						type : "POST",
						contentType : 'application/json',
						dataType : "json",
						success : function(data) {
							var finalData = [];
							$.each(data, function(index, value) {
								var obj = {};
								if (value.end != null) {
									obj = {
										id : value.id,
										title : value.title,
										start : value.start,
										end : value.end
									};
						
								} else {
									obj = {
										id : value.id,
										title : value.title,
										start : value.start.substring(0, 10)
									};
						
								}
								finalData.push(obj);
							});
						
							eventDataList = finalData;
							$('#calendar').fullCalendar(
								{
									header : {
										left : 'prev,next today',
										center : 'title',
										right : 'month,agendaWeek,agendaDay,listWeek'
									},
									eventClick: function (calEvent, jsEvent, view) {
							            $("#name").val(calEvent.id);

							            $("#title").val(calEvent.title);

							            $("#evtModal").modal("show");
							        },
									locale:'es',
									defaultDate : '2017-07-09',
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
													end : end
												};
											} else {
												eventData = {
													title : title,
													start : start
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
											allDay : event.allDay
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
											allDay : event.allDay
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
						
							},
							error : function(e) {
								console.log(e);
							}
						});						
				});