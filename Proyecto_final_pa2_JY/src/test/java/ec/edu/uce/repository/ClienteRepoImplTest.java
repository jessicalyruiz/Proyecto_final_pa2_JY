package ec.edu.uce.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.uce.repository.modelo.Cliente;
import ec.edu.uce.service.IClienteService;

@Transactional
@Rollback(true)
@SpringBootTest
class ClienteRepoImplTest {

	@Autowired
	private IClienteService clienteService;
	
	@Test
	void testCreate() {
		Cliente cliente=new Cliente();
		cliente.setApellido("apellidoPrueba");
		cliente.setCedula("cedulaPrueba");
		cliente.setFechaNacimiento(LocalDate.now());
		cliente.setGenero("generoPrueba");
		cliente.setNombre("nombrePrueba");
		cliente.setTipoRegistro("registroPrueba");
		this.clienteService.create(cliente);
		
		assertThat(cliente).isExactlyInstanceOf(Cliente.class);
	}

	@Test
	void testRead() {
		//fail("Not yet implemented");
		Cliente cliente=new Cliente();
		cliente.setApellido("apellidoPrueba");
		cliente.setCedula("cedulaPrueba");
		cliente.setFechaNacimiento(LocalDate.now());
		cliente.setGenero("generoPrueba");
		cliente.setNombre("nombrePrueba");
		cliente.setTipoRegistro("registroPrueba");
		this.clienteService.create(cliente);
		
		Cliente clienteFind=this.clienteService.read(cliente.getId());
		assertThat(clienteFind).isNotNull();
	}

	@Test
	void testUpdate() {
		//fail("Not yet implemented");
		Cliente cliente=new Cliente();
		cliente.setApellido("apellidoPrueba");
		cliente.setCedula("cedulaPrueba");
		cliente.setFechaNacimiento(LocalDate.now());
		cliente.setGenero("generoPrueba");
		cliente.setNombre("nombrePrueba");
		cliente.setTipoRegistro("registroPrueba");
		this.clienteService.create(cliente);
		
		Cliente clienteFind=this.clienteService.read(cliente.getId());
		clienteFind.setApellido("apellidoActualizado");
		this.clienteService.update(clienteFind);
		assertThat(clienteFind.getId()).isEqualTo(cliente.getId());
	}

	@Test
	void testDelete() {
		//fail("Not yet implemented");
		Cliente cliente=new Cliente();
		cliente.setApellido("apellidoPrueba");
		cliente.setCedula("cedulaPrueba");
		cliente.setFechaNacimiento(LocalDate.now());
		cliente.setGenero("generoPrueba");
		cliente.setNombre("nombrePrueba");
		cliente.setTipoRegistro("registroPrueba");
		this.clienteService.create(cliente);
		
		this.clienteService.delete(cliente.getId());
		Cliente clienteFind=this.clienteService.read(cliente.getId());
		
		assertThat(clienteFind).isNull();
	}

	@Test
	void testBuscarCedula() {
		//fail("Not yet implemented");
		Cliente cliente=new Cliente();
		cliente.setApellido("apellidoPrueba");
		cliente.setCedula("cedulaPrueba");
		cliente.setFechaNacimiento(LocalDate.now());
		cliente.setGenero("generoPrueba");
		cliente.setNombre("nombrePrueba");
		cliente.setTipoRegistro("registroPrueba");
		this.clienteService.create(cliente);
		
		String cedula="cedulaPrueba";
		
		Cliente clienteFind=this.clienteService.buscarCedula(cedula);
		assertThat(clienteFind.getId()).isEqualTo(cliente.getId());
	}

}
