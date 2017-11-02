package com.reservas.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.reservas.dao.LocalidadDAO;
import com.reservas.model.LocalidadBO;
import com.reservas.model.ProvinciaBO;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
@Repository
@Transactional
public class LocalidadDAOImpl extends BaseDAOImpl<Long, LocalidadBO> implements LocalidadDAO {

	public List<LocalidadBO> findLocalidadByProvincia(ProvinciaBO prov) {
		List<LocalidadBO> loc = (List<LocalidadBO>) getSessionFactory().getCurrentSession()
				.createCriteria(LocalidadBO.class).add(Restrictions.eq("provincia.id", prov.getId())).list();
		return loc;
	}

}
