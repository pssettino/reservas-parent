package com.reservas.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "USERS")
public class UsuarioBO {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@Column(name = "USER_APELLIDO")
	private String apellido;

	@Column(name = "USER_NOMBRE")
	private String nombre;

	@Column(name = "USER_USERNAME")
	private String userName;

	@Column(name = "USER_PASSWORD")
	private String password;

	@Column(name = "USER_NUMERO_DOCUMENTO")
	private String nroDocumento;

	@Column(name = "USER_IDIOMA")
	private String idioma;

	@Column(name = "USER_EMAIL")
	private String email;

	@Column(name = "USER_FECHA_NACIMIENTO")
	private Date fechaNacimiento;

	@Column(name = "USER_ESTADO")
	private Boolean estado;

	@Column(name = "USER_TELEFONO_PARTICULAR")
	private String telefonoParticular;

	@Column(name = "USER_TELEFONO_LABORAL")
	private String telefonoLaboral;

	@Column(name = "USER_FECHA_ULTIMA_MODIFICACION_CLAVE")
	private Date fechaUltModifClave;

	@Column(name = "USER_CANTIDAD_INTENTOS_FALLIDOS")
	private Integer intentosFallidos = 0;

	@Column(name = "USER_CUIT")
	private String CUIT;

	@Transient
	private static final Integer MESES_VIGENCIA_CLAVE = 6;

	@Transient
	public static final Integer CANTIDAD_MAXIMA_INTENTOS_FALLIDOS = 5;

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

	public String getNombreApellido() {
		return getNombre() + " " + getApellido();
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

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
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

	public String getCUIT() {
		return CUIT;
	}

	public void setCUIT(String cuit) {
		this.CUIT = cuit;
	}

	public Date getFechaUltModifClave() {
		return fechaUltModifClave;
	}

	public void setFechaUltModifClave(Date fechaUltModifClave) {
		this.fechaUltModifClave = fechaUltModifClave;
	}

	public Integer getIntentosFallidos() {
		return (this.intentosFallidos != null ? this.intentosFallidos : 0);
	}

	public void setIntentosFallidos(Integer i) {
		this.intentosFallidos = i;
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

}
