package com.reservas.login;

import java.security.SecureRandom;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.CookieTheftException;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.model.PersistentTokenBO;
import com.reservas.model.UsuarioBO;

@Component("customPersistentRememberMeService")
public class CustomPersistentRememberMeService extends AbstractRememberMeServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomPersistentRememberMeService.class);

	private static final int TOKEN_VALIDITY_DAYS = 31;

	private static final int TOKEN_VALIDITY_SECONDS = 30 * 60;

	private static final int DEFAULT_SERIES_LENGTH = 16;

	private static final int DEFAULT_TOKEN_LENGTH = 16;

	private SecureRandom random;

	@Autowired
	private UserSecurityServices userSecurityServices;

	@Autowired
	public CustomPersistentRememberMeService(
			org.springframework.security.core.userdetails.UserDetailsService userDetailsService) {
		super("puestoVendedorKey", userDetailsService);
		random = new SecureRandom();
	}

	@Override
	protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request,
			HttpServletResponse response) {

		PersistentTokenBO token = getPersistentToken(cookieTokens);
		String login = token.getUser().getIdUsuario().toString();
		return getUserDetailsService().loadUserByUsername(login);
	}

	@Override
	protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication successfulAuthentication) {
		String login = successfulAuthentication.getName();

		LOGGER.debug("Creating new persistent login for user {}", login);
		UsuarioBO user = userSecurityServices.getUserByLogin(login);

		PersistentTokenBO token = new PersistentTokenBO();
		token.setSeries(generateSeriesData());
		token.setUser(user);
		token.setTokenValue(generateTokenData());

		token.setIpAddress(request.getRemoteAddr());
		token.setUserAgent(request.getHeader("User-Agent"));
		try {
			userSecurityServices.saveTokenAndFlush(token);
			addCookie(token, request, response);
		} catch (DataAccessException e) {
			LOGGER.error("Failed to save persistent token ", e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String rememberMeCookie = extractRememberMeCookie(request);
		if (rememberMeCookie != null && rememberMeCookie.length() != 0) {
			try {
				String[] cookieTokens = decodeCookie(rememberMeCookie);
				PersistentTokenBO token = getPersistentToken(cookieTokens);
				userSecurityServices.deleteToken(token);
			} catch (InvalidCookieException ice) {
				LOGGER.info("Invalid cookie, no persistent token could be deleted");
			} catch (RememberMeAuthenticationException rmae) {
				LOGGER.debug("No persistent token found, so no token could be deleted");
			}
		}
		super.logout(request, response, authentication);
	}

	private PersistentTokenBO getPersistentToken(String[] cookieTokens) {
		if (cookieTokens.length != 2) {
			throw new InvalidCookieException("Cookie token did not contain " + 2 + " tokens, but contained '"
					+ Arrays.asList(cookieTokens) + "'");
		}

		final String presentedSeries = cookieTokens[0];
		final String presentedToken = cookieTokens[1];

		PersistentTokenBO token = null;
		token = userSecurityServices.findPersistentTokenBySerie(presentedSeries);

		if (token == null) {

			throw new RememberMeAuthenticationException("No persistent token found for series id: " + presentedSeries);
		}

		LOGGER.info("presentedToken={} / tokenValue={}", presentedToken, token.getTokenValue());
		if (!presentedToken.equals(token.getTokenValue())) {

			userSecurityServices.deleteToken(token);
			throw new CookieTheftException(
					"Invalid remember-me token (Series/token) mismatch. Implies previous cookie theft attack.");
		}

		// if
		// (token.getTokenDate().plusDays(TOKEN_VALIDITY_DAYS).isBefore(LocalDate.now()))
		// {
		// userSecurityServices.deleteToken(token);
		// throw new RememberMeAuthenticationException("Remember-me login has
		// expired");
		// }
		return token;
	}

	private String generateSeriesData() {
		byte[] newSeries = new byte[DEFAULT_SERIES_LENGTH];
		random.nextBytes(newSeries);
		return new String(Base64.encode(newSeries));
	}

	private String generateTokenData() {
		byte[] newToken = new byte[DEFAULT_TOKEN_LENGTH];
		random.nextBytes(newToken);
		return new String(Base64.encode(newToken));
	}

	private void addCookie(PersistentTokenBO token, HttpServletRequest request, HttpServletResponse response) {
		setCookie(new String[] { token.getSeries(), token.getTokenValue() }, TOKEN_VALIDITY_SECONDS, request, response);
	}
}
