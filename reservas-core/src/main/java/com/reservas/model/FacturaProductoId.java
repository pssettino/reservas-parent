package com.reservas.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FacturaProductoId implements Serializable {

	@Column(name = "factura_id")
	private Long facturaId;
	@Column(name = "producto_id")
	private Long productoId;
	@Column(name = "combo_id")
	private Long comboId;

	public FacturaProductoId() {
		super();
	}

	public FacturaProductoId(Long facturaId, Long productoId, Long comboId) {
		this.comboId = comboId;
		this.facturaId = facturaId;
		this.productoId = productoId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		FacturaProductoId that = (FacturaProductoId) o;
		return Objects.equals(facturaId, that.facturaId) && Objects.equals(productoId, that.productoId)
				&& Objects.equals(comboId, that.comboId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(facturaId, productoId, comboId);
	}

	public Long getFacturaId() {
		return facturaId;
	}

	public void setFacturaId(Long facturaId) {
		this.facturaId = facturaId;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public Long getComboId() {
		return comboId;
	}

	public void setComboId(Long comboId) {
		this.comboId = comboId;
	}

}
