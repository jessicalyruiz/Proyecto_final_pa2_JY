package ec.edu.uce.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.repository.modelo.Cliente;
import ec.edu.uce.repository.modelo.Cobro;
import ec.edu.uce.repository.modelo.Reserva;
import ec.edu.uce.repository.modelo.Vehiculo;
import ec.edu.uce.repository.modelo.VehiculoBuscar;
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
		modelo.addAttribute("cliente", clienteBuscar); 
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
	
	
	// primer metodo para retirar vehiculo reservado
		@GetMapping("retirarVehiculoBuscar")
		public String obtenerPaginaRetirarVehiculo(Reserva reserva) {
			return "retirarVehiculo";

		}

		// segundo metodo para retirar vehiculo reservado
		@GetMapping("retirarVehiculoReservado")
		public String retirarVehiculo(Model modelo,Reserva reserva) {
			Reserva reservaR=this.vehiculoService.retirarVehiculoReservado(reserva.getNumero());
			modelo.addAttribute("reservaR", reservaR); 
			return "retirarVehiculoMostrar";

		}
		
		
// ********** retirar vehiculo sin reserva
		
		// primer metodo para buscar vehiculos disponibles
		@GetMapping("buscarVehiculos")
		public String obtenerPaginaIngresoMarcaModelo(Vehiculo vehiculo) {
			return "buscarVehiculoDispSR";

		}

		// segundo metodo para buscar vehiculos disponibles
		@GetMapping("buscar/disponibles")
		public String mostrarVehiculosDisponibles(Vehiculo vehiculo, Model modelom) {
			List<VehiculoBuscar> vehiculosBuscados = this.vehiculoService.buscarVehiculosDisponibles(vehiculo.getMarca(),
					vehiculo.getModelo());
			modelom.addAttribute("vehiculos", vehiculosBuscados);
			return "listaVehiculosDisponiblesSR";

		}
		
 
		// primer metodo para reservar vehiculo
		@GetMapping("reservar/buscarVehiculo")
		public String obtenerPaginaBuscarVehiculo(Reserva reserva, Model modelo) {
			modelo.addAttribute("reserva", reserva);
			return "reservarBuscarVehiculoSR";

		}

		// segundo metodo para reservar vehiculo
		@GetMapping("verificarVehiculo")
		public String verificarVehiculo(Model modelo, Reserva reserva, BindingResult result, RedirectAttributes redirect) {
			Vehiculo vehiculoBuscar = this.vehiculoService.buscarPlaca(reserva.getVehiculo().getPlaca());
			BigDecimal valorTotal=this.vehiculoService.calcularPagoVehiculo(reserva.getVehiculo().getPlaca(),
					reserva.getCliente().getCedula(), reserva.getFechaInicio(), reserva.getFechaFin());
			//LOG.info("valor total "+ valorTotal);
			Cobro cobro=new Cobro();
			cobro.setValorTotalPagar(valorTotal);
			reserva.setCobro(cobro);
			modelo.addAttribute("reserva", reserva);
			
			List<Reserva> reservasVehiculo = vehiculoBuscar.getReservas();
			if (reservasVehiculo == null || reservasVehiculo.isEmpty()) {
				String mensaje="Vehiculo Disponible, Valor total a Pagar $"+valorTotal;
				redirect.addFlashAttribute("mensaje", mensaje );
				//LOG.info("Vehiculo Disponible, Valor total a Pagar "+valorTotal.toString());
				return "pagarVehiculoSR";
			} else {
				for (Reserva r : reservasVehiculo) {
					if (this.vehiculoService.fechasSolapadas(reserva.getFechaInicio(), reserva.getFechaFin(),
							r.getFechaInicio(), r.getFechaFin())) {
						
						redirect.addFlashAttribute("mensaje", "Fechas Solapadas, elija otras fechas");
				//		LOG.info("Fechas Solapadas, elija otras fechas"); 
						return "reservarBuscarVehiculoSR"; 
					}
				}
				String mensaje="Vehiculo Disponible, Valor total a Pagar $"+valorTotal;
				redirect.addFlashAttribute("mensaje", mensaje);
				//LOG.info("Vehiculo Disponible, Valor total a Pagar "+valorTotal.toString());
				 return "pagarVehiculoSR";
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
			Reserva reservaR=this.vehiculoService.retirarVehiculoReservado(reservaGenerada.getNumero());
			modelo.addAttribute("reservaR", reservaR); 
			return "retirarVehiculoMostrarSR";


		}
		

}
