package com.reservas.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ComboDTO implements Serializable {
	private Long id;

	private String descripcion;

	private Double descuento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public ComboDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComboDTO(Long id, String descripcion, Double descuento) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.descuento = descuento;
	}

	@Override
	public String toString() {
		return "ComboDTO [id=" + id + ", descripcion=" + descripcion + ", descuento=" + descuento + "]";
	}
	
	
	
}
