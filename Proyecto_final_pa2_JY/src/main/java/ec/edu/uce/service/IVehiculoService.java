package ec.edu.uce.service;

import ec.edu.uce.repository.modelo.Vehiculo;

public interface IVehiculoService {

	public void create(Vehiculo vehiculo);
	public Vehiculo read(Integer id); 
	public void update(Vehiculo vehiculo); 
	public void delete(Integer id); 
}
