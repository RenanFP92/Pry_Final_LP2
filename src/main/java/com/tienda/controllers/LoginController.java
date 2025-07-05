package com.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tienda.dtos.AutenticacionFilter;
import com.tienda.models.Usuario;
import com.tienda.services.AutenticacionService;
import com.tienda.utils.Alert;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private AutenticacionService autenticacionService;
	
	@GetMapping({"/","/login" })
	public String login(Model model) {
		model.addAttribute("filter", new AutenticacionFilter());
		return "login";
	}
	
	@PostMapping("/iniciar-sesion")
	public String iniciarSesion(@ModelAttribute AutenticacionFilter filter, HttpSession session, Model model,
			RedirectAttributes flash) {
		Usuario usuarioVerificado = autenticacionService.autenticacion(filter);
		
		if(usuarioVerificado == null) {
			model.addAttribute("filter", new AutenticacionFilter());
			model.addAttribute("alert", Alert.sweetAlertError("Usario y/o contraseña es incorrecta"));
			return "login";
		}
		
		String nombreCompleto = String.format("%s %s",usuarioVerificado.getNombreUsuario(), usuarioVerificado.getApellidoUsuario());
		session.setAttribute("idUsuario", usuarioVerificado.getIdUsuario());
		session.setAttribute("nombreCompleto", nombreCompleto);
		session.setAttribute("cuenta", usuarioVerificado.getUsuario());
		
		String alert = Alert.sweetImageUrl("Bienvenido a La Bodeguita", nombreCompleto, "/imagenes/saludo.gif");
		flash.addFlashAttribute("alert", alert);
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		if(session.getAttribute("cuenta") == null)
			return "redirect:/login";
		model.addAttribute("nombreCompleto", session.getAttribute("nombreCompleto"));
		return "home";
	}
	
	@GetMapping("/cerrar-sesion")
	public String cerrarSesion(HttpSession session) {
		session.invalidate();
			return "redirect:/login";
	}
}
