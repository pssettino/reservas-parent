package com.reservas.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	@JoinColumn(name = "fk_evento", nullable = true)
	private EventoBO evento;

	@Column(name = "fecha")
	private Date fecha;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_medio_pago", nullable = true)
	private MedioPagoBO medioPago;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_tipo_factura", nullable = true)
	private TipoFacturaBO tipoFactura;

	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FacturaDetalleBO> facturaDetalle = new ArrayList<FacturaDetalleBO>();

	public FacturaBO(Long id, EventoBO evento, Date fecha, MedioPagoBO medioPago, TipoFacturaBO tipoFactura,
			List<FacturaDetalleBO> facturaDetalle) {
		super();
		this.id = id;
		this.evento = evento;
		this.fecha = fecha;
		this.medioPago = medioPago;
		this.tipoFactura = tipoFactura;
		this.facturaDetalle = facturaDetalle;
	}

	public FacturaBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventoBO getEvento() {
		return evento;
	}

	public void setEvento(EventoBO evento) {
		this.evento = evento;
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
		return "FacturaBO [id=" + id + ", evento=" + evento + ", fecha=" + fecha + ", medioPago=" + medioPago + "]";
	}

	public TipoFacturaBO getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(TipoFacturaBO tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public List<FacturaDetalleBO> getFacturaDetalle() {
		return facturaDetalle;
	}

	public void setFacturaDetalle(List<FacturaDetalleBO> facturaDetalle) {
		this.facturaDetalle = facturaDetalle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((evento == null) ? 0 : evento.hashCode());
		result = prime * result + ((facturaDetalle == null) ? 0 : facturaDetalle.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((medioPago == null) ? 0 : medioPago.hashCode());
		result = prime * result + ((tipoFactura == null) ? 0 : tipoFactura.hashCode());
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
		FacturaBO other = (FacturaBO) obj;
		if (evento == null) {
			if (other.evento != null)
				return false;
		} else if (!evento.equals(other.evento))
			return false;
		if (facturaDetalle == null) {
			if (other.facturaDetalle != null)
				return false;
		} else if (!facturaDetalle.equals(other.facturaDetalle))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (medioPago == null) {
			if (other.medioPago != null)
				return false;
		} else if (!medioPago.equals(other.medioPago))
			return false;
		if (tipoFactura == null) {
			if (other.tipoFactura != null)
				return false;
		} else if (!tipoFactura.equals(other.tipoFactura))
			return false;
		return true;
	}
	

}
