package com.reservas.dto;

import java.io.Serializable;

public class EventoDTO implements Serializable {

	private Long id;

	private String fechaDesde;

	private String fechaHasta;

	private String titulo;

	private String descripcion;

	private String usuarioId;

	private String usuario;

	private String comboId;

	private String combo;

	public String getCombo() {
		return combo;
	}

	public void setCombo(String combo) {
		this.combo = combo;
	}

	public String getComboId() {
		return comboId;
	}

	public void setComboId(String comboId) {
		this.comboId = comboId;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

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

	public EventoDTO(Long id, String fechaDesde, String fechaHasta, String titulo, String descripcion, String usuarioId,
			String usuario, String comboId, String combo) {
		super();
		this.id = id;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.usuarioId = usuarioId;
		this.usuario = usuario;
		this.comboId = comboId;
		this.combo = combo;
	}

	public EventoDTO() {
		super();
	}

}
