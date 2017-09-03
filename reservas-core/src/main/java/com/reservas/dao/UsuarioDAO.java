package com.reservas.dao;

import com.reservas.model.UsuarioBO;


/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
public interface UsuarioDAO extends BaseDAO<Long, UsuarioBO> {
	UsuarioBO findByUserNamePassword(String userName, String password);
}
