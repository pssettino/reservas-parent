package com.reservas.dto;

import java.io.Serializable;
import java.util.Date;

public class EventoDTO implements Serializable {

	private Long id;

	private Date fechaDesde;

	private Date fechaHasta;

	private String titulo;

	private String descripcion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EventoDTO(Long id, Date fechaDesde, Date fechaHasta, String titulo, String descripcion) {
		super();
		this.id = id;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.titulo = titulo;
		this.descripcion = descripcion;
	}

	public EventoDTO() {
		super();
	}

}
