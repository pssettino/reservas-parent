package com.reservas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "salon")
public class SalonBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6978855256060653193L;

	@Id
	@Column(name = "sal_id")
	private Integer id;

	@Column(name = "sal_descipion")
	private String descripcion;

	@Column(name = "sal_capacidad")
	private Integer capacidad;

	@Column(name = "sal_direccion")
	private String direccion;

	@OneToOne
	private LocalidadBO localidad;

	@OneToOne
	private ProvinciaBO provincia;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalidadBO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadBO localidad) {
		this.localidad = localidad;
	}

	public ProvinciaBO getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciaBO provincia) {
		this.provincia = provincia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capacidad == null) ? 0 : capacidad.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
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
		SalonBO other = (SalonBO) obj;
		if (capacidad == null) {
			if (other.capacidad != null)
				return false;
		} else if (!capacidad.equals(other.capacidad))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalonBO [id=" + id + ", descripcion=" + descripcion + ", capacidad=" + capacidad + ", direccion="
				+ direccion + ", localidad=" + localidad + ", provincia=" + provincia + "]";
	}

}
