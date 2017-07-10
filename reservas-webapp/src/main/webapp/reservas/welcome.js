/**
 * 
 */
$(document)
		.ready(
				function() {

					var eventDataList = [];

					$
							.ajax({
								url : "./getAllEvents",
								type : "POST",
								contentType : 'application/json',
								dataType : "json",
								beforeSend : function(xhr) {
									xhr.setRequestHeader("Accept",
											"application/json");
								},
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
												start : value.start.substring(0,10)
											};

										}
										finalData.push(obj);
									});

									eventDataList = finalData;
									$('#calendar')
											.fullCalendar(
													{
														header : {
															left : 'prev,next today',
															center : 'title',
															right : 'month,agendaWeek,agendaDay,listWeek'
														},
														defaultDate : '2017-07-09',
														navLinks : true,
														selectable : true,
														selectHelper : true,
														select : function(
																start, end) {
															var title = prompt('Event Title:');
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

																$
																		.ajax({
																			url : "./saveEvent",
																			type : "POST",
																			data : JSON
																					.stringify(eventData),
																			contentType : 'application/json',
																			dataType : "json",
																			beforeSend : function(
																					xhr) {
																				xhr
																						.setRequestHeader(
																								"Accept",
																								"application/json");
																			},
																			success : function(
																					data) {
																				$(
																						'#calendar')
																						.fullCalendar(
																								'renderEvent',
																								eventData,
																								true);
																			},
																			error : function(
																					e) {
																				console
																						.log(e);
																			}
																		});
															}
															$('#calendar')
																	.fullCalendar(
																			'unselect');
														},
														eventResize : function(
																event, delta,
																revertFunc) {

															var eventData = {
																id : event.id,
																title : event.title,
																start : event.start,
																end : event.end
															};

															$
																	.ajax({
																		url : "./updateEvent",
																		type : "POST",
																		data : JSON
																				.stringify(eventData),
																		contentType : 'application/json',
																		dataType : "json",
																		beforeSend : function(
																				xhr) {
																			xhr
																					.setRequestHeader(
																							"Accept",
																							"application/json");
																		},
																		success : function(
																				data) {
																			// $('#calendar').fullCalendar('renderEvent',
																			// eventData,
																			// true);
																		},
																		error : function(
																				e) {
																			console
																					.log(e);
																		}
																	});

														},
														eventDrop : function(
																event, delta,
																revertFunc) {

															var eventData = {
																id : event.id,
																title : event.title,
																start : event.start,
																end : event.end
															};

															$
																	.ajax({
																		url : "./updateEvent",
																		type : "POST",
																		data : JSON
																				.stringify(eventData),
																		contentType : 'application/json',
																		dataType : "json",
																		beforeSend : function(
																				xhr) {
																			xhr
																					.setRequestHeader(
																							"Accept",
																							"application/json");
																		},
																		success : function(
																				data) {
																			// $('#calendar').fullCalendar('renderEvent',
																			// eventData,
																			// true);
																		},
																		error : function(
																				e) {
																			console
																					.log(e);
																		}
																	});

														},
														editable : true,
														eventLimit : true, // allow
														// "more"
														// link
														// when
														// too
														// many
														// events
														events : eventDataList
													});

								},
								error : function(e) {
									console.log(e);
								}
							});

				});
