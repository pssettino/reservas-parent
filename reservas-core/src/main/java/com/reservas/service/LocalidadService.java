package com.reservas.service;

import java.util.List;

import com.reservas.model.LocalidadBO;
import com.reservas.model.ProvinciaBO;

public interface LocalidadService extends BaseService<Long, LocalidadBO> {

	List<LocalidadBO> findByProvincia(ProvinciaBO prov);
	
}
