package com.reservas.service;

import java.io.Serializable;
import java.util.List;

import com.reservas.dao.BaseDAO;

public interface BaseService<KeyType extends Serializable, EntityClass> {

	public BaseDAO<KeyType, EntityClass> getDAO();

	public List<EntityClass> getAll();

	public EntityClass save(EntityClass tipo);

	public EntityClass delete(EntityClass tipo);

	public EntityClass get(KeyType id);

	// public EntityClass update(EntityClass tipo);

	public List<EntityClass> findOrderAsc(String atributo);

	public List<EntityClass> findOrderDesc(String atributo);

	public List<EntityClass> findByProperty(String propertyName, String propertyValue);

	public List<EntityClass> findByProperty(String propertyName, long propertyValue);

	public List<EntityClass> findByProperty(String propertyName, Integer propertyValue);
}
