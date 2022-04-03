package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.repository.modelo.Cliente;
import ec.edu.uce.repository.modelo.ClienteVIP;

public interface IClienteService {

	public void create(Cliente cliente);
	public Cliente read(Integer id); 
	public void update(Cliente cliente); 
	public void delete(Integer id); 
	
	public Cliente buscarCedula(String cedula);
	
	public void registrarse(Cliente cliente);
	public List<Cliente> buscarTodosClientes();
	
	public List<ClienteVIP> reporteClientesVIP();
}
