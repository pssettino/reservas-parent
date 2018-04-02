package com.reservas.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
@Entity
@Table(name = "combo")
public class ComboBO {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "descuento")
	private Double descuento;

	@OneToMany(mappedBy = "combo", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	private List<ComboProductoBO> comboProducto = new ArrayList<ComboProductoBO>();

	public List<ComboProductoBO> getComboProducto() {
		return comboProducto;
	}

	public void setComboProducto(List<ComboProductoBO> comboProducto) {
		this.comboProducto = comboProducto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	@Override
	public String toString() {
		return "ComboBO [id=" + id + ", descripcion=" + descripcion + ", descuento=" + descuento + ", comboProducto="
				+ comboProducto + "]";
	}

	public ComboBO(Long id, String descripcion, Double descuento, List<ComboProductoBO> comboProducto) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.descuento = descuento;
		this.comboProducto = comboProducto;
	}

	public ComboBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comboProducto == null) ? 0 : comboProducto.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((descuento == null) ? 0 : descuento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void addProducto(ProductoBO producto) {
		ComboProductoBO fd = new ComboProductoBO(this, producto);
		comboProducto.add(fd);
		producto.getComboProducto().add(fd);
	}

	public void removeProducto(ProductoBO producto) {
		for (Iterator<ComboProductoBO> iterator = comboProducto.iterator(); iterator.hasNext();) {
			ComboProductoBO fd = iterator.next();

			if (fd.getCombo().equals(this) && fd.getProducto().equals(producto)) {
				iterator.remove();
				fd.getProducto().getComboProducto().remove(fd);
				fd.setCombo(null);
				fd.setProducto(null);
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComboBO other = (ComboBO) obj;
		if (comboProducto == null) {
			if (other.comboProducto != null)
				return false;
		} else if (!comboProducto.equals(other.comboProducto))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (descuento == null) {
			if (other.descuento != null)
				return false;
		} else if (!descuento.equals(other.descuento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
