package com.reservas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
@Entity
@Table(name = "factura_detalle")
public class FacturaDetalle {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_factura", nullable = true)
	private FacturaBO factura;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_producto", nullable = true)
	private ProductoBO producto;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_combo", nullable = true)
	private ComboBO combo;
	@Column(name = "precio")
	private Double precio;
	@Column(name = "cantidad")
	private Integer cantidad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FacturaBO getFactura() {
		return factura;
	}

	public void setFactura(FacturaBO factura) {
		this.factura = factura;
	}

	public ProductoBO getProducto() {
		return producto;
	}

	public void setProducto(ProductoBO producto) {
		this.producto = producto;
	}

	public ComboBO getCombo() {
		return combo;
	}

	public void setCombo(ComboBO combo) {
		this.combo = combo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "FacturaDetalle [id=" + id + ", factura=" + factura + ", producto=" + producto + ", combo=" + combo
				+ ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}

}
