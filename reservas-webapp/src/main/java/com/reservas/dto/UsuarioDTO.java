package com.reservas.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
public class UsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7287181341914042660L;

	private Long idUsuario;

	private String apellido;

	private String nombre;

	private String userName;

	private String password;

	private String nroDocumento;

	private String email;

	private String fechaNacimiento;

	private Boolean estado;

	private String telefonoParticular;

	private String telefonoLaboral;

	private Date fechaUltModifClave;

	private Integer intentosFallidos = 0;

	private String perfil;

	private String localidad;

	private String provincia;
	
	public UsuarioDTO() {

	}

	public UsuarioDTO(Long idUsuario, String apellido, String nombre, String userName, String password,
			String nroDocumento, String email, String fechaNacimiento, Boolean estado, String telefonoParticular,
			String telefonoLaboral, Date fechaUltModifClave, Integer intentosFallidos, String perfil, String provincia) {
		this.idUsuario = idUsuario;
		this.apellido = apellido;
		this.nombre = nombre;
		this.userName = userName;
		this.password = password;
		this.nroDocumento = nroDocumento;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.estado = estado;
		this.telefonoParticular = telefonoParticular;
		this.telefonoLaboral = telefonoLaboral;
		this.fechaUltModifClave = fechaUltModifClave;
		this.intentosFallidos = intentosFallidos;
		this.perfil = perfil;
		this.provincia = provincia;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getTelefonoParticular() {
		return telefonoParticular;
	}

	public void setTelefonoParticular(String telefonoParticular) {
		this.telefonoParticular = telefonoParticular;
	}

	public String getTelefonoLaboral() {
		return telefonoLaboral;
	}

	public void setTelefonoLaboral(String telefonoLaboral) {
		this.telefonoLaboral = telefonoLaboral;
	}

	public Date getFechaUltModifClave() {
		return fechaUltModifClave;
	}

	public void setFechaUltModifClave(Date fechaUltModifClave) {
		this.fechaUltModifClave = fechaUltModifClave;
	}

	public Integer getIntentosFallidos() {
		return intentosFallidos;
	}

	public void setIntentosFallidos(Integer intentosFallidos) {
		this.intentosFallidos = intentosFallidos;
	}

	public String getPerfil() {
		return perfil;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	 
}
