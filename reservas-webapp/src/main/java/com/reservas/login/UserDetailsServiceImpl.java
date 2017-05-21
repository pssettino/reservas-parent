package com.reservas.login;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.dao.UsuarioDAO;
import com.reservas.model.UsuarioBO;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Inject
	private UsuarioDAO userDao;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String nroDoc, String pass) throws UsernameNotFoundException {
		LOGGER.debug("Authenticating {}", nroDoc);
		UsuarioBO userFromDatabase = userDao.findByUserNamePassword(nroDoc, pass);
		if (userFromDatabase == null) {
			throw new UsernameNotFoundException("User " + nroDoc + " was not found in the database");
		}

		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		String login = nroDoc;
		return new org.springframework.security.core.userdetails.User(login, userFromDatabase.getPassword(),
				grantedAuthorities);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioBO userFromDatabase = userDao.findByProperty("userName", username).get(0);
		if (userFromDatabase == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		return new org.springframework.security.core.userdetails.User(username, userFromDatabase.getPassword(),
				grantedAuthorities);
	}
}