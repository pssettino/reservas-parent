package com.reservas.model;

import java.io.Serializable;
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
@Table(name = "evento")
public class EventoBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7883380201772946616L;

	@Id
	@Column(name = "eve_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "eve_fecha_desde")
	private Date fechaDesde;

	@Column(name = "eve_fecha_hasta")
	private Date fechaHasta;

	@Column(name = "eve_todo_dia")
	private Boolean todoDia;

	@Column(name = "eve_titulo")
	private String titulo;

	@Column(name = "eve_descripcion")
	private String descripcion;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "evt_estado_fk")
	private EstadoBO estado;
	

	public EstadoBO getEstado() {
		return estado;
	}

	public void setEstado(EstadoBO estado) {
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Boolean getTodoDia() {
		return todoDia;
	}

	public void setTodoDia(Boolean todoDia) {
		this.todoDia = todoDia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaDesde == null) ? 0 : fechaDesde.hashCode());
		result = prime * result + ((fechaHasta == null) ? 0 : fechaHasta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((todoDia == null) ? 0 : todoDia.hashCode());
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
		EventoBO other = (EventoBO) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fechaDesde == null) {
			if (other.fechaDesde != null)
				return false;
		} else if (!fechaDesde.equals(other.fechaDesde))
			return false;
		if (fechaHasta == null) {
			if (other.fechaHasta != null)
				return false;
		} else if (!fechaHasta.equals(other.fechaHasta))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (todoDia == null) {
			if (other.todoDia != null)
				return false;
		} else if (!todoDia.equals(other.todoDia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventoBO [id=" + id + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", todoDia="
				+ todoDia + ", titulo=" + titulo + ", descripcion=" + descripcion + ", estado=" + estado + "]";
	}

}
