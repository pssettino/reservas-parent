package com.reservas.service;

import com.reservas.exeptions.BusinessExeption;
import com.reservas.model.UsuarioBO;

public interface UsuarioService extends BaseService<Long, UsuarioBO> {
	UsuarioBO findByUserNamePassword(String userName, String password) throws BusinessExeption;
}
