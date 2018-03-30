package com.reservas.controllers;

import java.util.ArrayList;
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

import com.reservas.dto.ComboDTO;
import com.reservas.exeptions.BusinessExeption;
import com.reservas.model.ComboBO;
import com.reservas.model.ComboProductoBO;
import com.reservas.model.ProductoBO;
import com.reservas.service.ComboService;
import com.reservas.service.ProductoService;
import com.reservas.utils.JsonResponse;

@Controller
public class AdmCombosController extends AbstractBaseController {
	@Autowired
	ComboService comboService;

	@Autowired
	ProductoService productoService;

	@RequestMapping(value = "/admCombos", method = RequestMethod.GET)
	public ModelAndView admCombos(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admCombos");

			List<ComboBO> combos = comboService.getAll();
			List<ComboDTO> dtos = new ArrayList<ComboDTO>();
			for (ComboBO comboBO : combos) {
				ComboDTO comboDTO = comboBOToDTO(comboBO);
				dtos.add(comboDTO);
			}

			modelo.addObject("productos", productoService.getAll());
			modelo.addObject("combos", dtos);
			modelo.addObject("comboDTO", new ComboDTO());
			return modelo;

		} catch (Exception e) {
			return null;
		}

	}

	private ModelAndView bindAdmCombos(ModelAndView modelo) throws BusinessExeption {
		List<ComboBO> combos = comboService.getAll();
		List<ComboDTO> dtos = new ArrayList<ComboDTO>();
		for (ComboBO comboBO : combos) {
			ComboDTO comboDTO = comboBOToDTO(comboBO);
			dtos.add(comboDTO);
		}

		modelo.addObject("combos", dtos);
		modelo.addObject("comboDTO", new ComboDTO());
		return modelo;
	}

	private ComboDTO comboBOToDTO(ComboBO comboBO) {
		ComboDTO combo;
		combo = new ComboDTO(comboBO.getId(), comboBO.getDescripcion(), comboBO.getDescuento());
		return combo;
	}

	@RequestMapping(value = "/saveCombo", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public JsonResponse saveCombo(@RequestBody ComboDTO comboDTO, BindingResult result, ModelMap model) {
		try {

			ModelAndView modelo = new ModelAndView("admCombos");
			ComboBO comboBO;

			if (comboDTO.getId() == null) {

				comboBO = new ComboBO(comboDTO.getId(), comboDTO.getDescripcion(), comboDTO.getDescuento(), null);
				
			} else {
				comboBO = comboService.findByProperty("id", comboDTO.getId()).get(0);
				comboBO.setDescripcion(comboDTO.getDescripcion());
				comboBO.setDescuento(comboDTO.getDescuento());
			}
			asignarComboProducto(comboDTO, comboBO);
			comboService.save(comboBO);

			List<ComboBO> combos = comboService.getAll();
			List<ComboDTO> dtos = new ArrayList<ComboDTO>();
			for (ComboBO item : combos) {
				ComboDTO dto = comboBOToDTO(item);
				dtos.add(dto);
			}

			modelo.addObject("combos", dtos);
			modelo.addObject("comboDTO", new ComboDTO());

			JsonResponse response = new JsonResponse<>();
			response.setRows(dtos);
			response.setSuccess(true);
			return response;
		} catch (Exception e) {
			return null;
		}
	}

	private void asignarComboProducto(ComboDTO comboDTO, ComboBO comboBO) throws BusinessExeption {
		List<ComboProductoBO> comboProductoListBO = new ArrayList<ComboProductoBO>();
		for (String prodId : comboDTO.getProductos()) {
			ProductoBO prod = productoService.findByProperty("id", Long.valueOf(prodId)).get(0);
			ComboProductoBO comboProductoBO = new ComboProductoBO(comboBO, prod);
			comboProductoListBO.add(comboProductoBO);
		}
		comboBO.setComboProducto(comboProductoListBO);
	}

	@RequestMapping(value = "/editarCombo", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse<ComboDTO> editarCombo(@RequestParam(value = "id") Integer id, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admCombos");
			ComboDTO comboDTO = comboBOToDTO(comboService.findByProperty("id", new Long(id)).get(0));
			modelo.addObject("comboDTO", comboDTO);

			JsonResponse<ComboDTO> jsonResponse = new JsonResponse<ComboDTO>(comboDTO);
			return jsonResponse;
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/eliminarCombo", method = RequestMethod.POST)
	public ModelAndView eliminarCombo(@RequestParam(value = "id") Integer id, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admCombos");
			ComboBO comboBO = comboService.findByProperty("id", new Long(id)).get(0);
			comboService.delete(comboBO);
			return bindAdmCombos(modelo);
		} catch (Exception e) {
			return null;
		}
	}
}
