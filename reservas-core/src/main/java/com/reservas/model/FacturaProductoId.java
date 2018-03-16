package com.reservas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FacturaProductoId implements Serializable {

	@Column(name = "factura_fk")
	private Long facturaId;
	@Column(name = "producto_fk")
	private Long productoId;

	public FacturaProductoId(Long facturaId, Long productoId) {
		super();
		this.facturaId = facturaId;
		this.productoId = productoId;
	}

	public FacturaProductoId() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((facturaId == null) ? 0 : facturaId.hashCode());
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
		FacturaProductoId other = (FacturaProductoId) obj;
		if (facturaId == null) {
			if (other.facturaId != null)
				return false;
		} else if (!facturaId.equals(other.facturaId))
			return false;
		if (productoId == null) {
			if (other.productoId != null)
				return false;
		} else if (!productoId.equals(other.productoId))
			return false;
		return true;
	}

}
