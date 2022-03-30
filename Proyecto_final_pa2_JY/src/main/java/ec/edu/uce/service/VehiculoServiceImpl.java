package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.uce.ProyectoFinalPa2JyApplication;
import ec.edu.uce.repository.IVehiculoRepo;
import ec.edu.uce.repository.modelo.Cliente;
import ec.edu.uce.repository.modelo.Reserva;
import ec.edu.uce.repository.modelo.Vehiculo;
import ec.edu.uce.repository.modelo.VehiculoBuscar;

@Service
public class VehiculoServiceImpl implements IVehiculoService{

	private static final Logger LOG= LoggerFactory.getLogger(VehiculoServiceImpl.class);
	private Integer numeroReserva=0;
	
	@Autowired
	private IVehiculoRepo vehiculoRepo;

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IReservaService reservaService;
	
	@Override
	public void create(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.vehiculoRepo.create(vehiculo);
	}

	@Override
	public Vehiculo read(Integer id) {
		// TODO Auto-generated method stub
		return this.vehiculoRepo.read(id);
	}

	@Override
	public void update(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.vehiculoRepo.update(vehiculo);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.vehiculoRepo.delete(id);
	}

	@Override
	public List<VehiculoBuscar> buscarVehiculosDisponibles(String marca, String modelo) {
		// TODO Auto-generated method stub
		return this.vehiculoRepo.buscarVehiculosDisponibles(marca, modelo);
	}

	@Override
	public Vehiculo buscarPlaca(String placa) {
		// TODO Auto-generated method stub
		return this.vehiculoRepo.buscarPlaca(placa);
	}

	@Override
	@Transactional
	public void reservarVehiculo(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
		// TODO Auto-generated method stub
		Vehiculo vehiculo=this.buscarPlaca(placa);
		Duration duracion= Duration.between(fechaInicio, fechaFinal);
		long dias=duracion.toDays();
		
		Cliente cliente=this.clienteService.buscarCedula(cedula);
		
		if(vehiculo.getEstado().equalsIgnoreCase("disponible")) {
			LOG.info("vehiculo disponible");
			BigDecimal valorsubTotal=vehiculo.getValorPorDia().multiply(new BigDecimal(dias));
			BigDecimal valorIVA=valorsubTotal.multiply(new BigDecimal(0.12));
			BigDecimal valorTotal=valorsubTotal.add(valorIVA);
			
			Reserva reserva=new Reserva();
			reserva.setCliente(cliente);
			reserva.setEstado("generada");
			reserva.setFechaFin(fechaFinal);
			reserva.setFechaInicio(fechaInicio);
			reserva.setValorIVA(valorIVA);
			reserva.setValorSubtotal(valorsubTotal);
			reserva.setValorTotalPagar(valorTotal);
			reserva.setVehiculo(vehiculo);
			reserva.setNumero(this.generarNumeroReserva());
			this.reservaService.create(reserva);
			
			vehiculo.setReserva(reserva);
			vehiculo.setFechaDisponibilidad(fechaFinal);
			this.vehiculoRepo.update(vehiculo);
			
			cliente.setReserva(reserva);
			this.clienteService.update(cliente);
		}else {
			LOG.info("Vehiculo no disponible en esa fecha");
			LOG.info("fecha disponibilidad"+vehiculo.getFechaDisponibilidad());
			
		}
		
		
	}
	
	
	
	private String generarNumeroReserva() {
		this.numeroReserva=this.numeroReserva+1;
		return "000"+this.numeroReserva;
		
	}
	
}
