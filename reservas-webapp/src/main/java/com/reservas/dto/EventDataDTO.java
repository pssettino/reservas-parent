package com.reservas.dto;

import java.io.Serializable;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
public class EventDataDTO implements Serializable {

	private Long id;
	private String title;
	private String start;
	private String end;
	private Boolean allDay;
	private Long estadoId;

	public EventDataDTO(Long id, String title, String start, String end, Boolean allDay, Long estadoId) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.allDay = allDay;
		this.estadoId = estadoId;
	}

	public EventDataDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public Boolean getAllDay() {
		return allDay;
	}

	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Long getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}

}
