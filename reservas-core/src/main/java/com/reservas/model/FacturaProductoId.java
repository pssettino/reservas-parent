package com.reservas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FacturaProductoId implements Serializable {

	@Column(name = "factura_fk")
	private Long facturaFk;
	@Column(name = "producto_fk")
	private Long productoFk;

	

	public FacturaProductoId() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((facturaFk == null) ? 0 : facturaFk.hashCode());
		result = prime * result + ((productoFk == null) ? 0 : productoFk.hashCode());
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
		if (facturaFk == null) {
			if (other.facturaFk != null)
				return false;
		} else if (!facturaFk.equals(other.facturaFk))
			return false;
		if (productoFk == null) {
			if (other.productoFk != null)
				return false;
		} else if (!productoFk.equals(other.productoFk))
			return false;
		return true;
	}



	public FacturaProductoId(Long facturaFk, Long productoFk) {
		super();
		this.facturaFk = facturaFk;
		this.productoFk = productoFk;
	}



	@Override
	public String toString() {
		return "FacturaProductoId [facturaFk=" + facturaFk + ", productoFk=" + productoFk + "]";
	}

	
}
