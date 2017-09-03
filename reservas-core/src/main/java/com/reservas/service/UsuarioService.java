package com.reservas.service;

import com.reservas.exeptions.BusinessExeption;
import com.reservas.model.UsuarioBO;


/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
public interface UsuarioService extends BaseService<Long, UsuarioBO> {
	UsuarioBO findByUserNamePassword(String userName, String password) throws BusinessExeption;
}
