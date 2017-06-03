package com.reservas.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.reservas.model.UsuarioBO;
import com.reservas.utils.Constantes;

public abstract class AbstractBaseController {
	Logger logger = Logger.getLogger(this.getClass());

	protected UsuarioBO getUsuarioBO(HttpServletRequest request) {
		return (UsuarioBO) request.getAttribute(Constantes.KEY_USUARIO_BO);
	}

	protected void addSessionAttribute(HttpServletRequest request, String attributeName, Object attributeValue)
			throws Exception {
		if (attributeValue == null) {
			throw new Exception(
					"Add Session Attribute failed. Session Attribute:" + attributeName + " cannot be null.");
		}
		request.getSession().setAttribute(attributeName, attributeValue);
	}

	protected void addSessionAttribute(HttpSession session, String attributeName, Object attributeValue)
			throws Exception {
		if (attributeValue == null) {
			throw new Exception(
					"Add Session Attribute failed. Session Attribute: " + attributeName + " cannot be null.");
		}
		session.setAttribute(attributeName, attributeValue);
	}

	protected void removeSessionAttribute(HttpServletRequest request, String attributeName) {
		request.getSession().setAttribute(attributeName, null);
		request.getSession().removeAttribute(attributeName);
	}

	protected void removeSessionAttribute(HttpSession session, String attributeName) {
		session.removeAttribute(attributeName);
	}

}
