package com.reservas.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

import com.reservas.dto.UsuarioDTO;
import com.reservas.exeptions.BusinessExeption;
import com.reservas.model.LocalidadBO;
import com.reservas.model.ProvinciaBO;
import com.reservas.model.UsuarioBO;
import com.reservas.service.LocalidadService;
import com.reservas.service.PerfilService;
import com.reservas.service.ProvinciaService;
import com.reservas.service.UsuarioService;
import com.reservas.utils.JsonResponse;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
@Controller
public class AdmUsuariosController extends AbstractBaseController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PerfilService perfilService;

	@Autowired
	private ProvinciaService proviciaService;

	@Autowired
	private LocalidadService localidadService;

	@RequestMapping(value = "/admUsuarios", method = RequestMethod.GET)
	public ModelAndView admUsuarios(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admUsuarios");

			List<UsuarioBO> usuarios = usuarioService.getAll();
			List<UsuarioDTO> dtos = new ArrayList<UsuarioDTO>();
			for (UsuarioBO usuarioBO : usuarios) {
				UsuarioDTO usuarioDTO = usuarioBOToDTO(usuarioBO);
				dtos.add(usuarioDTO);
			}

			modelo.addObject("usuarios", dtos);
			modelo.addObject("provincias", proviciaService.getAll());
			modelo.addObject("usuarioDTO", new UsuarioDTO());
			return modelo;

		} catch (Exception e) {
			return null;
		}

	}

	private ModelAndView bindAdmUsuarios(ModelAndView modelo) throws BusinessExeption {
		List<UsuarioBO> usuarios = usuarioService.getAll();
		List<UsuarioDTO> dtos = new ArrayList<UsuarioDTO>();
		for (UsuarioBO usuarioBO : usuarios) {
			UsuarioDTO usuarioDTO = usuarioBOToDTO(usuarioBO);
			dtos.add(usuarioDTO);
		}

		modelo.addObject("usuarios", dtos);
		modelo.addObject("usuarioDTO", new UsuarioDTO());
		return modelo;
	}

	private UsuarioDTO usuarioBOToDTO(UsuarioBO usuarioBO) {
		UsuarioDTO usuario;
		if (usuarioBO.getFechaNacimiento() == null && usuarioBO.getLocalidad() == null) {

			usuario = new UsuarioDTO(usuarioBO.getIdUsuario(), usuarioBO.getApellido(), usuarioBO.getNombre(),
					usuarioBO.getUserName(), usuarioBO.getPassword(), usuarioBO.getNroDocumento(), usuarioBO.getEmail(),
					null, usuarioBO.getEstado(), usuarioBO.getTelefonoParticular(), usuarioBO.getTelefonoLaboral(),
					usuarioBO.getFechaUltModifClave(), usuarioBO.getIntentosFallidos(),
					usuarioBO.getPerfil().getDescripcion(), null);
		} else {
			usuario = new UsuarioDTO(usuarioBO.getIdUsuario(), usuarioBO.getApellido(), usuarioBO.getNombre(),
					usuarioBO.getUserName(), usuarioBO.getPassword(), usuarioBO.getNroDocumento(), usuarioBO.getEmail(),
					usuarioBO.getFechaNacimiento().toString(), usuarioBO.getEstado(), usuarioBO.getTelefonoParticular(),
					usuarioBO.getTelefonoLaboral(), usuarioBO.getFechaUltModifClave(), usuarioBO.getIntentosFallidos(),
					usuarioBO.getPerfil().getDescripcion(), usuarioBO.getLocalidad().getProvincia().getDescripcion());

		}
		return usuario;
	}

	@RequestMapping(value = "/saveUsuario", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public JsonResponse saveUsuario(@RequestBody UsuarioDTO usuarioDTO, BindingResult result, ModelMap model) {
		try {

			ModelAndView modelo = new ModelAndView("admUsuarios");
			UsuarioBO usuarioBO;
			if (usuarioDTO.getIdUsuario() == null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
				LocalidadBO loc = localidadService.findByProperty("id", new Integer(usuarioDTO.getLocalidad())).get(0);
				usuarioBO = new UsuarioBO(usuarioDTO.getIdUsuario(), usuarioDTO.getApellido(), usuarioDTO.getNombre(),
						usuarioDTO.getUserName(), usuarioDTO.getPassword(), usuarioDTO.getNroDocumento(),
						usuarioDTO.getEmail(), sdf.parse(usuarioDTO.getFechaNacimiento()), usuarioDTO.getEstado(),
						usuarioDTO.getTelefonoParticular(), usuarioDTO.getTelefonoLaboral(),
						usuarioDTO.getFechaUltModifClave(), usuarioDTO.getIntentosFallidos(),
						perfilService.findByProperty("id", new Integer(usuarioDTO.getPerfil())).get(0), loc);

				usuarioBO.setPassword(UUID.randomUUID().toString().substring(0, 8));
			} else {
				usuarioBO = usuarioService.findByProperty("id", usuarioDTO.getIdUsuario()).get(0);
				usuarioBO.setApellido(usuarioDTO.getApellido());
				usuarioBO.setNombre(usuarioDTO.getNombre());
				usuarioBO.setNroDocumento(usuarioDTO.getNroDocumento());
				usuarioBO.setEmail(usuarioDTO.getEmail());
				usuarioBO.setTelefonoLaboral(usuarioDTO.getTelefonoLaboral());
				usuarioBO.setTelefonoParticular(usuarioDTO.getTelefonoParticular());
				usuarioBO.setEstado(usuarioDTO.getEstado());
				usuarioBO.setUserName(usuarioDTO.getUserName());
				usuarioBO.setPerfil(perfilService.findByProperty("id", new Integer(usuarioDTO.getPerfil())).get(0));
			}

			usuarioService.save(usuarioBO);

			List<UsuarioBO> usuarios = usuarioService.getAll();
			List<UsuarioDTO> dtos = new ArrayList<UsuarioDTO>();
			for (UsuarioBO item : usuarios) {

				UsuarioDTO dto = usuarioBOToDTO(item);
				dtos.add(dto);

			}

			modelo.addObject("usuarios", dtos);
			modelo.addObject("usuarioDTO", new UsuarioDTO());

			JsonResponse response = new JsonResponse<>();
			response.setRows(dtos);
			response.setSuccess(true);
			return response;
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/editarUsuario", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse<UsuarioDTO> editarUsuario(@RequestParam(value = "id") Integer id, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admUsuarios");
			UsuarioDTO usuarioDTO = usuarioBOToDTO(usuarioService.findByProperty("idUsuario", new Long(id)).get(0));
			modelo.addObject("usuarioDTO", usuarioDTO);

			JsonResponse<UsuarioDTO> jsonResponse = new JsonResponse<UsuarioDTO>(usuarioDTO);
			return jsonResponse;
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/eliminarUsuario", method = RequestMethod.POST)
	public ModelAndView eliminarUsuario(@RequestParam(value = "id") Integer id, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admUsuarios");
			UsuarioBO usuarioBO = usuarioService.findByProperty("idUsuario", new Long(id)).get(0);
			usuarioBO.setEstado(false);
			usuarioService.save(usuarioBO);
			return bindAdmUsuarios(modelo);
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/usuario/findByProvinciaId/{id}", method = RequestMethod.GET)
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
