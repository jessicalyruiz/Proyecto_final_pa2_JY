package ec.edu.uce.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.repository.IClienteRepo;
import ec.edu.uce.repository.IReservaRepo;
import ec.edu.uce.repository.modelo.Reserva;
import ec.edu.uce.repository.modelo.Vehiculo;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IReservaRepo reservaRepo;
	
	
	@Override
	public void create(Reserva reserva) {
		// TODO Auto-generated method stub
		this.reservaRepo.create(reserva);
	}

	@Override
	public Reserva read(Integer id) {
		// TODO Auto-generated method stub
		return this.reservaRepo.read(id);
	}

	@Override
	public void update(Reserva reserva) {
		// TODO Auto-generated method stub
		this.reservaRepo.update(reserva);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.reservaRepo.delete(id);
	}

	@Override
	public Reserva obtenerUltimoRegistro() {
		// TODO Auto-generated method stub
		return this.reservaRepo.obtenerUltimoRegistro();
	}

	@Override
	public BigInteger obtenerTotalRegistros() {
		// TODO Auto-generated method stub
		return this.reservaRepo.obtenerTotalRegistros();
	}

	@Override
	public Reserva buscarNumero(String numero) {
		// TODO Auto-generated method stub
		return this.reservaRepo.buscarNumero(numero);
	}


}
