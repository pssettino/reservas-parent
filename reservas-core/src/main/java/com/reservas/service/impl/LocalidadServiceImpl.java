package com.reservas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservas.dao.BaseDAO;
import com.reservas.dao.LocalidadDAO;
import com.reservas.model.LocalidadBO;
import com.reservas.model.ProvinciaBO;
import com.reservas.service.LocalidadService;

@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Long, LocalidadBO> implements LocalidadService {

	@Autowired
	private LocalidadDAO dao;

	public BaseDAO<Long, LocalidadBO> getDAO() {
		return this.dao;
	}

	public List<LocalidadBO> findByProvincia(ProvinciaBO prov) {
		return this.dao.findByProperty("provincia.id", prov.getId());
	}

}
