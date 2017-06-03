package com.reservas.dao.impl;

import org.springframework.stereotype.Repository;

import com.reservas.dao.EventoDAO;
import com.reservas.model.EventoBO;

@Repository
public class EventoDAOImpl extends BaseDAOImpl<Long, EventoBO> implements EventoDAO{

}
