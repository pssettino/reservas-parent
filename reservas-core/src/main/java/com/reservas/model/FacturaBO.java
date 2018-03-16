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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
@Entity
@Table(name = "factura")
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FacturaBO {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_usuario", nullable = true)
	private UsuarioBO usuario;

	@NaturalId
	private Date fecha;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_medio_pago", nullable = true)
	private MedioPagoBO medioPago;

	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FacturaDetalleBO> facturaDetalle = new ArrayList<FacturaDetalleBO>();

	public FacturaBO(Long id, UsuarioBO usuario, Date fecha, MedioPagoBO medioPago,
			List<FacturaDetalleBO> facturaDetalle) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.fecha = fecha;
		this.medioPago = medioPago;
		this.facturaDetalle = facturaDetalle;
	}

	public void addProducto(ProductoBO producto) {
		FacturaDetalleBO fd = new FacturaDetalleBO(this, producto);
		facturaDetalle.add(fd);
		producto.getFacturaDetalle().add(fd);
	}

	public void removeProducto(ProductoBO producto) {
		for (Iterator<FacturaDetalleBO> iterator = facturaDetalle.iterator(); iterator.hasNext();) {
			FacturaDetalleBO fd = iterator.next();

			if (fd.getFactura().equals(this) && fd.getProducto().equals(producto)) {
				iterator.remove();
				fd.getProducto().getFacturaDetalle().remove(fd);
				fd.setFactura(null);
				fd.setProducto(null);
			}
		}
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((facturaDetalle == null) ? 0 : facturaDetalle.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((medioPago == null) ? 0 : medioPago.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
