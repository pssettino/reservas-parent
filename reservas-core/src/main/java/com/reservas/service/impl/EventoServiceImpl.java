package com.reservas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.dao.BaseDAO;
import com.reservas.dao.EventoDAO;
import com.reservas.model.EventoBO;
import com.reservas.service.EventoService;


/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
@Service
@Transactional
public class EventoServiceImpl extends BaseServiceImpl<Long, EventoBO> implements EventoService {

	@Autowired
	private EventoDAO dao;

	public BaseDAO<Long, EventoBO> getDAO() {
		return this.dao;
	}

}
