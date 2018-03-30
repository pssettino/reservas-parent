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

@Entity
@Table(name = "combo_producto")
public class ComboProductoBO implements Serializable {

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idComboProducto;

	@EmbeddedId
	private ComboProductoId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("comboId")
	private ComboBO combo;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("productoId")
	private ProductoBO producto;

	public Long getIdComboProducto() {
		return idComboProducto;
	}

	public void setIdComboProducto(Long idComboProducto) {
		this.idComboProducto = idComboProducto;
	}

	public ComboProductoId getId() {
		return id;
	}

	public void setId(ComboProductoId id) {
		this.id = id;
	}

	public ComboBO getCombo() {
		return combo;
	}

	public void setCombo(ComboBO combo) {
		this.combo = combo;
	}

	public ProductoBO getProducto() {
		return producto;
	}

	public void setProducto(ProductoBO producto) {
		this.producto = producto;
	}

	public ComboProductoBO(Long idComboProducto, ComboProductoId id, ComboBO combo, ProductoBO producto) {
		super();
		this.idComboProducto = idComboProducto;
		this.id = id;
		this.combo = combo;
		this.producto = producto;
	}

	public ComboProductoBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComboProductoBO(ComboBO combo, ProductoBO producto) {
		this.producto = producto;
		this.combo = combo;
		this.id = new ComboProductoId(combo.getId(), producto.getId());
	}

	@Override
	public String toString() {
		return "ComboProductoBO [idComboProducto=" + idComboProducto + ", id=" + id + ", combo=" + combo + ", producto="
				+ producto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((combo == null) ? 0 : combo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idComboProducto == null) ? 0 : idComboProducto.hashCode());
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
		ComboProductoBO other = (ComboProductoBO) obj;
		if (combo == null) {
			if (other.combo != null)
				return false;
		} else if (!combo.equals(other.combo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idComboProducto == null) {
			if (other.idComboProducto != null)
				return false;
		} else if (!idComboProducto.equals(other.idComboProducto))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}

}
