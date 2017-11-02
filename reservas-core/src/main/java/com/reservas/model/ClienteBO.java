package com.reservas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
@Entity
@Table(name = "cliente")
public class ClienteBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4542473738866179570L;

	@Id
	@GeneratedValue
	@Column(name = "cli_id")
	private Integer id;

	@Column(name = "cli_nombre")
	private String nombre;

	@Column(name = "cli_apellido")
	private String apellido;

	@Column(name = "cli_dni")
	private Long dni;

	@Column(name = "cli_telefono")
	private String telefono;

	@Column(name = "cli_fecha_alta")
	private Date fechaAlta;

	@Column(name = "cli_eliminado")
	private Boolean eliminado;

	@Column(name = "cli_email")
	private String email;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cli_localidad_fk", nullable = true)
	private LocalidadBO localidad;

	public ClienteBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteBO(Integer id, String nombre, String apellido, Long dni, String telefono, Date fechaAlta,
			Boolean eliminado, String email, LocalidadBO localidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.fechaAlta = fechaAlta;
		this.eliminado = eliminado;
		this.email = email;
		this.localidad = localidad;
	}
	public ClienteBO( String nombre, String apellido, Long dni, String telefono, Date fechaAlta,
			Boolean eliminado, String email, LocalidadBO localidad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.fechaAlta = fechaAlta;
		this.eliminado = eliminado;
		this.email = email;
		this.localidad = localidad;
	}

	public ClienteBO(String nombre, String apellido, Long dni, String telefono, Date fechaAlta, Boolean eliminado,
			String email) {
		super();

		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.fechaAlta = fechaAlta;
		this.eliminado = eliminado;
		this.email = email;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalidadBO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadBO localidad) {
		this.localidad = localidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((eliminado == null) ? 0 : eliminado.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fechaAlta == null) ? 0 : fechaAlta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
		ClienteBO other = (ClienteBO) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (eliminado == null) {
			if (other.eliminado != null)
				return false;
		} else if (!eliminado.equals(other.eliminado))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fechaAlta == null) {
			if (other.fechaAlta != null)
				return false;
		} else if (!fechaAlta.equals(other.fechaAlta))
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
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClienteBO [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", telefono="
				+ telefono + ", fechaAlta=" + fechaAlta + ", eliminado=" + eliminado + ", email=" + email
				+ ", localidad=" + localidad + "]";
	}

}
