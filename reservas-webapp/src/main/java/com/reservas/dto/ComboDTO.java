package com.reservas.dto;

import java.io.Serializable;
import java.util.List;

public class ComboDTO implements Serializable {
	private Long id;

	private String descripcion;

	private Double descuento;

	private List<String> productos;

	public List<String> getProductos() {
		return productos;
	}

	public void setProductos(List<String> productos) {
		this.productos = productos;
	}

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

	public ComboDTO(Long id, String descripcion, Double descuento, List<String> productos) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.descuento = descuento;
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "ComboDTO [id=" + id + ", descripcion=" + descripcion + ", descuento=" + descuento + "]";
	}

}
