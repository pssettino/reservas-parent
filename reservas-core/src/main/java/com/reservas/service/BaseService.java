package com.reservas.service;

import java.io.Serializable;
import java.util.List;

import com.reservas.dao.BaseDAO;
import com.reservas.exeptions.BusinessExeption;

public interface BaseService<KeyType extends Serializable, EntityClass> {

	public BaseDAO<KeyType, EntityClass> getDAO();

	public List<EntityClass> getAll() throws BusinessExeption;;

	public EntityClass save(EntityClass tipo) throws BusinessExeption;;

	public EntityClass delete(EntityClass tipo) throws BusinessExeption;;

	public EntityClass get(KeyType id) throws BusinessExeption;;

	public List<EntityClass> findOrderAsc(String atributo) throws BusinessExeption;;

	public List<EntityClass> findOrderDesc(String atributo) throws BusinessExeption;;

	public List<EntityClass> findByProperty(String propertyName, String propertyValue) throws BusinessExeption;;

	public List<EntityClass> findByProperty(String propertyName, long propertyValue) throws BusinessExeption;;

	public List<EntityClass> findByProperty(String propertyName, Integer propertyValue) throws BusinessExeption;;
}
