package com.reservas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
@Entity
@Table(name = "factura_detalle")
public class FacturaDetalleBO implements Serializable {

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFacturaDetalle;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
		result = prime * result + ((combo == null) ? 0 : combo.hashCode());
		result = prime * result + ((factura == null) ? 0 : factura.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idFacturaDetalle == null) ? 0 : idFacturaDetalle.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FacturaDetalleBO other = (FacturaDetalleBO) obj;
		if (cantidad == null) {
			if (other.cantidad != null)
				return false;
		} else if (!cantidad.equals(other.cantidad))
			return false;
		if (combo == null) {
			if (other.combo != null)
				return false;
		} else if (!combo.equals(other.combo))
			return false;
		if (factura == null) {
			if (other.factura != null)
				return false;
		} else if (!factura.equals(other.factura))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idFacturaDetalle == null) {
			if (other.idFacturaDetalle != null)
				return false;
		} else if (!idFacturaDetalle.equals(other.idFacturaDetalle))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}

	public FacturaDetalleBO(Long idFacturaDetalle, FacturaProductoId id, FacturaBO factura, ProductoBO producto,
			ComboBO combo, Double precio, Integer cantidad) {
		super();
		this.idFacturaDetalle = idFacturaDetalle;
		this.id = id;
		this.factura = factura;
		this.producto = producto;
		this.combo = combo;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "FacturaDetalleBO [idFacturaDetalle=" + idFacturaDetalle + ", id=" + id + ", factura=" + factura
				+ ", producto=" + producto + ", combo=" + combo + ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}

}
