package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import ec.edu.uce.repository.IVehiculoRepo;
import ec.edu.uce.repository.modelo.Cliente;
import ec.edu.uce.repository.modelo.ClienteVIP;
import ec.edu.uce.repository.modelo.Cobro;
import ec.edu.uce.repository.modelo.Reserva;
import ec.edu.uce.repository.modelo.Vehiculo;
import ec.edu.uce.repository.modelo.VehiculoBuscar;
import ec.edu.uce.repository.modelo.VehiculoVIP;

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

	@Override
	public List<Vehiculo> buscarTodosVehiculos() {
		// TODO Auto-generated method stub
		return this.vehiculoRepo.buscarTodosVehiculos();
	}

	@Override
	public List<VehiculoVIP> reporteVEhiculosVIP(Integer mes, Integer anio) {
		//List<Vehiculo> listaVehiculos=this.buscarTodosVehiculos();
		LocalDateTime fechaInicio=LocalDateTime.of(anio, mes, 1, 0, 0);
		LocalDateTime fechaFin;
		if (mes==2) {
			fechaFin=LocalDateTime.of(anio, mes, 28, 0, 0);
		}else if(mes==4||mes==6||mes==9||mes==11) {
			fechaFin=LocalDateTime.of(anio, mes, 30, 0, 0);
		}else {
			fechaFin=LocalDateTime.of(anio, mes, 31, 0, 0);
		}
		List<Vehiculo> vehiculosEnFechaIngresada=this.buscarFechas(fechaInicio, fechaFin);
		List<VehiculoVIP>vehiculosVIP=new ArrayList<>();
		
		
		
		
		for (Vehiculo v : vehiculosEnFechaIngresada) {
			BigDecimal valorSubtotal = new BigDecimal(0);
			BigDecimal valorTotal= new BigDecimal(0);
			List<Reserva> reservasVehiculo=this.buscarReservasVehiculoFecha(v, fechaInicio, fechaFin);
			System.out.println("***** vehiculo"+v);
			
			for (Reserva r : reservasVehiculo) {
				System.out.println("*****"+r);
				System.out.println("**** subtotal"+valorSubtotal);
				valorSubtotal=valorSubtotal.add(r.getCobro().getValorSubtotal());
				valorTotal=valorTotal.add(r.getCobro().getValorTotalPagar());
				System.out.println("**** subtotal:"+valorSubtotal);
				System.out.println("**** total"+valorTotal);
			}
			VehiculoVIP vehiculoVIP=new VehiculoVIP(v.getPlaca(), v.getModelo(), v.getMarca(), v.getAnioFabricacion(), v.getValorPorDia(), valorSubtotal, valorTotal);
			vehiculosVIP.add(vehiculoVIP);
		}
		List<VehiculoVIP> listaOrdenada=vehiculosVIP.parallelStream().sorted(Comparator.comparing(VehiculoVIP::getValorTotal)).collect(Collectors.toList());
		//ordeno mayor a menor
		listaOrdenada=listaOrdenada.stream().sorted(Collections.reverseOrder(Comparator.comparing(VehiculoVIP::getValorTotal))).collect(Collectors.toList());
		return listaOrdenada;
		
	}

	public List<Reserva> buscarReservasVehiculoFecha(Vehiculo vechiculo, LocalDateTime fechaInicio, LocalDateTime FechaFin){
		List<Reserva> reservasVehiculo=vechiculo.getReservas();
		List<Reserva> reservasEnLasFechas=new ArrayList<>();
		for (Reserva r : reservasVehiculo) {
			if(r.getFechaInicio().isAfter(fechaInicio)&& r.getFechaFin().isBefore(FechaFin)) {
				reservasEnLasFechas.add(r);
			}
		}
		
		return reservasEnLasFechas;
		
	}
	
	
	public List<Vehiculo> buscarFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		// TODO Auto-generated method stub
		return this.vehiculoRepo.buscarFechas(fechaInicio, fechaFin);
	}
}
