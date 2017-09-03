package com.reservas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
@Entity
@Table(name = "perfil")
public class PerfilBO implements Comparable<PerfilBO> {

	@Id
	@Column(name = "per_id")
	private Integer id;

	@Column(name = "per_rol")
	private String rol;

	@Column(name = "per_descripcion")
	private String descripcion;

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

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int compareTo(PerfilBO o) {
		return this.getDescripcion().compareTo(o.getDescripcion());
	}

}
