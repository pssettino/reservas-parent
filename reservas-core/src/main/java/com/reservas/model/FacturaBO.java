package com.reservas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
@Entity
@Table(name = "factura")
public class FacturaBO {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_usuario", nullable = true)
	private UsuarioBO usuario;

	@Column(name = "fecha")
	private Date fecha;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_medio_pago", nullable = true)
	private MedioPagoBO medioPago;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioBO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioBO usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public MedioPagoBO getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(MedioPagoBO medioPago) {
		this.medioPago = medioPago;
	}

	@Override
	public String toString() {
		return "FacturaBO [id=" + id + ", usuario=" + usuario + ", fecha=" + fecha + ", medioPago=" + medioPago + "]";
	}

}
