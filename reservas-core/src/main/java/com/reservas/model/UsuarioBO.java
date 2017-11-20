package com.reservas.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
@Entity
@Table(name = "users")
public class UsuarioBO {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@Column(name = "user_apellido")
	private String apellido;

	@Column(name = "user_nombre")
	private String nombre;

	@Column(name = "user_username")
	private String userName;

	@Column(name = "user_password")
	private String password;

	@Column(name = "user_numero_documento")
	private String nroDocumento;

	@Column(name = "user_email")
	private String email;

	@Column(name = "user_fecha_nacimiento")
	private Date fechaNacimiento;

	@Column(name = "user_estado")
	private Boolean estado;

	@Column(name = "user_telefono_particular")
	private String telefonoParticular;

	@Column(name = "user_telefono_laboral")
	private String telefonoLaboral;

	@Column(name = "user_fecha_ultima_modificacion_clave")
	private Date fechaUltModifClave;

	@Column(name = "user_cantidad_intentos_fallidos")
	private Integer intentosFallidos = 0;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_per_id", nullable = true)
	// @JsonFilter("Perfil")
	private PerfilBO perfil;

	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_localidad_fk", nullable = true)
	private LocalidadBO localidad;
	
	
	@Transient
	private static final Integer MESES_VIGENCIA_CLAVE = 6;

	@Transient
	public static final Integer CANTIDAD_MAXIMA_INTENTOS_FALLIDOS = 5;

	public UsuarioBO() {
	}

	public UsuarioBO(Long idUsuario, String apellido, String nombre, String userName, String password,
			String nroDocumento, String email, Date fechaNacimiento, Boolean estado, String telefonoParticular,
			String telefonoLaboral, Date fechaUltModifClave, Integer intentosFallidos, PerfilBO perfil, LocalidadBO localidad) {
		super();
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
		this.localidad = localidad;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
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

	public Boolean estaBloqueado() {
		return (this.getEstado() != null) && !this.getEstado();
	}

	public void resetIntentoFallidos() {
		this.setIntentosFallidos(0);
		this.setEstado(true);
	}

	public Boolean quedanIntentos() {
		return this.getIntentosFallidos() < CANTIDAD_MAXIMA_INTENTOS_FALLIDOS;
	}

	public void incrementarIntentosFallidos() {
		this.setIntentosFallidos(this.getIntentosFallidos() + 1);
		this.setEstado(this.getIntentosFallidos() < CANTIDAD_MAXIMA_INTENTOS_FALLIDOS);
	}

	public Boolean tieneClaveVigente() {
		Calendar cal = GregorianCalendar.getInstance();
		Date hoy = cal.getTime();
		cal.add(Calendar.MONTH, -MESES_VIGENCIA_CLAVE);
		Date mesesVigencia = cal.getTime();

		if (this.getFechaUltModifClave() == null) {
			this.setFechaUltModifClave(hoy);
		}

		return this.getFechaUltModifClave().after(mesesVigencia);
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

	public PerfilBO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilBO perfil) {
		this.perfil = perfil;
	}

	public LocalidadBO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadBO localidad) {
		this.localidad = localidad;
	}

	
}
