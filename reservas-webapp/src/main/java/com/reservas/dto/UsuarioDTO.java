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

	public UsuarioDTO() {

	}

	public UsuarioDTO(Long idUsuario, String apellido, String nombre, String userName, String password,
			String nroDocumento, String email, String fechaNacimiento, Boolean estado, String telefonoParticular,
			String telefonoLaboral, Date fechaUltModifClave, Integer intentosFallidos, String perfil) {
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

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((fechaUltModifClave == null) ? 0 : fechaUltModifClave.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result + ((intentosFallidos == null) ? 0 : intentosFallidos.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((nroDocumento == null) ? 0 : nroDocumento.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + ((telefonoLaboral == null) ? 0 : telefonoLaboral.hashCode());
		result = prime * result + ((telefonoParticular == null) ? 0 : telefonoParticular.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		UsuarioDTO other = (UsuarioDTO) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (fechaUltModifClave == null) {
			if (other.fechaUltModifClave != null)
				return false;
		} else if (!fechaUltModifClave.equals(other.fechaUltModifClave))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (intentosFallidos == null) {
			if (other.intentosFallidos != null)
				return false;
		} else if (!intentosFallidos.equals(other.intentosFallidos))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (nroDocumento == null) {
			if (other.nroDocumento != null)
				return false;
		} else if (!nroDocumento.equals(other.nroDocumento))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		if (telefonoLaboral == null) {
			if (other.telefonoLaboral != null)
				return false;
		} else if (!telefonoLaboral.equals(other.telefonoLaboral))
			return false;
		if (telefonoParticular == null) {
			if (other.telefonoParticular != null)
				return false;
		} else if (!telefonoParticular.equals(other.telefonoParticular))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [idUsuario=" + idUsuario + ", apellido=" + apellido + ", nombre=" + nombre + ", userName="
				+ userName + ", password=" + password + ", nroDocumento=" + nroDocumento + ", email=" + email
				+ ", fechaNacimiento=" + fechaNacimiento + ", estado=" + estado + ", telefonoParticular="
				+ telefonoParticular + ", telefonoLaboral=" + telefonoLaboral + ", fechaUltModifClave="
				+ fechaUltModifClave + ", intentosFallidos=" + intentosFallidos + ", perfil=" + perfil + "]";
	}

}
