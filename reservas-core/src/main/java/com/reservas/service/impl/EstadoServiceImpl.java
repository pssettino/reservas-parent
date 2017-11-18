package com.reservas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.dao.BaseDAO;
import com.reservas.dao.EstadoDAO;
import com.reservas.model.EstadoBO;
import com.reservas.service.EstadoService;

@Service
@Transactional
public class EstadoServiceImpl extends BaseServiceImpl<Long, EstadoBO> implements EstadoService {

	@Autowired
	private EstadoDAO dao;

	public BaseDAO<Long, EstadoBO> getDAO() {
		return this.dao;
	}

}
