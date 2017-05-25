package com.reservas.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportesController {
	
	@RequestMapping(value = "/reportes", method = RequestMethod.GET)
	public ModelAndView reportes(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return new ModelAndView("reportes");
	}


}
