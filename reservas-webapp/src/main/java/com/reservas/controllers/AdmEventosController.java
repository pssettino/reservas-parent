package com.reservas.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.reservas.dto.EventoDTO;
import com.reservas.exeptions.BusinessExeption;
import com.reservas.model.EstadoBO;
import com.reservas.model.EventoBO;
import com.reservas.model.UsuarioBO;
import com.reservas.service.ComboService;
import com.reservas.service.EstadoService;
import com.reservas.service.EventoService;
import com.reservas.service.ProductoService;
import com.reservas.service.UsuarioService;
import com.reservas.utils.JsonResponse;

@Controller
public class AdmEventosController extends AbstractBaseController {

	@Autowired
	private EventoService eventoService;

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	ComboService comboService;

	@Autowired
	ProductoService productoService;

	@RequestMapping(value = "/admEventos", method = RequestMethod.GET)
	public ModelAndView admEventos(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admEventos");

			List<EventoBO> eventos = eventoService.getAll();
			List<EventoDTO> dtos = new ArrayList<EventoDTO>();
			for (EventoBO eventoBO : eventos) {
				EventoDTO eventoDTO = eventoBOToDTO(eventoBO);

				dtos.add(eventoDTO);
			}
			modelo.addObject("usuarios", usuarioService.getAll());
			modelo.addObject("productos", productoService.getAll());
			modelo.addObject("combos", comboService.getAll());
			modelo.addObject("eventos", dtos);
			modelo.addObject("eventoDTO", new EventoDTO());
			return modelo;

		} catch (Exception e) {
			return null;
		}

	}

	private ModelAndView bindAdmEventos(ModelAndView modelo) throws BusinessExeption, ParseException {
		List<EventoBO> eventos = eventoService.getAll();
		List<EventoDTO> dtos = new ArrayList<EventoDTO>();
		for (EventoBO eventoBO : eventos) {
			EventoDTO eventoDTO = eventoBOToDTO(eventoBO);
			dtos.add(eventoDTO);
		}

		modelo.addObject("usuarios", usuarioService.getAll());
		modelo.addObject("eventos", dtos);
		modelo.addObject("eventoDTO", new EventoDTO());
		return modelo;
	}

	private EventoDTO eventoBOToDTO(EventoBO eventoBO) throws ParseException {
		EventoDTO evento;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		String fechaDesde = eventoBO.getFechaDesde() != null ? sdf.format(eventoBO.getFechaDesde()) : "";
		String fechaHasta = eventoBO.getFechaHasta() != null ? sdf.format(eventoBO.getFechaHasta()) : "";
		evento = new EventoDTO(eventoBO.getId(), fechaDesde, fechaHasta, eventoBO.getTitulo(),
				eventoBO.getDescripcion(), eventoBO.getUsuario().getIdUsuario().toString(),
				eventoBO.getUsuario().getUserName());
		return evento;
	}

	@RequestMapping(value = "/saveEvento", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public JsonResponse saveEvento(@RequestBody EventoDTO eventoDTO, BindingResult result, ModelMap model) {
		try {

			ModelAndView modelo = new ModelAndView("admEventos");
			EventoBO eventoBO;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
			UsuarioBO usuario = usuarioService.findByProperty("idUsuario", Long.valueOf(eventoDTO.getUsuarioId()))
					.get(0);
			if (eventoDTO.getId() == null) {

				EstadoBO estado = estadoService.findByProperty("id", 1L).get(0);

				Date fechaDesde = sdf.parse(eventoDTO.getFechaDesde());
				Date fechaHasta = sdf.parse(eventoDTO.getFechaHasta());

				eventoBO = new EventoBO(eventoDTO.getId(), fechaDesde, fechaHasta, false, eventoDTO.getTitulo(),
						eventoDTO.getDescripcion(), estado, usuario);

			} else {
				eventoBO = eventoService.findByProperty("id", eventoDTO.getId()).get(0);

				Date fechaDesde = sdf.parse(eventoDTO.getFechaDesde());
				Date fechaHasta = sdf.parse(eventoDTO.getFechaHasta());

				eventoBO.setTitulo(eventoDTO.getTitulo());
				eventoBO.setFechaDesde(fechaDesde);
				eventoBO.setFechaHasta(fechaHasta);
				eventoBO.setDescripcion(eventoDTO.getDescripcion());
				eventoBO.setUsuario(usuario);
			}

			eventoService.save(eventoBO);

			List<EventoBO> eventos = eventoService.getAll();
			List<EventoDTO> dtos = new ArrayList<EventoDTO>();
			for (EventoBO evtBO : eventos) {
				EventoDTO evtDTO = eventoBOToDTO(evtBO);
				dtos.add(evtDTO);
			}

			modelo.addObject("usuarios", usuarioService.getAll());
			modelo.addObject("eventos", dtos);
			modelo.addObject("eventoDTO", new EventoDTO());
			JsonResponse response = new JsonResponse<>();
			response.setRows(dtos);
			response.setSuccess(true);
			return response;
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/editarEvento", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse<EventoDTO> editarEvento(@RequestParam(value = "id") Integer id, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admEventos");
			EventoDTO eventoDTO = eventoBOToDTO(eventoService.findByProperty("id", new Long(id)).get(0));
			modelo.addObject("eventoDTO", eventoDTO);

			JsonResponse<EventoDTO> jsonResponse = new JsonResponse<EventoDTO>(eventoDTO);
			return jsonResponse;
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/eliminarEvento", method = RequestMethod.POST)
	public ModelAndView eliminarEvento(@RequestParam(value = "id") Integer id, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admEventos");
			EventoBO eventoBO = eventoService.findByProperty("id", new Long(id)).get(0);
			// eventoBO.setEstado(estado);
			// usuarioService.save(usuarioBO);
			eventoService.delete(eventoBO);
			return bindAdmEventos(modelo);
		} catch (Exception e) {
			return null;
		}
	}

}
