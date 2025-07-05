package com.tienda.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tienda.utils.Alert;
import com.tienda.dtos.InventarioFilter;
import com.tienda.dtos.ResultadoResponse;
import com.tienda.models.Inventario;
import com.tienda.services.InventarioService;
import com.tienda.services.ProductoService;
import com.tienda.services.ProveedorService;
import com.tienda.services.TipoProductoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/inventarios")
public class InventariosController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private TipoProductoService tipoProductoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<Inventario> lstInventario = inventarioService.getAll();
        model.addAttribute("lstInventario", lstInventario);
        return "inventarios/listado";
    }
    
    @GetMapping("/filtrado")
	public String filtrado(@ModelAttribute InventarioFilter filtro, Model model) {
		
		List<Inventario> lstInventario = inventarioService.search(filtro);
		
		model.addAttribute("productos", productoService.getAll());		
		model.addAttribute("proveedores", proveedorService.getAll());
		model.addAttribute("tipoProducto", tipoProductoService.getAll());
		model.addAttribute("filtro", filtro);
		model.addAttribute("lstInventario", lstInventario);
		
		return "inventarios/filtrado";
	}

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
    	Inventario inventario = new Inventario();
        inventario.setFechaIngreso(LocalDate.now());
        
        model.addAttribute("productos", productoService.getAll());
        model.addAttribute("proveedores", proveedorService.getAll()); 
        model.addAttribute("tipoProducto", tipoProductoService.getAll());
        model.addAttribute("inventario", inventario);
        return "inventarios/nuevo";
    }

    @PostMapping("/registrar")
    public String registrar(@Valid @ModelAttribute Inventario inventario, BindingResult bindingResult, Model model,
            RedirectAttributes flash) {
        
    	//Si falta completar algún campo

        if (bindingResult.hasErrors()) {
        	model.addAttribute("proveedores", proveedorService.getAll());
            model.addAttribute("productos", productoService.getAll());
            model.addAttribute("tipoProducto", tipoProductoService.getAll());
            model.addAttribute("alert",Alert.sweetAlertInfo("Falta completar información"));
            return "inventarios/nuevo";
        }
        ResultadoResponse response = inventarioService.create(inventario);
        
        if (!response.success) {
        	model.addAttribute("proveedores", proveedorService.getAll());
            model.addAttribute("productos", productoService.getAll());
            model.addAttribute("tipoProducto", tipoProductoService.getAll());
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "inventarios/nuevo";
        }
        
        String toast = Alert.sweetToast(response.mensaje, "success", 5000);
		flash.addFlashAttribute("toast", toast);
		return "redirect:/inventarios/filtrado";
      
    }

    @GetMapping("/edicion/{id}")
    public String edicion(@PathVariable Integer id, Model model) {
        model.addAttribute("productos", productoService.getAll());
        model.addAttribute("proveedores", proveedorService.getAll());
        model.addAttribute("tipoProducto", tipoProductoService.getAll());

        Inventario inventario = inventarioService.getOne(id);
        model.addAttribute("inventario", inventario);
        return "inventarios/edicion";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Inventario inventario, BindingResult bindingResult, Model model,
    			RedirectAttributes flash) {

        if(bindingResult.hasErrors()) {
        	model.addAttribute("proveedores", proveedorService.getAll());
            model.addAttribute("productos", productoService.getAll());
            model.addAttribute("tipoProducto", tipoProductoService.getAll());
            model.addAttribute("alert", Alert.sweetAlertInfo("Falta completar información"));
            return "inventarios/edicion";
        }
        
        ResultadoResponse response = inventarioService.update(inventario);
        
        if(!response.success) {
        	model.addAttribute("proveedores", proveedorService.getAll());
            model.addAttribute("productos", productoService.getAll());
            model.addAttribute("tipoProducto", tipoProductoService.getAll());
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "inventarios/edicion";
       }
        
        String toast = Alert.sweetToast(response.mensaje, "success", 5000);
		flash.addFlashAttribute("toast", toast);
		return "redirect:/inventarios/filtrado";
    }
}