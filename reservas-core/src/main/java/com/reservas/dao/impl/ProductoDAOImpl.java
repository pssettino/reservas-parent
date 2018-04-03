package com.reservas.dao.impl;

import org.springframework.stereotype.Repository;

import com.reservas.dao.ProductoDAO;
import com.reservas.model.ProductoBO;

@Repository
public class ProductoDAOImpl extends BaseDAOImpl<Long, ProductoBO> implements ProductoDAO {

}