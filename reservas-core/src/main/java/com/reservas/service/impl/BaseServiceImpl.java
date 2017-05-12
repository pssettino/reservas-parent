package com.reservas.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.reservas.service.BaseService;

public abstract class BaseServiceImpl<KeyType extends Serializable, EntityClass>
		implements BaseService<KeyType, EntityClass> {

	@Transactional
	public List<EntityClass> getAll() {
		return getDAO().getAll();
	}

	@Transactional
	public EntityClass save(EntityClass entity) {
		return getDAO().save(entity);
	}

	@Transactional
	public EntityClass delete(EntityClass entity) {
		return getDAO().delete(entity);
	}

	@Transactional
	public EntityClass get(KeyType id) {
		return getDAO().get(id);
	}

	@Transactional
	public List<EntityClass> findOrderAsc(String atributo) {
		return getDAO().findOrderAsc(atributo);
	}

	@Transactional
	public List<EntityClass> findOrderDesc(String atributo) {
		return getDAO().findOrderDesc(atributo);
	}

	@Transactional
	public List<EntityClass> findByProperty(String propertyName, String propertyValue) {
		return getDAO().findByProperty(propertyName, propertyValue);
	}

	@Transactional
	public List<EntityClass> findByProperty(String propertyName, long propertyValue) {
		return getDAO().findByProperty(propertyName, propertyValue);
	}

	@Transactional
	public List<EntityClass> findByProperty(String propertyName, Integer propertyValue) {
		return getDAO().findByProperty(propertyName, propertyValue);
	}
}
