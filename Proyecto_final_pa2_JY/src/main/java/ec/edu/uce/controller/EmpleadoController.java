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
import ec.edu.uce.service.IClienteService;
import ec.edu.uce.service.IVehiculoService;

@Controller
@RequestMapping("/empleados/")
public class EmpleadoController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IVehiculoService vehiculoService;

	// primer metodo para registrar cliente
	@GetMapping("clienteNuevo")
	public String obtenerPaginaIngresoDatos(Cliente cliente) {
		return "clienteNuevoE";

	}

	// segundo metodo para registrarse
	@PostMapping("registrar")
	public String registrarCliente(Cliente cliente, BindingResult resut, Model modelo, RedirectAttributes redirec) {
		cliente.setTipoRegistro("E");
		this.clienteService.create(cliente);
		// redirec.addFlashAttribute("mensaje", "Estudiante Guardado");
		return "clienteRegistradoMSj";
	}

	// primer metodo para buscar cliente
	@GetMapping("buscarCliente")
	public String obtenerPaginaBuscarCliente(Cliente cliente) {
		return "buscarCliente";

	}

	// segundo metodo para buscar cliente
	@GetMapping("mostrarCliente")
	public String buscarCliente(Model modelo, Cliente cliente) {
		Cliente clienteBuscar = this.clienteService.buscarCedula(cliente.getCedula());
		modelo.addAttribute("cliente", clienteBuscar); // el que esta entre comillas es el nombre para llamarle en la
														// vista html
		return "mostrarCliente";

	}

	// primer metodo ingresar vehiculo
	@GetMapping("vehiculoNuevo")
	public String obtenerPaginaIngresoDatosVehiculo(Vehiculo vehiculo) {
		return "ingresarVehiculo";

	}

	// segundo metodo ingresar vehiculo
	@PostMapping("registrarVehiculo")
	public String registrarVehiculo(Vehiculo vehiculo, BindingResult resut, Model modelo, RedirectAttributes redirec) {
		vehiculo.setEstado("D");
		this.vehiculoService.create(vehiculo);
		return "vehiculoRegistradoMSj";
	}

	// primer metodo para buscar vehiculo
	@GetMapping("buscarVehiculo")
	public String obtenerPaginaBuscarVehiculo(Vehiculo vehiculo) {
		return "buscarVehiculo";

	}

	// segundo metodo para buscar vehiculo
	@GetMapping("mostrarVehiculo")
	public String buscarVehiculo(Model modelo, Vehiculo vehiculo) {
		Vehiculo vehiculoBuscar = this.vehiculoService.buscarPlaca(vehiculo.getPlaca());
		modelo.addAttribute("vehiculo", vehiculoBuscar); 
		return "mostrarVehiculo";

	}

}
