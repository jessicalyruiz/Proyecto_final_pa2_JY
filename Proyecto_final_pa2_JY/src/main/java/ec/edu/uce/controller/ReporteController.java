package ec.edu.uce.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.repository.modelo.ClienteVIP;
import ec.edu.uce.repository.modelo.MesAnio;
import ec.edu.uce.repository.modelo.Reserva;
import ec.edu.uce.repository.modelo.Vehiculo;
import ec.edu.uce.repository.modelo.VehiculoBuscar;
import ec.edu.uce.repository.modelo.VehiculoVIP;
import ec.edu.uce.service.IClienteService;
import ec.edu.uce.service.IReservaService;
import ec.edu.uce.service.IVehiculoService;

@Controller
@RequestMapping("/reportes/")
public class ReporteController {

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IVehiculoService vehiculoService;

	// primer metodo reporteReservas
	@GetMapping("reporteReservas")
	public String obtenerPaginaIngresarDatosReporteReservas(Reserva reserva) {
		return "reporteReservas";

	}

	// segundo metodo reporteReservas
	@GetMapping("reporteReservasMostrar")
	public String reporteReservasMostrar(Model modelo, Reserva reserva) {
		List<Reserva> listaReservas = this.reservaService.reporteReservas(reserva.getFechaInicio(),
				reserva.getFechaFin());
		modelo.addAttribute("listaReservas", listaReservas);
		return "reporteReservasMostrar";

	}

	// reporte Clientes VIP
	@GetMapping("reporteClientesVIP")
	public String reporteClientesVIP(Model modelo, ClienteVIP clienteVIP) {
		List<ClienteVIP> listaClientesVIP = this.clienteService.reporteClientesVIP();
		modelo.addAttribute("listaClientesVIP", listaClientesVIP);
		return "reporteClientesVIP";

	}

	// primer metodo reporteVehiculosVIP
	@GetMapping("reporteVehiculosVIP")
	public String obtenerPaginaIngresarDatosReporteVehiculosVIP(MesAnio mesAnio) {
		//System.out.println("****anio"+mesAnio.getMes());
	//	System.out.println("***mes "+mesAnio.getAnio());
		return "reporteVehiculosVIP";

	}

	// segundo metodo reporteReservas
	@GetMapping("reporteVehiculosVIPMostrar")
	public String reporteVehiculosVIPMostrar(Model modelo, MesAnio mesAnio) {
		//System.out.println("****anio"+mesAnio.getMes());
		//System.out.println("***mes "+mesAnio.getAnio());
		List<VehiculoVIP> listaVechiculoVIP = this.vehiculoService.reporteVEhiculosVIP(mesAnio.getMes(), mesAnio.getAnio());
		modelo.addAttribute("listaVechiculoVIP", listaVechiculoVIP);
		return "reporteVehiculosVIPMostrar";

	}
}
