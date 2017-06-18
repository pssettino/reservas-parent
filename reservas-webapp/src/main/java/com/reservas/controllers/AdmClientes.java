package com.reservas.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.reservas.service.ClienteService;

@Controller
public class AdmClientes extends AbstractBaseController {
	@Autowired
	private ClienteService clienteSevice;

	@RequestMapping(value = "/admClientes", method = RequestMethod.GET)
	public ModelAndView admClientes(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admClientes");
			modelo.addObject("clientes", clienteSevice.getAll());
			return modelo;
		} catch (Exception e) {
			return null;
		}

	}

}
