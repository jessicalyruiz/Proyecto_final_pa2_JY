package ec.edu.uce.repository;

import ec.edu.uce.repository.modelo.Tarjeta;

public interface ITarjetaRepo {

	public void create(Tarjeta tarjeta);
	public Tarjeta read(Integer id); 
	public void update(Tarjeta tarjeta); 
	public void delete(Integer id); 
}
