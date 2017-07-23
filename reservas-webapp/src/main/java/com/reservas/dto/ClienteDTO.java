package com.reservas.dto;

import java.io.Serializable;
import java.util.Date;

public class ClienteDTO implements Serializable {

	private Integer id;

	private String apellido;

	private String nombre;

	private Long dni;

	private String telefono;

	private String email;

	private Date fechaAlta;

	private String localidad;

	private Boolean eliminado;

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Integer id, String apellido, String nombre, Long dni, String telefono, String email,
			Date fechaAlta, String localidad, Boolean eliminado) {
		super();
		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
		this.fechaAlta = fechaAlta;
		this.localidad = localidad;
		this.eliminado = eliminado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
