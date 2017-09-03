package com.reservas.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.reservas.exeptions.BusinessExeption;
import com.reservas.service.BaseService;

/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
public abstract class BaseServiceImpl<KeyType extends Serializable, EntityClass>
		implements BaseService<KeyType, EntityClass> {

	@Transactional
	public List<EntityClass> getAll() throws BusinessExeption {
		return getDAO().getAll();
	}

	@Transactional
	public EntityClass save(EntityClass entity) throws BusinessExeption {
		return getDAO().save(entity);
	}

	@Transactional
	public EntityClass delete(EntityClass entity) throws BusinessExeption {
		return getDAO().delete(entity);
	}

	@Transactional
	public EntityClass get(KeyType id) throws BusinessExeption {
		return getDAO().get(id);
	}

	@Transactional
	public List<EntityClass> findOrderAsc(String atributo) throws BusinessExeption {
		return getDAO().findOrderAsc(atributo);
	}

	@Transactional
	public List<EntityClass> findOrderDesc(String atributo) throws BusinessExeption {
		return getDAO().findOrderDesc(atributo);
	}

	@Transactional
	public List<EntityClass> findByProperty(String propertyName, String propertyValue) throws BusinessExeption {
		return getDAO().findByProperty(propertyName, propertyValue);
	}

	@Transactional
	public List<EntityClass> findByProperty(String propertyName, long propertyValue) throws BusinessExeption {
		return getDAO().findByProperty(propertyName, propertyValue);
	}

	@Transactional
	public List<EntityClass> findByProperty(String propertyName, Integer propertyValue) throws BusinessExeption {
		return getDAO().findByProperty(propertyName, propertyValue);
	}
}
