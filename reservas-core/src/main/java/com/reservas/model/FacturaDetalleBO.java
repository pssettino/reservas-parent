package com.reservas.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
@Entity
@Table(name = "factura_detalle")
public class FacturaDetalleBO implements Serializable {

	@EmbeddedId
	private FacturaProductoId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("facturaId")
	private FacturaBO factura;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("productoId")
	private ProductoBO producto;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("comboId")
	private ComboBO combo;

	@Column(name = "precio")
	private Double precio;

	@Column(name = "cantidad")
	private Integer cantidad;

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

	public FacturaDetalleBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FacturaProductoId getId() {
		return id;
	}

	public void setId(FacturaProductoId id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		FacturaDetalleBO that = (FacturaDetalleBO) o;
		return Objects.equals(factura, that.factura) && Objects.equals(producto, that.producto)
				&& Objects.equals(combo, that.combo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(factura, producto, combo);
	}

	public FacturaDetalleBO(Long idFacturaDetalle, FacturaProductoId id, FacturaBO factura, ProductoBO producto,
			ComboBO combo, Double precio, Integer cantidad) {
		super();
		this.id = id;
		this.factura = factura;
		this.producto = producto;
		this.combo = combo;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public FacturaDetalleBO(FacturaBO factura, ProductoBO producto, ComboBO combo) {
		this.factura = factura;
		this.producto = producto;
		this.combo = combo;
		this.id = new FacturaProductoId(factura.getId(), producto.getId(), combo.getId());
	}

	@Override
	public String toString() {
		return "FacturaDetalleBO [id=" + id + ", factura=" + factura + ", producto=" + producto + ", combo=" + combo
				+ ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}

}
