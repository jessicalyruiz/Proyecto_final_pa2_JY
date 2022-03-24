package ec.edu.uce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ec.edu.uce.repository.ITarjetaRepo;
import ec.edu.uce.repository.modelo.Tarjeta;

@Service
public class TarjetaServiceImpl implements ITarjetaService {

	@Autowired
	private ITarjetaRepo tarjetaRepo;

	@Override
	public void create(Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		this.tarjetaRepo.create(tarjeta);
	}

	@Override
	public Tarjeta read(Integer id) {
		// TODO Auto-generated method stub
		return this.tarjetaRepo.read(id);
	}

	@Override
	public void update(Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		this.tarjetaRepo.update(tarjeta);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.tarjetaRepo.delete(id);
	}
}
