package ec.edu.uce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.repository.modelo.Reserva;
import ec.edu.uce.repository.modelo.Vehiculo;
import ec.edu.uce.repository.modelo.VehiculoBuscar;
import ec.edu.uce.service.IReservaService;
import ec.edu.uce.service.IVehiculoService;

@Controller
@RequestMapping("/reportes/")
public class ReporteController {

	@Autowired
	private IReservaService reservaService;
	
	// primer metodo reporteReservas
	@GetMapping("reporteReservas")
	public String obtenerPaginaIngresarDatosReporteReservas(Reserva reserva) {
		return "reporteReservas";

	}

	// segundo metodo reporteReservas
	@GetMapping("reporteReservasMostrar")
	public String retirarVehiculo(Model modelo, Reserva reserva) {
		List<Reserva> listaReservas=this.reservaService.reporteReservas(reserva.getFechaInicio(), reserva.getFechaFin());
		modelo.addAttribute("listaReservas", listaReservas);
		return "reporteReservasMostrar";

	}

}
