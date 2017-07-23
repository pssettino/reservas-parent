package com.reservas.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.reservas.dto.UsuarioDTO;
import com.reservas.exeptions.BusinessExeption;
import com.reservas.model.PerfilBO;
import com.reservas.model.UsuarioBO;
import com.reservas.service.UsuarioService;
import com.reservas.utils.JsonResponse;

@Controller
public class AdmUsuariosController extends AbstractBaseController {

	@Autowired
	private UsuarioService usuarioService;

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
		return new UsuarioDTO(usuarioBO.getIdUsuario(), usuarioBO.getApellido(), usuarioBO.getNombre(),
				usuarioBO.getUserName(), usuarioBO.getPassword(), usuarioBO.getNroDocumento(), usuarioBO.getIdioma(),
				usuarioBO.getEmail(), usuarioBO.getFechaNacimiento(), usuarioBO.getEstado(),
				usuarioBO.getTelefonoParticular(), usuarioBO.getTelefonoLaboral(), usuarioBO.getFechaUltModifClave(),
				usuarioBO.getIntentosFallidos(), usuarioBO.getCuit(), usuarioBO.getPerfil().getDescripcion());
	}

	@RequestMapping(value = "/saveUsuario", method = RequestMethod.POST)
	public ModelAndView saveUsuario(@Valid @ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO, BindingResult result,
			ModelMap model) {
		try {

			ModelAndView modelo = new ModelAndView("admUsuarios");
			UsuarioBO usuarioBO;
			if (usuarioDTO.getIdUsuario() == null) {
				usuarioBO = new UsuarioBO(usuarioDTO.getIdUsuario(), usuarioDTO.getApellido(), usuarioDTO.getNombre(),
						usuarioDTO.getUserName(), usuarioDTO.getPassword(), usuarioDTO.getNroDocumento(),
						usuarioDTO.getIdioma(), usuarioDTO.getEmail(), usuarioDTO.getFechaNacimiento(),
						usuarioDTO.getEstado(), usuarioDTO.getTelefonoParticular(), usuarioDTO.getTelefonoLaboral(),
						usuarioDTO.getFechaUltModifClave(), usuarioDTO.getIntentosFallidos(), usuarioDTO.getCuit(),
						new PerfilBO());
			} else {
				usuarioBO = usuarioService.findByProperty("id", usuarioDTO.getIdUsuario()).get(0);
				usuarioBO.setApellido(usuarioDTO.getApellido());
				usuarioBO.setNombre(usuarioDTO.getNombre());
				usuarioBO.setNroDocumento(usuarioDTO.getNroDocumento());
				usuarioBO.setEmail(usuarioDTO.getEmail());
				usuarioBO.setTelefonoLaboral(usuarioDTO.getTelefonoLaboral());
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
			return modelo;
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

}
