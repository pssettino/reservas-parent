package com.reservas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservas.dao.BaseDAO;
import com.reservas.dao.ProvinciaDAO;
import com.reservas.model.ProvinciaBO;
import com.reservas.service.ProvinciaService;

@Service
public class ProvinciaServiceImpl extends BaseServiceImpl<Long, ProvinciaBO> implements ProvinciaService {

	@Autowired
	private ProvinciaDAO dao;

	public BaseDAO<Long, ProvinciaBO> getDAO() {
		return this.dao;
	}

}
