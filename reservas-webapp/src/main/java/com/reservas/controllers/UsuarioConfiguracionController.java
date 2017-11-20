package com.reservas.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.reservas.dto.UsuarioDTO;
import com.reservas.exeptions.BusinessExeption;
import com.reservas.model.UsuarioBO;
import com.reservas.service.ProvinciaService;
import com.reservas.service.UsuarioService;
import com.reservas.utils.JsonResponse;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
@Controller
public class UsuarioConfiguracionController extends AbstractBaseController {

	@Autowired
	ProvinciaService provinciaService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/usuarioConfiguracion", method = RequestMethod.GET)
	public ModelAndView reportes(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws BusinessExeption {

		ModelAndView modelView = new ModelAndView("usuarioConfiguracion");

		UsuarioBO usuarioSession = (UsuarioBO) request.getSession().getAttribute("usuarioBO");

		UsuarioBO usuario = usuarioService.findByProperty("idUsuario", usuarioSession.getIdUsuario()).get(0);
		request.getSession().removeAttribute("usuarioBO");
		request.getSession().setAttribute("usuarioBO", usuario);

		modelView.addObject("id", usuario.getIdUsuario());
		modelView.addObject("nroDocumento", usuario.getNroDocumento());
		modelView.addObject("apellido", usuario.getApellido());
		modelView.addObject("nombre", usuario.getNombre());
		modelView.addObject("userName", usuario.getUserName());
		modelView.addObject("telefonoParticular", usuario.getTelefonoParticular());
		modelView.addObject("telefonoLaboral", usuario.getTelefonoLaboral());
		modelView.addObject("email", usuario.getEmail());
		modelView.addObject("fechaNacimiento", usuario.getFechaNacimiento());
		modelView.addObject("provincias", provinciaService.getAll());

		return modelView;
	}

	@RequestMapping(value = "/usuarioConfiguracion/saveUsuario", method = RequestMethod.POST, consumes = {
			"application/json" })
	@ResponseBody
	public JsonResponse saveUsuario(@RequestBody UsuarioDTO usuarioDTO, BindingResult result, ModelMap model) {
		try {

			ModelAndView modelo = new ModelAndView("admUsuarios");
			UsuarioBO usuarioBO;

			usuarioBO = usuarioService.findByProperty("id", usuarioDTO.getIdUsuario()).get(0);
			usuarioBO.setApellido(usuarioDTO.getApellido());
			usuarioBO.setNombre(usuarioDTO.getNombre());
			usuarioBO.setNroDocumento(usuarioDTO.getNroDocumento());
			usuarioBO.setEmail(usuarioDTO.getEmail());
			usuarioBO.setTelefonoLaboral(usuarioDTO.getTelefonoLaboral());
			usuarioBO.setTelefonoParticular(usuarioDTO.getTelefonoParticular());
			usuarioBO.setEstado(usuarioDTO.getEstado());
			usuarioBO.setUserName(usuarioDTO.getUserName());
			// usuarioBO.setPerfil(perfilService.findByProperty("id", new
			// Integer(usuarioDTO.getPerfil())).get(0));

			usuarioService.save(usuarioBO);

			JsonResponse response = new JsonResponse<>();

			response.setSuccess(true);
			return response;
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/usuarioConfiguracion/changePassword", method = RequestMethod.POST, consumes = {
			"application/json" })
	@ResponseBody
	public JsonResponse changePassword(@RequestBody PasswordDTO passwordDTO, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		try {

			UsuarioBO usuarioBO = (UsuarioBO) request.getSession().getAttribute("usuarioBO");

			usuarioBO = usuarioService.findByProperty("id", usuarioBO.getIdUsuario()).get(0);

			String old = usuarioBO.getPassword();
			JsonResponse response = new JsonResponse<>();

			if (!old.equalsIgnoreCase(passwordDTO.getOldPassword().trim())) {
				response.setSuccess(false);
				response.setMessage("La contraseña antigua no es correcta.");
				return response;
			} else if (!passwordDTO.getInputPassword().trim()
					.equalsIgnoreCase(passwordDTO.getInputPassword2().trim())) {
				response.setSuccess(false);
				response.setMessage("Las nuevas contraseñas son distintas.");
				return response;
			} else {
				usuarioBO.setPassword(passwordDTO.getInputPassword());
				usuarioService.save(usuarioBO);
				response.setSuccess(true);
			}

			return response;
		} catch (Exception e) {
			return null;
		}
	}

	private UsuarioDTO usuarioBOToDTO(UsuarioBO usuarioBO) {
		return new UsuarioDTO(usuarioBO.getIdUsuario(), usuarioBO.getApellido(), usuarioBO.getNombre(),
				usuarioBO.getUserName(), usuarioBO.getPassword(), usuarioBO.getNroDocumento(), usuarioBO.getEmail(),
				usuarioBO.getFechaNacimiento().toString(), usuarioBO.getEstado(), usuarioBO.getTelefonoParticular(),
				usuarioBO.getTelefonoLaboral(), usuarioBO.getFechaUltModifClave(), usuarioBO.getIntentosFallidos(),
				usuarioBO.getPerfil().getDescripcion(), usuarioBO.getLocalidad().getProvincia().getDescripcion());
	}

}
