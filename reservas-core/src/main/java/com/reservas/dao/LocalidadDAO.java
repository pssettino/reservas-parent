package com.reservas.dao;

import java.util.List;

import com.reservas.model.LocalidadBO;
import com.reservas.model.ProvinciaBO;


/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
public interface LocalidadDAO extends BaseDAO<Long, LocalidadBO>{
		public List<LocalidadBO> findLocalidadByProvincia(ProvinciaBO prov);
}
