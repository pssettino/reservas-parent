package com.reservas.dto;

import java.io.Serializable;

public class EventDataDTO implements Serializable {

	private Long id;
	private String title;
	private String start;
	private String end;

	
	
	
	
	public EventDataDTO(Long id, String title, String start, String end) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
	}

	public EventDataDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
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

	

}
