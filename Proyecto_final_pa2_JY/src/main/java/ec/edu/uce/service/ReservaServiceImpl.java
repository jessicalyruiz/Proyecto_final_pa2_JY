package ec.edu.uce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.repository.IClienteRepo;
import ec.edu.uce.repository.IReservaRepo;
import ec.edu.uce.repository.modelo.Reserva;

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
}
