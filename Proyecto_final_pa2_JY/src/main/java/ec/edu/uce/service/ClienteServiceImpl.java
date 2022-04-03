package ec.edu.uce.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.repository.IClienteRepo;
import ec.edu.uce.repository.modelo.Cliente;
import ec.edu.uce.repository.modelo.ClienteVIP;
import ec.edu.uce.repository.modelo.Reserva;

@Service
public class ClienteServiceImpl implements IClienteService {

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

	@Override
	public List<Cliente> buscarTodosClientes() {
		// TODO Auto-generated method stub
		return this.clienteRepo.buscarTodosClientes();
	}

	@Override
	public List<ClienteVIP> reporteClientesVIP() {
		List<Cliente> listaClientes = this.buscarTodosClientes();
		List<ClienteVIP> clientesVIP = new ArrayList<>();
		BigDecimal valorIVA = new BigDecimal(0);
		BigDecimal valorTotal= new BigDecimal(0);
		for (Cliente c : listaClientes) {
			List<Reserva> reservasCliente = c.getReserva();
			for (Reserva r : reservasCliente) {
				valorIVA=valorIVA.add(r.getCobro().getValorIVA());
				valorTotal=valorTotal.add(r.getCobro().getValorTotalPagar());
				
			}
			ClienteVIP clienteVIP = new ClienteVIP(c.getApellido(), c.getNombre(), c.getCedula(), c.getGenero(),
					c.getTipoRegistro(), valorIVA, valorTotal);
			clientesVIP.add(clienteVIP);
		}
		
		List<ClienteVIP> listaOrdenada=clientesVIP.parallelStream().sorted(Comparator.comparing(ClienteVIP::getValorTotal)).collect(Collectors.toList());
		//ordeno mayor a menor
		listaOrdenada=listaOrdenada.stream().sorted(Collections.reverseOrder(Comparator.comparing(ClienteVIP::getValorTotal))).collect(Collectors.toList());
		return listaOrdenada;
	}

}
