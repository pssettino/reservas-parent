package com.reservas.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.dao.PersistentTokenDAO;
import com.reservas.dao.UsuarioDAO;
import com.reservas.model.PersistentTokenBO;
import com.reservas.model.UsuarioBO;

/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
@Service("userServices")
public class UserSecurityServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserSecurityServices.class);

	@Autowired
	private UsuarioDAO userDao;

	// @Autowired
	private PersistentTokenDAO persistentTokenDao;

	@Transactional(readOnly = true)
	public UsuarioBO getUserByLogin(final String login) {
		// return userDao.getByLogin(login);
		// TODO: es para que compile nomas
		return userDao.getAll().get(0);
	}

	@Transactional
	public void saveTokenAndFlush(PersistentTokenBO token) {
		LOGGER.debug("storing token {} for user {}", token.getTokenValue(), token.getUser().getIdUsuario().toString());
		persistentTokenDao.saveAndFlush(token);
	}

	@Transactional
	public void deleteToken(PersistentTokenBO token) {
		LOGGER.debug("deleting token {} for user {}", token.getTokenValue(), token.getUser().getIdUsuario().toString());
		persistentTokenDao.delete(token);
	}

	@Transactional(readOnly = true)
	public PersistentTokenBO findPersistentTokenBySerie(String presentedSeries) {
		LOGGER.debug("looking for token of serie {}", presentedSeries);
		return persistentTokenDao.findOne(presentedSeries);
	}
}
