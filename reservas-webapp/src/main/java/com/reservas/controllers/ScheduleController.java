package com.reservas.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.reservas.dao.MailSenderService;
import com.reservas.dto.EventDataDTO;
import com.reservas.exeptions.BusinessExeption;
import com.reservas.model.EstadoBO;
import com.reservas.model.EventoBO;
import com.reservas.model.UsuarioBO;
import com.reservas.service.EstadoService;
import com.reservas.service.EventoService;

/**
 * @author pablo gabriel settino Fecha: 2017-07-22 Copyright 2017
 */
@Controller
public class ScheduleController extends AbstractBaseController {

	Log log = LogFactory.getLog(ScheduleController.class);

	@Autowired
	private EventoService eventoService;

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private MailSenderService mailSenderService;

	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	@ResponseBody
	public EventDataDTO saveEvent(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestBody EventDataDTO eventData) throws Exception {
		UsuarioBO usuario = (UsuarioBO) request.getSession().getAttribute("usuarioBO");
		EventoBO evtBO = new EventoBO();
		evtBO.setUsuario(usuario);
		EventoBO evt = saveOrUpdateEvent(eventData, evtBO);
		
		eventData.setId(evt.getId());
		return eventData;
	}

	private EventoBO saveOrUpdateEvent(EventDataDTO eventData, EventoBO evt) throws Exception {
		SimpleDateFormat sdf;

		String start = eventData.getStart();

		String end = eventData.getEnd();
		if (evt.getId() == null) {
			end = null;
		}

		if (!Strings.isNullOrEmpty(start) && start.length() > 10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}

		if (!Strings.isNullOrEmpty(start)) {
			evt.setFechaDesde(sdf.parse(start));
		} else {
			evt.setFechaDesde(null);
		}

		if (!Strings.isNullOrEmpty(end)) {
			evt.setFechaHasta(sdf.parse(end));
		} else {
			evt.setFechaHasta(null);
		}

		if (eventData.getAllDay() != null && eventData.getAllDay()) {
			evt.setFechaHasta(null);
		} else {
			if (!Strings.isNullOrEmpty(end)) {
				evt.setFechaHasta(sdf.parse(end));
			} else {
				evt.setFechaHasta(null);
			}
		}
		evt.setDescripcion(evt.getTitulo());
		evt.setTitulo(eventData.getTitle());

		evt.setTodoDia(Strings.isNullOrEmpty(end));

		EstadoBO estado = estadoService.findByProperty("id", eventData.getEstadoId() + 1).get(0);
		evt.setEstado(estado);
		String mensaje="";
		
		if(evt.getId()==null) {
			mensaje= "Felicitaciones usted ha reservado el evento: "+evt.getTitulo()+", en unos instantes uno de nuestros representantes se comunicará con usted.";
		}else {
			mensaje= "Felicitaciones usted ha modificado el evento: "+evt.getTitulo()+", en unos instantes uno de nuestros representantes se comunicará con usted.";
		}
		
		EventoBO evtBO = eventoService.save(evt);
		mailSenderService.sendMail(evt.getUsuario().getEmail(), "Eventos OnLine!", mensaje);
		return evtBO;
	}

	@RequestMapping(value = "/getAllEvents", method = RequestMethod.POST)
	@ResponseBody
	public List<EventDataDTO> getAllEvents(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		List<EventoBO> eventos = eventoService.getAll();
		List<EventDataDTO> eventosDTO = new ArrayList<EventDataDTO>();
		for (EventoBO eventoBO : eventos) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			Date desde = eventoBO.getFechaDesde();
			Date hasta = eventoBO.getFechaHasta();

			String fechaDesde = null;
			String fechaHasta = null;
			if (desde != null) {
				fechaDesde = sdf.format(desde).replace(" ", "T");
			}
			if (hasta != null) {
				fechaHasta = sdf.format(hasta).replace(" ", "T");
			}

			eventosDTO.add(new EventDataDTO(eventoBO.getId(), eventoBO.getTitulo(), fechaDesde, fechaHasta,
					eventoBO.getTodoDia(), eventoBO.getEstado().getId()));
		}

		return eventosDTO;

	}

	@RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
	@ResponseBody
	public EventDataDTO updateEvent(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestBody EventDataDTO eventData) throws Exception {

		UsuarioBO usuario = (UsuarioBO) request.getSession().getAttribute("usuarioBO");
		EventoBO evt = eventoService.findByProperty("id", eventData.getId()).get(0);
		if (evt.getUsuario().getIdUsuario() == usuario.getIdUsuario()) {
			saveOrUpdateEvent(eventData, evt);
		}else if(usuario.getPerfil().getId()==1) {
			saveOrUpdateEvent(eventData, evt);
		}
		return eventData;
	}

}
