package com.reservas.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FacturaComboId implements Serializable {

	@Column(name = "factura_id")
	private Long facturaId;

	@Column(name = "combo_id")
	private Long comboId;

	public FacturaComboId() {
		super();
	}

	public FacturaComboId(Long facturaId, Long comboId) {
		this.comboId = comboId;
		this.facturaId = facturaId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		FacturaComboId that = (FacturaComboId) o;
		return Objects.equals(facturaId, that.facturaId) && Objects.equals(comboId, that.comboId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(facturaId, comboId);
	}

	public Long getFacturaId() {
		return facturaId;
	}

	public void setFacturaId(Long facturaId) {
		this.facturaId = facturaId;
	}

	public Long getComboId() {
		return comboId;
	}

	public void setComboId(Long comboId) {
		this.comboId = comboId;
	}

}
