package ec.edu.uce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.repository.IClienteRepo;
import ec.edu.uce.repository.ICobroRepo;
import ec.edu.uce.repository.modelo.Cobro;

@Service
public class CobroServiceImpl implements ICobroService{

	@Autowired
	private ICobroRepo cobroRepo;

	@Override
	public void create(Cobro cobro) {
		// TODO Auto-generated method stub
		this.cobroRepo.create(cobro);
	}

	@Override
	public Cobro read(Integer id) {
		// TODO Auto-generated method stub
		return this.cobroRepo.read(id);
	}

	@Override
	public void update(Cobro cobro) {
		// TODO Auto-generated method stub
		this.cobroRepo.update(cobro);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.cobroRepo.delete(id);
	}
}
