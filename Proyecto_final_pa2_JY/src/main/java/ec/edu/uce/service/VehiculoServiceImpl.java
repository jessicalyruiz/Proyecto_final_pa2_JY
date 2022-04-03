package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import ec.edu.uce.repository.IVehiculoRepo;
import ec.edu.uce.repository.modelo.Cliente;
import ec.edu.uce.repository.modelo.Cobro;
import ec.edu.uce.repository.modelo.Reserva;
import ec.edu.uce.repository.modelo.Vehiculo;
import ec.edu.uce.repository.modelo.VehiculoBuscar;

@Service
public class VehiculoServiceImpl implements IVehiculoService{

	//private static final Logger LOG= LoggerFactory.getLogger(VehiculoServiceImpl.class);
	private static Logger LOG=Logger.getLogger(VehiculoServiceImpl.class);
	
	//private Integer numeroReserva=0;
	
	@Autowired
	private IVehiculoRepo vehiculoRepo;

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IReservaService reservaService;
	
	@Autowired
	private ICobroService cobroService;
	
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
/*
	@Override
	@Transactional
	public void reservarVehiculo(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
		// TODO Auto-generated method stub
		Vehiculo vehiculo=this.buscarPlaca(placa);
		Duration duracion= Duration.between(fechaInicio, fechaFinal);
		long dias=duracion.toDays();
		
		Cliente cliente=this.clienteService.buscarCedula(cedula);
		
		if(vehiculo.getEstado().equalsIgnoreCase("D")) {
			LOG.info("vehiculo disponible");
			BigDecimal valorsubTotal=vehiculo.getValorPorDia().multiply(new BigDecimal(dias));
			BigDecimal valorIVA=valorsubTotal.multiply(new BigDecimal(0.12));
			BigDecimal valorTotal=valorsubTotal.add(valorIVA);
			
			List<Reserva> reservasCliente=cliente.getReserva();
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
			
			List<Reserva> reservaVehiculo=vehiculo.getReservas();
			reservaVehiculo.add(reserva);
			vehiculo.setReservas(reservaVehiculo);
			vehiculo.setFechaDisponibilidad(fechaFinal);
			vehiculo.setEstado("N");
			this.vehiculoRepo.update(vehiculo);
			
			reservasCliente.add(reserva);
			cliente.setReserva(reservasCliente);
			this.clienteService.update(cliente);
		}else {
			LOG.info("Vehiculo no disponible en esa fecha");
			LOG.info("fecha disponibilidad"+vehiculo.getFechaDisponibilidad());
			
		}
		
		
	}
	*/
	
		@Override
	public BigDecimal calcularPagoVehiculo(String placa, String cedula,LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
			Vehiculo vehiculo=this.buscarPlaca(placa);
			Duration duracion= Duration.between(fechaInicio, fechaFinal);
			long dias=duracion.toDays();
			
			Cliente cliente=this.clienteService.buscarCedula(cedula);
			
			
				LOG.info("vehiculo disponible");
				BigDecimal valorsubTotal=vehiculo.getValorPorDia().multiply(new BigDecimal(dias));
				BigDecimal valorIVA=valorsubTotal.multiply(new BigDecimal(0.12));
				BigDecimal valorTotal=valorsubTotal.add(valorIVA);
		return valorTotal;
	}
	
	@Override
	@Transactional
	public Reserva reservarVehiculo(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFinal, String numeroTarjeta) {
		// TODO Auto-generated method stub
		Vehiculo vehiculo=this.buscarPlaca(placa);
		Duration duracion= Duration.between(fechaInicio, fechaFinal);
		long dias=duracion.toDays();
		
		Cliente cliente=this.clienteService.buscarCedula(cedula);
		
		
			LOG.info("vehiculo disponible");
			BigDecimal valorsubTotal=vehiculo.getValorPorDia().multiply(new BigDecimal(dias));
			BigDecimal valorIVA=valorsubTotal.multiply(new BigDecimal(0.12));
			BigDecimal valorTotal=valorsubTotal.add(valorIVA);
			
			List<Reserva> reservasCliente=cliente.getReserva();
			Reserva reserva=new Reserva();
			reserva.setCliente(cliente);
			reserva.setEstado("G"); //generada
			reserva.setFechaFin(fechaFinal);
			reserva.setFechaInicio(fechaInicio);
			reserva.setVehiculo(vehiculo);
					
			
			List<Reserva> reservaVehiculo=vehiculo.getReservas();
			reservaVehiculo.add(reserva);
			vehiculo.setReservas(reservaVehiculo);
			vehiculo.setFechaDisponibilidad(fechaFinal);
			//vehiculo.setEstado("N");
			this.vehiculoRepo.update(vehiculo);
			
			reservasCliente.add(reserva);
			cliente.setReserva(reservasCliente);
			this.clienteService.update(cliente);
			
			Cobro cobro=new Cobro();
			cobro.setFecha(LocalDateTime.now());
			cobro.setReserva(reserva);
			cobro.setTarjeta(numeroTarjeta);
			cobro.setValorIVA(valorIVA);
			cobro.setValorSubtotal(valorsubTotal);
			cobro.setValorTotalPagar(valorTotal);
			
			reserva.setCobro(cobro);
			this.reservaService.create(reserva);
			reserva.setNumero("000"+reserva.getId());
			this.reservaService.update(reserva);
			return reserva;
	}
	
	@Override
	public boolean fechasSolapadas(LocalDateTime fechaInicio, LocalDateTime fechaFin,LocalDateTime fechaInicio2, LocalDateTime fechaFin2) {
		if(fechaInicio.isEqual(fechaInicio2)) {
			return true;
		}else if(fechaInicio2.isAfter(fechaInicio)&&fechaInicio2.isBefore(fechaFin)) {
			return true;
		}else if(fechaFin2.isAfter(fechaInicio)&&fechaFin2.isBefore(fechaFin)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Reserva retirarVehiculoReservado(String numeroReserva) {
		// TODO Auto-generated method stub
		Reserva reserva=this.reservaService.buscarNumero(numeroReserva);
		reserva.setEstado("E"); //ejecutada
		this.reservaService.update(reserva);
		
		Vehiculo vehiculo=reserva.getVehiculo();
		vehiculo.setEstado("ND"); //no disponible
		this.update(vehiculo);
		return reserva;
	}
}
