package com.reservas.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservas.dao.BaseDAO;
import com.reservas.dao.ProductoDAO;
import com.reservas.model.ProductoBO;
import com.reservas.service.ProductoService;

@Service
@Transactional
public class ProductoServiceImpl extends BaseServiceImpl<Long, ProductoBO> implements ProductoService {

	@Autowired
	private ProductoDAO dao;

	public BaseDAO<Long, ProductoBO> getDAO() {
		return this.dao;
	}

}
