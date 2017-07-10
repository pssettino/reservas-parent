package com.reservas.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

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

import com.reservas.dto.EventDataDTO;
import com.reservas.model.EventoBO;
import com.reservas.service.EventoService;

@Controller
public class ScheduleController extends AbstractBaseController {

	Log log = LogFactory.getLog(ScheduleController.class);

	@Autowired
	private EventoService eventoService;

	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	@ResponseBody
	public EventDataDTO saveEvent(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestBody EventDataDTO eventData) throws Exception {

		EventoBO evt = new EventoBO();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		evt.setFechaDesde(sdf.parse(eventData.getStart()));
		evt.setFechaHasta(sdf.parse(eventData.getEnd()));
		evt.setDescripcion(evt.getTitulo());
		evt.setTitulo(eventData.getTitle());
		evt.setTodoDia(false);

		eventoService.save(evt);
		return eventData;
	}

	@RequestMapping(value = "/getAllEvents", method = RequestMethod.POST)
	@ResponseBody
	public List<EventDataDTO> getAllEvents(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		List<EventoBO> eventos = eventoService.getAll();
		List<EventDataDTO> eventosDTO = new ArrayList<EventDataDTO>();
		for (EventoBO eventoBO : eventos) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			String fechaDesde = sdf.format(eventoBO.getFechaDesde());
			String fechaHasta = sdf.format(eventoBO.getFechaHasta());

			eventosDTO.add(new EventDataDTO(eventoBO.getId(), eventoBO.getTitulo(), fechaDesde, fechaHasta));
		}

		return eventosDTO;

	}

	@RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
	@ResponseBody
	public EventDataDTO updateEvent(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestBody EventDataDTO eventData) throws Exception {

		EventoBO evt = eventoService.findByProperty("id", eventData.getId()).get(0);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		evt.setFechaDesde(sdf.parse(eventData.getStart()));
		evt.setFechaHasta(sdf.parse(eventData.getEnd()));
		evt.setDescripcion(evt.getTitulo());
		evt.setTitulo(eventData.getTitle());
		evt.setTodoDia(false);

		eventoService.save(evt);
		return eventData;
	}

}
