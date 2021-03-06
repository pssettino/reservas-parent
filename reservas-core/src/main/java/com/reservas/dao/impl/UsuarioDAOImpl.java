package com.reservas.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.reservas.dao.UsuarioDAO;
import com.reservas.model.UsuarioBO;


/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
@Repository
public class UsuarioDAOImpl extends BaseDAOImpl<Long, UsuarioBO> implements UsuarioDAO {

	public UsuarioBO findByUserNamePassword(String userName, String password) {
		return (UsuarioBO) getSessionFactory().getCurrentSession().createCriteria(UsuarioBO.class)
				.add(Restrictions.eq("userName", userName)).add(Restrictions.eq("password", password)).uniqueResult();
	}

}
