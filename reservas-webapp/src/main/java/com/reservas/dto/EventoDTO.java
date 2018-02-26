package com.reservas.dto;

import java.io.Serializable;

public class EventoDTO implements Serializable {

	private Long id;

	private String fechaDesde;

	private String fechaHasta;

	private String titulo;

	private String descripcion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
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

	public EventoDTO(Long id, String fechaDesde, String fechaHasta, String titulo, String descripcion) {
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
