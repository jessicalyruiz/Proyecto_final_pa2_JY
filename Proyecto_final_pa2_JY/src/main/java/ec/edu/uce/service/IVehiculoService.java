package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.repository.modelo.Reserva;
import ec.edu.uce.repository.modelo.Vehiculo;
import ec.edu.uce.repository.modelo.VehiculoBuscar;

public interface IVehiculoService {

	public void create(Vehiculo vehiculo);
	public Vehiculo read(Integer id); 
	public void update(Vehiculo vehiculo); 
	public void delete(Integer id); 
	
	public List<VehiculoBuscar> buscarVehiculosDisponibles(String marca, String modelo);
	public Vehiculo buscarPlaca(String placa);
	public Reserva reservarVehiculo(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFinal, String numeroTarjeta);
	
	public BigDecimal calcularPagoVehiculo(String placa, String cedula,LocalDateTime fechaInicio, LocalDateTime fechaFinal);
	public boolean fechasSolapadas(LocalDateTime fechaInicio, LocalDateTime fechaFin,LocalDateTime fechaInicio2, LocalDateTime fechaFin2) ;
	public Reserva retirarVehiculoReservado(String numeroReserva);
	
}
