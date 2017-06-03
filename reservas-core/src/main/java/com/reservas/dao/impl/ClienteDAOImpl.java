package com.reservas.dao.impl;

import org.springframework.stereotype.Repository;

import com.reservas.dao.ClienteDAO;
import com.reservas.model.ClienteBO;

@Repository
public class ClienteDAOImpl extends BaseDAOImpl<Long, ClienteBO> implements ClienteDAO {

}
