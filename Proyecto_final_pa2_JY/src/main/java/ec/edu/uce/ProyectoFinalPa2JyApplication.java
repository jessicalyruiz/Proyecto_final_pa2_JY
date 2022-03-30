package ec.edu.uce;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ec.edu.uce.repository.modelo.Cliente;
import ec.edu.uce.repository.modelo.Reserva;
import ec.edu.uce.repository.modelo.Vehiculo;
import ec.edu.uce.repository.modelo.VehiculoBuscar;
import ec.edu.uce.service.IClienteService;
import ec.edu.uce.service.IVehiculoService;

@SpringBootApplication
public class ProyectoFinalPa2JyApplication implements CommandLineRunner{

	private static final Logger LOG= LoggerFactory.getLogger(ProyectoFinalPa2JyApplication.class);

	@Autowired
	IVehiculoService vehiculoService;
	
	@Autowired
	private IClienteService clienteService;
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalPa2JyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*
		Vehiculo vehiculo1=new Vehiculo();
		vehiculo1.setAnioFabricacion("2015");
		vehiculo1.setAvaluo(new BigDecimal(12500));
		vehiculo1.setCilindraje(0.6f);
		vehiculo1.setEstado("D");
		//vehiculo1.setFechaDisponibilidad(LocalDateTime.now());
		vehiculo1.setMarca("Toyota");
		vehiculo1.setModelo("Prius");
		vehiculo1.setPaisFabricacion("japon");
		vehiculo1.setPlaca("PCT-1245");
		vehiculo1.setValorPorDia( new BigDecimal(40));
		
		Vehiculo vehiculo2=new Vehiculo();
		vehiculo2.setAnioFabricacion("2017");
		vehiculo2.setAvaluo(new BigDecimal(55000));
		vehiculo2.setCilindraje(1.6f);
		vehiculo2.setEstado("D");
		//vehiculo2.setFechaDisponibilidad(LocalDateTime.now());
		vehiculo2.setMarca("Toyota");
		vehiculo2.setModelo("Prius");
		vehiculo2.setPaisFabricacion("japon");
		vehiculo2.setPlaca("PWQ-7895");
		vehiculo2.setValorPorDia( new BigDecimal(70));
		
		Vehiculo vehiculo3=new Vehiculo();
		vehiculo3.setAnioFabricacion("2019");
		vehiculo3.setAvaluo(new BigDecimal(60000));
		vehiculo3.setCilindraje(1.6f);
		vehiculo3.setEstado("N");
		//vehiculo3.setFechaDisponibilidad(LocalDateTime.of(2022, 04, 10, 0, 0));
		vehiculo3.setMarca("KIA");
		vehiculo3.setModelo("Sportage");
		vehiculo3.setPaisFabricacion("China");
		vehiculo3.setPlaca("PDT-1237");
		vehiculo3.setValorPorDia( new BigDecimal(80));
		
		this.vehiculoService.create(vehiculo1);
		this.vehiculoService.create(vehiculo2);
		this.vehiculoService.create(vehiculo3);
		
		*/
		/*
		List<VehiculoBuscar> vehiculosBuscados=this.vehiculoService.buscarVehiculosDisponibles("Toyota", "Prius");
		vehiculosBuscados.stream().forEach(n->LOG.info(n.toString()));
		*/
		
		
		//registrar cliente desde C
		Cliente cliente=new Cliente();
		cliente.setApellido("yanez");
		cliente.setCedula("2300115066");
		cliente.setFechaNacimiento(LocalDate.of(1992, 06, 24));
		cliente.setGenero("femenino");	
		cliente.setNombre("Jessica");
		//this.clienteService.registrarse(cliente);
		
		Cliente cie=this.clienteService.buscarCedula("2300115066");
		LOG.info(cie.toString());
		List<Reserva> reservasClietne=cie.getReserva();
		for (Reserva reserva : reservasClietne) {
			LOG.info(reserva.toString());
		}
	}

}
