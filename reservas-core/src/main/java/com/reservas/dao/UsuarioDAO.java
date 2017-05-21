package com.reservas.dao;

import com.reservas.model.UsuarioBO;

public interface UsuarioDAO extends BaseDAO<Long, UsuarioBO> {
	UsuarioBO findByUserNamePassword(String userName, String password);
}
