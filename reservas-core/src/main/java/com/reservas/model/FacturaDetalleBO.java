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
	private FacturaComboId id;

	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("facturaId")
	private FacturaBO factura;

	@ManyToOne(fetch = FetchType.EAGER)
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

	public FacturaComboId getId() {
		return id;
	}

	public void setId(FacturaComboId id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		FacturaDetalleBO that = (FacturaDetalleBO) o;
		return Objects.equals(factura, that.factura) && Objects.equals(combo, that.combo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(factura, combo);
	}

	public FacturaDetalleBO(Long idFacturaDetalle, FacturaComboId id, FacturaBO factura, ComboBO combo, Double precio,
			Integer cantidad) {
		super();
		this.id = id;
		this.factura = factura;
		this.combo = combo;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public FacturaDetalleBO(FacturaBO factura, ComboBO combo) {
		this.factura = factura;
		this.combo = combo;
		this.id = new FacturaComboId(factura.getId(), combo.getId());
	}

	@Override
	public String toString() {
		return "FacturaDetalleBO [id=" + id + ", factura=" + factura + ", combo=" + combo + ", precio=" + precio
				+ ", cantidad=" + cantidad + "]";
	}

}
