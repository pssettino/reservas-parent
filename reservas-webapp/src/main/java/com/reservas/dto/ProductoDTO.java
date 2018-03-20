package com.reservas.dto;

import java.io.Serializable;

public class ProductoDTO implements Serializable {

	private Long id;

	private String categoria;

	private Integer categoriaId;

	private Double precio;

	private String nombre;

	private Integer stock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public ProductoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public ProductoDTO(Long id, String categoria, Double precio, String nombre, Integer stock) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.precio = precio;
		this.nombre = nombre;
		this.stock = stock;
	}

}
