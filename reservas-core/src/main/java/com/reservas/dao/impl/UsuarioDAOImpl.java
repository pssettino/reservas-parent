package com.reservas.dao.impl;

import org.springframework.stereotype.Repository;

import com.reservas.dao.UsuarioDAO;
import com.reservas.model.UsuarioBO;

@Repository
public class UsuarioDAOImpl extends BaseDAOImpl<Integer, UsuarioBO> implements UsuarioDAO {

}
