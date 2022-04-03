package ec.edu.uce.repository;

import java.math.BigInteger;

import ec.edu.uce.repository.modelo.Reserva;

public interface IReservaRepo {

	public void create(Reserva reserva);
	public Reserva read(Integer id); 
	public void update(Reserva reserva); 
	public void delete(Integer id); 
	
	public Reserva obtenerUltimoRegistro();
	public BigInteger obtenerTotalRegistros();
	
	public Reserva buscarNumero(String numero);
}
