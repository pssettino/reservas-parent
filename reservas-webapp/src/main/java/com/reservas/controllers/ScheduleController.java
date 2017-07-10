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
import com.reservas.dto.EventDataDTO;
import com.reservas.exeptions.BusinessExeption;
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

		saveOrUpdateEvent(eventData, new EventoBO());
		return eventData;
	}

	private void saveOrUpdateEvent(EventDataDTO eventData, EventoBO evt) throws ParseException, BusinessExeption {
		SimpleDateFormat sdf;

		String start = eventData.getStart();
		String end = eventData.getEnd();

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
		evt.setDescripcion(evt.getTitulo());
		evt.setTitulo(eventData.getTitle());

		evt.setTodoDia(Strings.isNullOrEmpty(end));

		eventoService.save(evt);
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

			String fechaDesde=null;
			String fechaHasta=null;
			if (desde != null) {
				fechaDesde = sdf.format(desde);
			}
			if (hasta != null) {
				fechaHasta = sdf.format(hasta);
			}

			eventosDTO.add(new EventDataDTO(eventoBO.getId(), eventoBO.getTitulo(), fechaDesde, fechaHasta));
		}

		return eventosDTO;

	}

	@RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
	@ResponseBody
	public EventDataDTO updateEvent(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestBody EventDataDTO eventData) throws Exception {

		EventoBO evt = eventoService.findByProperty("id", eventData.getId()).get(0);
		saveOrUpdateEvent(eventData, evt);
		return eventData;
	}

}
