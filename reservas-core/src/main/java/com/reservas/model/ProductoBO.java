package com.reservas.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
@Entity
@Table(name = "producto")
public class ProductoBO {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_categoria", nullable = true)
	private CategoriaBO categoria;

	@Column(name = "precio")
	private Double precio;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "cantidad")
	private Integer cantidad;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ComboProductoBO> comboProducto = new ArrayList<ComboProductoBO>();

	public ProductoBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductoBO(Long id, CategoriaBO categoria, Double precio, String nombre, Integer cantidad,
			List<ComboProductoBO> comboProducto) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.precio = precio;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.comboProducto = comboProducto;
	}

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

	public CategoriaBO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaBO categoria) {
		this.categoria = categoria;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((comboProducto == null) ? 0 : comboProducto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		result = prime * result + ((cantidad == null) ? 0 : cantidad.hashCode());
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
		ProductoBO other = (ProductoBO) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (comboProducto == null) {
			if (other.comboProducto != null)
				return false;
		} else if (!comboProducto.equals(other.comboProducto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		if (cantidad == null) {
			if (other.cantidad != null)
				return false;
		} else if (!cantidad.equals(other.cantidad))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductoBO [id=" + id + ", categoria=" + categoria + ", precio=" + precio + ", nombre=" + nombre
				+ ", cantidad=" + cantidad + ", comboProducto=" + comboProducto + "]";
	}

}
