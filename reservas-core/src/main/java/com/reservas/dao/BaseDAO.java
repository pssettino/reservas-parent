package com.reservas.dao;

import java.util.List;

/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
public interface BaseDAO<KeyType, EntityClass> {

	public EntityClass get(KeyType id);

	public List<EntityClass> getAll();

	public EntityClass save(EntityClass entity);

	public EntityClass delete(EntityClass entity);

	public List<EntityClass> findOrderAsc(String atributo);

	public List<EntityClass> findOrderDesc(String atributo);

	public List<EntityClass> findByProperty(String propertyName, String propertyValue);

	public List<EntityClass> findByProperty(String propertyName, long propertyValue);

	public List<EntityClass> findByProperty(String propertyName, Integer propertyValue);
}
