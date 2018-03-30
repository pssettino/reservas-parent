package com.reservas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ComboProductoId implements Serializable {

	@Column(name = "combo_fk")
	private Long comboFk;

	@Column(name = "producto_fk")
	private Long productoFk;


	public ComboProductoId() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getComboFk() {
		return comboFk;
	}


	public void setComboFk(Long comboFk) {
		this.comboFk = comboFk;
	}


	public Long getProductoFk() {
		return productoFk;
	}


	public void setProductoFk(Long productoFk) {
		this.productoFk = productoFk;
	}


	public ComboProductoId(Long comboFk, Long productoFk) {
		super();
		this.comboFk = comboFk;
		this.productoFk = productoFk;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comboFk == null) ? 0 : comboFk.hashCode());
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
		ComboProductoId other = (ComboProductoId) obj;
		if (comboFk == null) {
			if (other.comboFk != null)
				return false;
		} else if (!comboFk.equals(other.comboFk))
			return false;
		if (productoFk == null) {
			if (other.productoFk != null)
				return false;
		} else if (!productoFk.equals(other.productoFk))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ComboProductoId [comboFk=" + comboFk + ", productoFk=" + productoFk + "]";
	}

	
	
	
}
