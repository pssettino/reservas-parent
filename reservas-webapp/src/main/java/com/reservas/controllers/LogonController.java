package com.reservas.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.reservas.dao.MailSenderService;
import com.reservas.dto.UsuarioDTO;
import com.reservas.exeptions.BusinessExeption;
import com.reservas.model.UsuarioBO;
import com.reservas.service.PerfilService;
import com.reservas.service.UsuarioService;
import com.reservas.utils.Constantes;
import com.reservas.utils.JsonResponse;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
@Controller
public class LogonController extends AbstractBaseController {

	Log log = LogFactory.getLog(LogonController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PerfilService perfilService;

	@Autowired
	private MailSenderService mailSenderService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		ModelAndView modelView = new ModelAndView("login");
		try {
			UsuarioBO user = usuarioService.findByUserNamePassword(userName, password);
			if (user != null) {
				if (!user.estaBloqueado()) {
					user.resetIntentoFallidos();
					usuarioService.save(user);
					if (user.tieneClaveVigente()) {
						log.info("Clave vigente. Guarda imagenPerfil=" + "imagenPerfil?imagen=" + ". Guarda el usuario="
								+ user.getNombre() + " " + user.getApellido());
						List<GrantedAuthority> permissions = new ArrayList<GrantedAuthority>();
						permissions.add(new SimpleGrantedAuthority(user.getPerfil().getId().toString()));
						Authentication newAuth = new UsernamePasswordAuthenticationToken(user.getNroDocumento(),
								user.getPerfil().getId(), permissions);
						SecurityContextHolder.getContext().setAuthentication(newAuth);
						request.getSession().setAttribute("usuario", user.getNombre() + " " + user.getApellido());
						super.addSessionAttribute(request, Constantes.KEY_ID_USUARIO, user.getIdUsuario());
						super.addSessionAttribute(request, Constantes.KEY_USUARIO_BO, user);
						modelView.setViewName("welcome");
						modelView.addObject("mensaje", "bienvenido");

						return modelView;
					} else {
						modelView.setViewName("login");
						modelView.addObject("errorMessage", "Clave no vigente");
						return modelView;
					}
				} else {
					// TODO: usuario bloqueado
					modelView.setViewName("login");
					modelView.addObject("errorMessage", "Usuario Bloqueado");
					return modelView;
				}
			} else {
				// TODO: No existe el usuario
				List<UsuarioBO> userList = usuarioService.findByProperty("userName", userName);

				if (userList.isEmpty()) {
					user = null;
				} else {
					user = userList.get(0);
				}

				if (user != null) {
					if (user.quedanIntentos()) {
						user.incrementarIntentosFallidos();
						usuarioService.save(user);
						if (user.quedanIntentos()) {
							modelView.setViewName("login");
							modelView.addObject("errorMessage",
									"Contraseña Incorrecta quedan " + user.getIntentosFallidos() + " intentos");
							return modelView;
						} else {
							// TODO: Ya no quedan intentos, esta bloqueado
							if (!Strings.isNullOrEmpty(user.getEmail())) {
								// TODO: mando password al mail
							} else {
								// TODO: se olvido la contraseña
							}

							modelView.setViewName("login");
							modelView.addObject("errorMessage", "Ya no quedan intentos, esta bloqueado");
							return modelView;
						}
					} else {
						// TODO: ya no quedan intentos usuario bloqueado
						modelView.setViewName("login");
						modelView.addObject("errorMessage", "Ya no quedan intentos, esta bloqueado");
						return modelView;
					}
				} else {
					// TODO: el usuario no existe.
					modelView.setViewName("login");
					modelView.addObject("errorMessage", "Usuario inexistente");
					return modelView;
				}
			}
		} catch (BusinessExeption e) {
		}
		return modelView;
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		super.removeSessionAttribute(request, Constantes.KEY_USUARIO_BO);
		super.removeSessionAttribute(request, Constantes.KEY_ID_USUARIO);
		request.getSession().invalidate();
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return new ModelAndView("welcome");
	}

	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse registrar(@RequestBody UsuarioDTO usuarioDTO, BindingResult result, ModelMap model)
			throws ParseException, BusinessExeption {

		UsuarioBO usuarioBO;
		usuarioBO = new UsuarioBO(usuarioDTO.getIdUsuario(), usuarioDTO.getApellido(), usuarioDTO.getNombre(),
				usuarioDTO.getUserName(), usuarioDTO.getPassword(), usuarioDTO.getNroDocumento(), usuarioDTO.getEmail(),
				null, usuarioDTO.getEstado(), usuarioDTO.getTelefonoParticular(), usuarioDTO.getTelefonoLaboral(),
				usuarioDTO.getFechaUltModifClave(), usuarioDTO.getIntentosFallidos(),
				perfilService.findByProperty("id", new Integer(2)).get(0), null);

		String pass = UUID.randomUUID().toString().substring(0, 8);
		usuarioBO.setPassword(pass);

		usuarioService.save(usuarioBO);
		String mensaje = "<h1>Usuario: </h1>" + usuarioBO.getUserName() + " <br/>" + "<h1>Contraseña: </h1>" + pass;
		try {
			mailSenderService.sendMail("Eventos OnLine!", "", mensaje, usuarioBO.getEmail(), "");
		} catch (Exception e) {
		}

		JsonResponse response = new JsonResponse<>();
		response.setSuccess(true);
		return response;

	}

}
