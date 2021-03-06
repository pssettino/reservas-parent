package com.reservas.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.reservas.dto.ClienteDTO;
import com.reservas.exeptions.BusinessExeption;
import com.reservas.model.ClienteBO;
import com.reservas.model.LocalidadBO;
import com.reservas.model.ProvinciaBO;
import com.reservas.service.ClienteService;
import com.reservas.service.LocalidadService;
import com.reservas.service.ProvinciaService;
import com.reservas.utils.JsonResponse;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
@Controller
public class AdmClientesController extends AbstractBaseController {
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ProvinciaService proviciaService;

	@Autowired
	private LocalidadService localidadService;

	@RequestMapping(value = "/admClientes", method = RequestMethod.GET)
	public ModelAndView admClientes(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admClientes");

			List<ClienteBO> clientes = clienteService.getAll();
			List<ClienteDTO> dtos = new ArrayList<ClienteDTO>();
			for (ClienteBO clienteBO : clientes) {
				if (!clienteBO.getEliminado()) {
					ClienteDTO clienteDTO = clienteBOToDTO(clienteBO);
					dtos.add(clienteDTO);
				}
			}
			modelo.addObject("provincias", proviciaService.getAll());
			modelo.addObject("clientes", dtos);
			modelo.addObject("clienteDTO", new ClienteDTO());
			return modelo;

		} catch (Exception e) {
			return null;
		}

	}

	private ModelAndView bindAdmClientes(ModelAndView modelo) throws BusinessExeption {
		List<ClienteBO> clientes = clienteService.getAll();
		List<ClienteDTO> dtos = new ArrayList<ClienteDTO>();
		for (ClienteBO clienteBO : clientes) {
			if (!clienteBO.getEliminado()) {
				ClienteDTO clienteDTO = clienteBOToDTO(clienteBO);
				dtos.add(clienteDTO);
			}
		}

		modelo.addObject("clientes", dtos);
		modelo.addObject("clienteDTO", new ClienteDTO());
		return modelo;
	}

	private ClienteDTO clienteBOToDTO(ClienteBO clienteBO) {
		return new ClienteDTO(clienteBO.getId(), clienteBO.getApellido(), clienteBO.getNombre(), clienteBO.getDni(),
				clienteBO.getTelefono(), clienteBO.getEmail(), clienteBO.getFechaAlta(),
				clienteBO.getLocalidad().getDescripcion(), clienteBO.getEliminado(),
				clienteBO.getLocalidad().getProvincia().getDescripcion());
	}

	@RequestMapping(value = "/saveCliente", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public JsonResponse saveCliente(@RequestBody ClienteDTO clienteDTO, BindingResult result, ModelMap model) {
		try {

			ModelAndView modelo = new ModelAndView("admClientes");
			ClienteBO clienteBO;
			if (clienteDTO.getId() == null) {
				LocalidadBO loc = localidadService.findByProperty("id", new Integer(clienteDTO.getLocalidad())).get(0);
				clienteBO = new ClienteBO(clienteDTO.getNombre(), clienteDTO.getApellido(), clienteDTO.getDni(),
						clienteDTO.getTelefono(), new Date(), false, clienteDTO.getEmail(),loc);
			} else {
				clienteBO = clienteService.findByProperty("id", clienteDTO.getId()).get(0);
				clienteBO.setApellido(clienteDTO.getApellido());
				clienteBO.setNombre(clienteDTO.getNombre());
				clienteBO.setDni(clienteDTO.getDni());
				clienteBO.setEmail(clienteDTO.getEmail());
				clienteBO.setTelefono(clienteDTO.getTelefono());
			}

			clienteService.save(clienteBO);

			List<ClienteBO> clientes = clienteService.getAll();
			List<ClienteDTO> dtos = new ArrayList<ClienteDTO>();
			for (ClienteBO item : clientes) {
				if (!clienteBO.getEliminado()) {
					ClienteDTO dto = clienteBOToDTO(item);
					dtos.add(dto);
				}
			}

			modelo.addObject("clientes", dtos);
			modelo.addObject("clienteDTO", new ClienteDTO());
			JsonResponse response = new JsonResponse<>();
			response.setRows(dtos);
			response.setSuccess(true);
			return response;
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/editarCliente", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse<ClienteDTO> editarCliente(@RequestParam(value = "id") Integer id, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admClientes");
			ClienteDTO clienteDTO = clienteBOToDTO(clienteService.findByProperty("id", id).get(0));
			modelo.addObject("clienteDTO", clienteDTO);

			JsonResponse<ClienteDTO> jsonResponse = new JsonResponse<ClienteDTO>(clienteDTO);
			return jsonResponse;
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/eliminarCliente", method = RequestMethod.POST)
	public ModelAndView eliminarCliente(@RequestParam(value = "id") Integer id, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admClientes");
			ClienteBO clienteBO = clienteService.findByProperty("id", id).get(0);
			clienteBO.setEliminado(true);
			clienteService.save(clienteBO);
			return bindAdmClientes(modelo);
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/findByProvinciaId/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<LocalidadBO> findByProvinciaId(@PathVariable(value = "id") Integer id, ModelMap model) {
		try {

			ProvinciaBO prov = proviciaService.findByProperty("id", id).get(0);
			List<LocalidadBO> localidades = localidadService.findByProvincia(prov);

			JsonResponse<LocalidadBO> response = new JsonResponse<LocalidadBO>();
			response.setSuccess(true);
			response.setRows(localidades);
			return response;
		} catch (Exception e) {
			return null;
		}
	}

}
