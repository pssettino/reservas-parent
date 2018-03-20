package com.reservas.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservas.dao.BaseDAO;
import com.reservas.dao.CategoriaDAO;
import com.reservas.model.CategoriaBO;
import com.reservas.service.CategoriaService;

@Service
@Transactional
public class CategoriaServiceImpl extends BaseServiceImpl<Integer, CategoriaBO> implements CategoriaService {

	@Autowired
	private CategoriaDAO dao;

	public BaseDAO<Integer, CategoriaBO> getDAO() {
		return this.dao;
	}

}
