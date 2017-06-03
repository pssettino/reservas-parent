package com.reservas.dao.impl;

import org.springframework.stereotype.Repository;

import com.reservas.dao.SalonDAO;
import com.reservas.model.SalonBO;

@Repository
public class SalonDAOImpl extends BaseDAOImpl<Long, SalonBO> implements SalonDAO{

}
