package com.reservas.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogonController {

	Log log = LogFactory.getLog(LogonController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "login";
	}

}
