package com.reservas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservas.dao.BaseDAO;
import com.reservas.dao.TipoFacturaDAO;
import com.reservas.model.TipoFacturaBO;
import com.reservas.service.TipoFacturaService;

@Service
public class TipoFacturaServiceImpl extends BaseServiceImpl<Integer, TipoFacturaBO> implements TipoFacturaService {

	@Autowired
	private TipoFacturaDAO dao;

	public BaseDAO<Integer, TipoFacturaBO> getDAO() {
		return this.dao;
	}

}
