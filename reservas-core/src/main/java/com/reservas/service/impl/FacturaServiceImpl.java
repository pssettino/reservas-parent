package com.reservas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.dao.BaseDAO;
import com.reservas.dao.FacturaDAO;
import com.reservas.model.FacturaBO;
import com.reservas.service.FacturaService;

@Service
@Transactional
public class FacturaServiceImpl extends BaseServiceImpl<Long, FacturaBO> implements FacturaService {

	@Autowired
	private FacturaDAO dao;

	public BaseDAO<Long, FacturaBO> getDAO() {
		return this.dao;
	}

}
