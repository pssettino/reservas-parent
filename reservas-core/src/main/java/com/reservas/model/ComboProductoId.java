package com.reservas.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ComboProductoId implements Serializable {

	@Column(name = "combo_id")
	private Long comboId;

	@Column(name = "producto_id")
	private Long productoId;

	public ComboProductoId() {
		super();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		ComboProductoId that = (ComboProductoId) o;
		return Objects.equals(comboId, that.comboId) && Objects.equals(productoId, that.productoId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(comboId, productoId);
	}

	public ComboProductoId(Long comboId, Long productoId) {
		super();
		this.comboId = comboId;
		this.productoId = productoId;
	}

	public Long getComboId() {
		return comboId;
	}

	public void setComboId(Long comboId) {
		this.comboId = comboId;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	@Override
	public String toString() {
		return "ComboProductoId [comboId=" + comboId + ", productoId=" + productoId + "]";
	}

}
