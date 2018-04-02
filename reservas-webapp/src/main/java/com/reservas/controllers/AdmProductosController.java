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

import com.reservas.dto.ProductoDTO;
import com.reservas.exeptions.BusinessExeption;
import com.reservas.model.CategoriaBO;
import com.reservas.model.ProductoBO;
import com.reservas.service.CategoriaService;
import com.reservas.service.ProductoService;
import com.reservas.utils.JsonResponse;

@Controller
public class AdmProductosController extends AbstractBaseController {

	@Autowired
	ProductoService productoService;

	@Autowired
	CategoriaService categoriaService;

	@RequestMapping(value = "/admProductos", method = RequestMethod.GET)
	public ModelAndView admProductos(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admProductos");

			List<ProductoBO> productos = productoService.getAll();
			List<ProductoDTO> dtos = new ArrayList<ProductoDTO>();
			for (ProductoBO productoBO : productos) {
				ProductoDTO productoDTO = productoBOToDTO(productoBO);
				dtos.add(productoDTO);
			}

			modelo.addObject("categorias", categoriaService.getAll());
			modelo.addObject("productos", dtos);
			modelo.addObject("productoDTO", new ProductoDTO());
			return modelo;

		} catch (Exception e) {
			return null;
		}

	}

	private ModelAndView bindAdmProductos(ModelAndView modelo) throws BusinessExeption {
		List<ProductoBO> productos = productoService.getAll();
		List<ProductoDTO> dtos = new ArrayList<ProductoDTO>();
		for (ProductoBO productoBO : productos) {
			ProductoDTO productoDTO = productoBOToDTO(productoBO);
			dtos.add(productoDTO);
		}

		modelo.addObject("productos", dtos);
		modelo.addObject("productoDTO", new ProductoDTO());
		return modelo;
	}

	private ProductoDTO productoBOToDTO(ProductoBO productoBO) {
		ProductoDTO producto;
		producto = new ProductoDTO(productoBO.getId(), productoBO.getCategoria().getDescripcion(),
				productoBO.getPrecio(), productoBO.getNombre(), productoBO.getCantidad());
		return producto;
	}

	@RequestMapping(value = "/saveProducto", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public JsonResponse saveProducto(@RequestBody ProductoDTO productoDTO, BindingResult result, ModelMap model) {
		try {

			ModelAndView modelo = new ModelAndView("admProductos");
			ProductoBO productoBO;
			CategoriaBO categoria = categoriaService.findByProperty("id", productoDTO.getCategoriaId()).get(0);
			if (productoDTO.getId() == null) {

				productoBO = new ProductoBO(productoDTO.getId(), categoria, productoDTO.getPrecio(),
						productoDTO.getNombre(), productoDTO.getCantidad(), null);

			} else {
				productoBO = productoService.findByProperty("id", productoDTO.getId()).get(0);
				productoBO.setCategoria(categoria);
				productoBO.setPrecio(productoDTO.getPrecio());
				productoBO.setNombre(productoDTO.getNombre());
				productoBO.setCantidad(productoDTO.getCantidad());
			}

			productoService.save(productoBO);

			List<ProductoBO> productos = productoService.getAll();
			List<ProductoDTO> dtos = new ArrayList<ProductoDTO>();
			for (ProductoBO item : productos) {
				ProductoDTO dto = productoBOToDTO(item);
				dtos.add(dto);
			}

			modelo.addObject("productos", dtos);
			modelo.addObject("productoDTO", new ProductoDTO());

			JsonResponse response = new JsonResponse<>();
			response.setRows(dtos);
			response.setSuccess(true);
			return response;
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/editarProducto", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse<ProductoDTO> editarProducto(@RequestParam(value = "id") Integer id, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admProductos");
			ProductoDTO productoDTO = productoBOToDTO(productoService.findByProperty("id", new Long(id)).get(0));
			modelo.addObject("productoDTO", productoDTO);

			JsonResponse<ProductoDTO> jsonResponse = new JsonResponse<ProductoDTO>(productoDTO);
			return jsonResponse;
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/eliminarProducto", method = RequestMethod.POST)
	public ModelAndView eliminarProducto(@RequestParam(value = "id") Integer id, ModelMap model) {
		try {
			ModelAndView modelo = new ModelAndView("admProductos");
			ProductoBO productoBO = productoService.findByProperty("id", new Long(id)).get(0);
			productoService.delete(productoBO);
			return bindAdmProductos(modelo);
		} catch (Exception e) {
			return null;
		}
	}

}
