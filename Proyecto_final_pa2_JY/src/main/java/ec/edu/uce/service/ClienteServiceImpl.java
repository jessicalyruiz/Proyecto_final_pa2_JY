package ec.edu.uce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.repository.IClienteRepo;
import ec.edu.uce.repository.modelo.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteRepo clienteRepo;
	
	@Override
	public void create(Cliente cliente) {
		// TODO Auto-generated method stub
		this.clienteRepo.create(cliente);
	}

	@Override
	public Cliente read(Integer id) {
		// TODO Auto-generated method stub
		return this.clienteRepo.read(id);
	}

	@Override
	public void update(Cliente cliente) {
		// TODO Auto-generated method stub
		this.clienteRepo.update(cliente);	
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.clienteRepo.delete(id);
	}

	@Override
	public Cliente buscarCedula(String cedula) {
		// TODO Auto-generated method stub
		return this.clienteRepo.buscarCedula(cedula);
	}

	@Override
	public void registrarse(Cliente cliente) {
		// TODO Auto-generated method stub
		cliente.setTipoRegistro("C");
		this.create(cliente);
	}



}
