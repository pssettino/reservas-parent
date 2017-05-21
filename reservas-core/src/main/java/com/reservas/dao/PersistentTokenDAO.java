package com.reservas.dao;

import com.reservas.model.PersistentTokenBO;

public interface PersistentTokenDAO {

	public void saveAndFlush(PersistentTokenBO token);

	public void delete(PersistentTokenBO token);

	public PersistentTokenBO findOne(String presentedSeries);

}
