package ec.edu.uce.controller;

import java.math.BigDecimal;
import java.security.cert.CollectionCertStoreParameters;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.ProyectoFinalPa2JyApplication;
import ec.edu.uce.repository.modelo.Cliente;
import ec.edu.uce.repository.modelo.Cobro;
import ec.edu.uce.repository.modelo.Reserva;
import ec.edu.uce.repository.modelo.Vehiculo;
import ec.edu.uce.repository.modelo.VehiculoBuscar;
import ec.edu.uce.service.IClienteService;
import ec.edu.uce.service.IVehiculoService;

@Controller
@RequestMapping("/clientes/")
public class ClienteController {

	private static final Logger LOG=Logger.getLogger(ProyectoFinalPa2JyApplication.class);
	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IClienteService clienteService;

	// primer metodo para buscar vehiculos disponibles
	@GetMapping("buscarVehiculos")
	public String obtenerPaginaIngresoMarcaModelo(Vehiculo vehiculo) {
		return "buscarVehiculoDisp";

	}

	// segundo metodo para buscar vehiculos disponibles
	@GetMapping("buscar/disponibles")
	public String mostrarVehiculosDisponibles(Vehiculo vehiculo, Model modelom) {
		List<VehiculoBuscar> vehiculosBuscados = this.vehiculoService.buscarVehiculosDisponibles(vehiculo.getMarca(),
				vehiculo.getModelo());
		modelom.addAttribute("vehiculos", vehiculosBuscados);
		return "listaVehiculosDisponibles";

	}

	// primer metodo para reservar vehiculo
	@GetMapping("reservar/buscarVehiculo")
	public String obtenerPaginaBuscarVehiculo(Reserva reserva, Model modelo) {
		modelo.addAttribute("reserva", reserva);
		return "reservarBuscarVehiculo";

	}

	// segundo metodo para reservar vehiculo
	@GetMapping("verificarVehiculo")
	public String verificarVehiculo(Model modelo, Reserva reserva, BindingResult result, RedirectAttributes redirect) {
		Vehiculo vehiculoBuscar = this.vehiculoService.buscarPlaca(reserva.getVehiculo().getPlaca());
		BigDecimal valorTotal=this.vehiculoService.calcularPagoVehiculo(reserva.getVehiculo().getPlaca(),
				reserva.getCliente().getCedula(), reserva.getFechaInicio(), reserva.getFechaFin());
		LOG.info("valor total "+ valorTotal);
		Cobro cobro=new Cobro();
		cobro.setValorTotalPagar(valorTotal);
		reserva.setCobro(cobro);
		modelo.addAttribute("reserva", reserva);
		
		List<Reserva> reservasVehiculo = vehiculoBuscar.getReservas();
		if (reservasVehiculo == null || reservasVehiculo.isEmpty()) {
			//String mensaje="Vehiculo Disponible, Valor total a Pagar $"+valorTotal;
			//redirect.addFlashAttribute("mensaje", mensaje );
			LOG.info("Vehiculo Disponible, Valor total a Pagar "+valorTotal.toString());
			return "pagarVehiculo";
		} else {
			for (Reserva r : reservasVehiculo) {
				if (this.vehiculoService.fechasSolapadas(reserva.getFechaInicio(), reserva.getFechaFin(),
						r.getFechaInicio(), r.getFechaFin())) {
					
					//redirect.addFlashAttribute("mensaje", "Fechas Solapadas, elija otras fechas");
					LOG.info("Fechas Solapadas, elija otras fechas"); 
					return "reservarBuscarVehiculo"; 
					
				}
			}
			//String mensaje="Vehiculo Disponible, Valor total a Pagar $"+valorTotal;
			//redirect.addFlashAttribute("mensaje", mensaje);
			LOG.info("Vehiculo Disponible, Valor total a Pagar "+valorTotal.toString());
			 return "pagarVehiculo";
		}

		

	}

	// tercer metodo para reservar vehiculo
	@PutMapping("reservar/pagarVehiculo")
	public String pagarVehiculo(Model modelo, Reserva reserva) {
		if(reserva.getCobro().getTarjeta().isEmpty()) {
			reserva.getCobro().setTarjeta(null);
		}
		Reserva reservaGenerada = this.vehiculoService.reservarVehiculo(reserva.getVehiculo().getPlaca(),
				reserva.getCliente().getCedula(), reserva.getFechaInicio(), reserva.getFechaFin(),
				reserva.getCobro().getTarjeta());
		modelo.addAttribute("reservaGenerada", reservaGenerada);
		return "mostrarReserva";

	}

	// primer metodo para registrarse
	@GetMapping("clienteNuevo")
	public String obtenerPaginaIngresoDatos(Cliente cliente) {
		return "clienteNuevo";

	}

	// segundo metodo para registrarse
	@PostMapping("registrar")
	public String registrarCliente(Cliente cliente, BindingResult resut, Model modelo, RedirectAttributes redirec) {
		this.clienteService.registrarse(cliente);
		// redirec.addFlashAttribute("mensaje", "Estudiante Guardado");
		return "clienteRegistradoMSj";
	}

}
