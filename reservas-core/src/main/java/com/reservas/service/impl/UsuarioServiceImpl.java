package com.reservas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.dao.BaseDAO;
import com.reservas.dao.UsuarioDAO;
import com.reservas.model.UsuarioBO;
import com.reservas.service.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl extends BaseServiceImpl<Long, UsuarioBO> implements UsuarioService {

	@Autowired
	protected UsuarioDAO dao;

	public BaseDAO<Long, UsuarioBO> getDAO() {
		return this.dao;
	}

}
