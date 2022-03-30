package ec.edu.uce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.repository.modelo.Cliente;

import ec.edu.uce.repository.modelo.Vehiculo;
import ec.edu.uce.repository.modelo.VehiculoBuscar;
import ec.edu.uce.service.IClienteService;
import ec.edu.uce.service.IVehiculoService;

@Controller
@RequestMapping("/clientes/")
public class ClienteController {

	@Autowired
	private IVehiculoService vehiculoService;
	
	@Autowired
	private IClienteService clienteService;
	
	//primer metodo para buscar vehiculos disponibles
	@GetMapping("buscarVehiculos")
	public String obtenerPaginaIngresoMarcaModelo(Vehiculo vehiculo) {
		return "buscarVehiculo";
		
	}
	//segundo metodo para buscar vehiculos disponibles
	@GetMapping("buscar/disponibles")
	public String mostrarVehiculosDisponibles(Vehiculo vehiculo,  Model modelom) {
		List<VehiculoBuscar> vehiculosBuscados=this.vehiculoService.buscarVehiculosDisponibles(vehiculo.getMarca(), vehiculo.getModelo());
		modelom.addAttribute("vehiculos", vehiculosBuscados);
		return "listaVehiculosDisponibles";
		
	}
	
	
	//primer metodo para registrarse
	@GetMapping("clienteNuevo")
	public String obtenerPaginaIngresoDatos(Cliente cliente) { 
		return "clienteNuevo";
		
	}
	//segundo metodo para registrarse
	@PostMapping("registrar")
	public String registrarCliente(Cliente cliente, BindingResult resut, Model modelo, RedirectAttributes redirec){
		this.clienteService.registrarse(cliente);
		//redirec.addFlashAttribute("mensaje", "Estudiante Guardado");
		return "clienteRegistradoMSj";
	}
	
	
	
}
