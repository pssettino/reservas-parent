package com.reservas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservas.dao.BaseDAO;
import com.reservas.dao.MedioPagoDAO;
import com.reservas.model.MedioPagoBO;
import com.reservas.service.MedioPagoService;

@Service
public class MedioPagoServiceImpl extends BaseServiceImpl<Integer, MedioPagoBO> implements MedioPagoService {

	@Autowired
	private MedioPagoDAO dao;

	public BaseDAO<Integer, MedioPagoBO> getDAO() {
		return this.dao;
	}

}
