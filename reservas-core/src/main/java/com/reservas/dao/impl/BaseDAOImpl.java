package com.reservas.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
public abstract class BaseDAOImpl<KeyType extends Serializable, EntityClass> {

	@Autowired
	private SessionFactory sf;

	public void setSessionFactory(SessionFactory _s) {
		this.sf = _s;
	}

	protected SessionFactory getSessionFactory() {
		return this.sf;
	}

	public List<EntityClass> getAll() {
		Session s = getSessionFactory().getCurrentSession();
		return s.createQuery("from " + this.getClassOfEntityClass().getSimpleName()).list();
	}

	public EntityClass save(EntityClass entity) {
		Session s = getSessionFactory().getCurrentSession();
		s.saveOrUpdate(entity);
		return entity;
	}

	public EntityClass delete(EntityClass entity) {
		Session s = getSessionFactory().getCurrentSession();
		s.delete(entity);
		return entity;
	}

	// public EntityClass update(EntityClass entity) {
	// Session s = getSessionFactory().getCurrentSession();
	// s.update(entity);
	// return entity;
	// }

	public EntityClass get(KeyType id) {
		Session s = getSessionFactory().getCurrentSession();
		return (EntityClass) s.load(this.getClassOfEntityClass(), id);
	}

	/**
	 * Método find en orden ascendente por el atributo pasado por parámetro.
	 */
	public List<EntityClass> findOrderAsc(String atributo) {
		List<EntityClass> marca = getSessionFactory().getCurrentSession().createCriteria(this.getClassOfEntityClass())
				.addOrder(Order.asc(atributo)).list();
		return marca;
	}

	/**
	 * Método find en orden descendente por el atributo pasado por parámetro.
	 */
	public List<EntityClass> findOrderDesc(String atributo) {
		List<EntityClass> marca = getSessionFactory().getCurrentSession().createCriteria(this.getClassOfEntityClass())
				.addOrder(Order.desc(atributo)).list();
		return marca;
	}

	/**
	 * Retorna el .class de EntityClass
	 */
	public Class<EntityClass> getClassOfEntityClass() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		return (Class) pt.getActualTypeArguments()[1];
	}

	/**
	 * Retorna la lista de filtrada por atributo y valor
	 */
	public List<EntityClass> findByProperty(String propertyName, String propertyValue) {
		List<EntityClass> list = getSessionFactory().getCurrentSession().createCriteria(this.getClassOfEntityClass())
				.add(Restrictions.eqOrIsNull(propertyName, propertyValue)).list();
		return list;
	}

	public List<EntityClass> findByProperty(String propertyName, Integer propertyValue) {
		List<EntityClass> list = getSessionFactory().getCurrentSession().createCriteria(this.getClassOfEntityClass())
				.add(Restrictions.eqOrIsNull(propertyName, propertyValue)).list();
		return list;
	}

	public List<EntityClass> findByProperty(String propertyName, long propertyValue) {
		List<EntityClass> list = getSessionFactory().getCurrentSession().createCriteria(this.getClassOfEntityClass())
				.add(Restrictions.eqOrIsNull(propertyName, propertyValue)).list();
		return list;
	}
	// ### Métodos auxiliares para crear criterial ###

	public String nullOrString(String value) {
		return (value == "") ? null : value;
	}

	public Integer nullOrInteger(String value) {
		return (value == "") ? null : Integer.valueOf(value);
	}
}
