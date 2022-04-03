package ec.edu.uce.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.repository.modelo.Reserva;

public interface IReservaService {

	public void create(Reserva reserva);
	public Reserva read(Integer id); 
	public void update(Reserva reserva); 
	public void delete(Integer id);
	
	public Reserva obtenerUltimoRegistro();
	public BigInteger obtenerTotalRegistros();
	public Reserva buscarNumero(String numero);
	public List<Reserva> reporteReservas(LocalDateTime fechaInicio, LocalDateTime fechaFin) ;
}
