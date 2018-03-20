package com.reservas.dao.impl;

import org.springframework.stereotype.Repository;

import com.reservas.dao.FacturaDAO;
import com.reservas.model.FacturaBO;

@Repository
public class FacturaDAOImpl extends BaseDAOImpl<Long, FacturaBO> implements FacturaDAO {

}
