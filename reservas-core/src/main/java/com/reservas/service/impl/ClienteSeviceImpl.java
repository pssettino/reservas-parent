package com.reservas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.dao.BaseDAO;
import com.reservas.dao.ClienteDAO;
import com.reservas.model.ClienteBO;
import com.reservas.service.ClienteService;


/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
@Service
@Transactional
public class ClienteSeviceImpl extends BaseServiceImpl<Long, ClienteBO> implements ClienteService {

	@Autowired
	private ClienteDAO dao;

	public BaseDAO<Long, ClienteBO> getDAO() {
		return this.dao;
	}

}
