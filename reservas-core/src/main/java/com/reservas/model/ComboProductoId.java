package com.reservas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ComboProductoId implements Serializable {

	@Column(name = "combo_fk")
	private Long comboId;

	@Column(name = "producto_fk")
	private Long productoId;

	public Long getComboId() {
		return comboId;
	}

	public void setComboId(Long comboId) {
		this.comboId = comboId;
	}

	public ComboProductoId(Long comboId, Long productoId) {
		super();
		this.comboId = comboId;
		this.productoId = productoId;
	}

	public ComboProductoId() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ComboProductoId [comboId=" + comboId + ", productoId=" + productoId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comboId == null) ? 0 : comboId.hashCode());
		result = prime * result + ((productoId == null) ? 0 : productoId.hashCode());
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
		ComboProductoId other = (ComboProductoId) obj;
		if (comboId == null) {
			if (other.comboId != null)
				return false;
		} else if (!comboId.equals(other.comboId))
			return false;
		if (productoId == null) {
			if (other.productoId != null)
				return false;
		} else if (!productoId.equals(other.productoId))
			return false;
		return true;
	}

}
