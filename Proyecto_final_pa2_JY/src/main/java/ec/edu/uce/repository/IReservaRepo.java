package ec.edu.uce.repository;

import ec.edu.uce.repository.modelo.Reserva;

public interface IReservaRepo {

	public void create(Reserva reserva);
	public Reserva read(Integer id); 
	public void update(Reserva reserva); 
	public void delete(Integer id); 
}
