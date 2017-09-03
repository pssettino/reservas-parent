package com.reservas.dao;

import com.reservas.model.PersistentTokenBO;


/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
public interface PersistentTokenDAO {

	public void saveAndFlush(PersistentTokenBO token);

	public void delete(PersistentTokenBO token);

	public PersistentTokenBO findOne(String presentedSeries);

}
