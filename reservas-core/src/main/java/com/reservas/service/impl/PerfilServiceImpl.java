package com.reservas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.dao.BaseDAO;
import com.reservas.dao.PerfilDAO;
import com.reservas.model.PerfilBO;
import com.reservas.service.PerfilService;

@Service
@Transactional
public class PerfilServiceImpl extends BaseServiceImpl<Integer, PerfilBO> implements PerfilService {

	@Autowired
	private PerfilDAO dao;

	public BaseDAO<Integer, PerfilBO> getDAO() {
		return this.dao;
	}

}
