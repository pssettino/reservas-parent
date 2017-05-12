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
@Table(name = "USUARIO")
public class UsuarioBO {

	@Id
	@Column(name = "USU_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;

	@Column(name = "USU_APELLIDO")
	private String apellido;

	@Column(name = "USU_NOMBRE")
	private String nombre;

	@Column(name = "USU_NUMERO_DOCUMENTO")
	private String nroDocumento;

	@Column(name = "USU_IDIOMA")
	private String idioma;

	@Column(name = "USU_EMAIL")
	private String email;

	@Column(name = "USU_FECHA_NACIMIENTO")
	private Date fechaNacimiento;

	@Column(name = "USU_ESTADO")
	private Boolean estado;

	@Column(name = "USU_TELEFONO_PARTICULAR")
	private String telefonoParticular;

	@Column(name = "USU_TELEFONO_LABORAL")
	private String telefonoLaboral;

	@Column(name = "USU_IMAGEN")
	private String imagen;

	@Column(name = "USU_CLAVE")
	private String clave;

	@Column(name = "USU_FECHA_ULTIMA_MODIFICACION_CLAVE")
	private Date fechaUltModifClave;

	@Column(name = "USU_CANT_INTENTOS_FALLIDOS")
	private Integer intentosFallidos = 0;

	@Column(name = "USU_CUIT")
	private String CUIT;

	@Column(name = "USU_CODIGO_MARCA")
	private String codigoMarca;

	@Transient
	private static final Integer MESES_VIGENCIA_CLAVE = 6;

	@Transient
	public static final Integer CANTIDAD_MAXIMA_INTENTOS_FALLIDOS = 5;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

}
