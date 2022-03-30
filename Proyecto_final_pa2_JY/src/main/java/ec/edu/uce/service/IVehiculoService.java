package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.repository.modelo.Vehiculo;
import ec.edu.uce.repository.modelo.VehiculoBuscar;

public interface IVehiculoService {

	public void create(Vehiculo vehiculo);
	public Vehiculo read(Integer id); 
	public void update(Vehiculo vehiculo); 
	public void delete(Integer id); 
	
	public List<VehiculoBuscar> buscarVehiculosDisponibles(String marca, String modelo);
	public Vehiculo buscarPlaca(String placa);
	public void reservarVehiculo(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFinal);
	
}
