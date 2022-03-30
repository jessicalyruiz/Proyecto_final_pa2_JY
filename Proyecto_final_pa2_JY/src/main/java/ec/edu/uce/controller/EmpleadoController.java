package ec.edu.uce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.service.IClienteService;



@Controller
@RequestMapping("/empleados/")
public interface EmpleadoController {
/*
	@Autowired
	private IClienteService clienteService;
	
	//primer metodo para registrar cliente
		@GetMapping("clienteNuevo")
		public String obtenerPaginaIngresoDatoss(Cliente cliente) { 
			return "clienteNuevo";
			
		}
		//segundo metodo para registrarse
		@PostMapping("registrar")
		public String registrarCliente(Cliente cliente, BindingResult resut, Model modelo, RedirectAttributes redirec){
			this.clienteService.registrarse(cliente);
			//redirec.addFlashAttribute("mensaje", "Estudiante Guardado");
			return "clienteRegistradoMSj";
		}
		*/
		
}
