package com.reservas.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "combo_producto")
public class ComboProductoBO implements Serializable {

	@EmbeddedId
	private ComboProductoId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("comboId")
	private ComboBO combo;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("productoId")
	private ProductoBO producto;

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
		this.id = id;
		this.combo = combo;
		this.producto = producto;
	}

	public ComboProductoBO(ComboBO combo, ProductoBO producto) {
		super();
		this.combo = combo;
		this.producto = producto;
		this.id = new ComboProductoId(combo.getId(), producto.getId());
	}

	public ComboProductoBO() {
		super();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		ComboProductoBO that = (ComboProductoBO) o;
		return Objects.equals(combo, that.combo) && Objects.equals(producto, that.producto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(combo, producto);
	}

	@Override
	public String toString() {
		return "ComboProductoBO [id=" + id + ", combo=" + combo + ", producto=" + producto + "]";
	}

}
