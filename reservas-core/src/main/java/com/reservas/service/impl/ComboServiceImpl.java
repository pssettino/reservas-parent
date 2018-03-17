package com.reservas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.dao.BaseDAO;
import com.reservas.dao.ComboDAO;
import com.reservas.model.ComboBO;
import com.reservas.service.ComboService;

@Service
@Transactional
public class ComboServiceImpl extends BaseServiceImpl<Long, ComboBO> implements ComboService {

	@Autowired
	private ComboDAO dao;

	public BaseDAO<Long, ComboBO> getDAO() {
		return this.dao;
	}

}
