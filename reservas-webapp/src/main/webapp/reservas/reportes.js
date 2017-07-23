/**
 * 
 */

$(function() {
	'use strict';

	$
			.ajax({
				url : "./getAllEvents",
				type : "POST",
				contentType : 'application/json',
				dataType : "json",
				beforeSend : function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
				},
				success : function(data) {
					var finalData = [];
					$.each(data, function(index, value) {
						var obj = {};
						obj = {
							timestamp : value.start,
							type : ""
						};
						finalData.push(obj);
					});

					$('.eventcontrol')
							.EventControl(
									{
										hammertime : true,
										onhover : function(item, element,
												event, inout) {
											if (inout == 'out') {
												$('.eventcontrol-target').html(
														'');
												element.css('color', element
														.data('clr'));
											} else {
												var x = [
														'<h2>',
														moment(item.timestamp)
																.format(
																		'YYYY-MM-DD HH:mm:ss'),
														'</h2>' ];
												$('.eventcontrol-target').html(
														x.join(''));
												$('.eventcontrol-target').css(
														'color',
														element.css('color'));
												element.data('clr', element
														.css('color'));
												element.css('color', '#9b59b6');
											}
										},
										oncreate : function(item, element) {
											if (item.type == 'error') {
												element.css('color', '#e74c3c');
											} else if (item.type == 'warning') {
												element.css('color', '#e67e22');
											} else {
												element.css('color', '#1abc9c');
											}
										},
										onclick : function(item, element, event) {
											alert(item.timestamp);
										},
										data : finalData

									});

				}
			});

});
