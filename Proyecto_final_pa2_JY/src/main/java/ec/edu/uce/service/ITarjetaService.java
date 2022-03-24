package ec.edu.uce.service;

import ec.edu.uce.repository.modelo.Tarjeta;

public interface ITarjetaService {


	public void create(Tarjeta tarjeta);
	public Tarjeta read(Integer id); 
	public void update(Tarjeta tarjeta); 
	public void delete(Integer id); 
}
