package ec.edu.uce.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.uce.repository.modelo.Cliente;
import ec.edu.uce.repository.modelo.Reserva;
import ec.edu.uce.repository.modelo.Vehiculo;
import ec.edu.uce.repository.modelo.VehiculoBuscar;
import ec.edu.uce.repository.modelo.VehiculoVIP;
@Transactional
@Rollback(true)
@SpringBootTest
class VehiculoServiceImplTest {

	@Autowired
	private IVehiculoService vehiculoService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Test
	void testCreate() {
		//fail("Not yet implemented");
		Vehiculo vehiculo1=new Vehiculo();
		vehiculo1.setAnioFabricacion("2015");
		vehiculo1.setAvaluo(new BigDecimal(12500));
		vehiculo1.setCilindraje(0.6f);
		vehiculo1.setEstado("D");
		vehiculo1.setMarca("Toyota");
		vehiculo1.setModelo("Prius");
		vehiculo1.setPaisFabricacion("japon");
		vehiculo1.setPlaca("PCooooT-1245");
		vehiculo1.setValorPorDia( new BigDecimal(40));
		this.vehiculoService.create(vehiculo1);
		assertThat(vehiculo1).isExactlyInstanceOf(Vehiculo.class);
	}

	@Test
	void testRead() {
		//fail("Not yet implemented");
		Vehiculo vehiculo1=new Vehiculo();
		vehiculo1.setAnioFabricacion("2015");
		vehiculo1.setAvaluo(new BigDecimal(12500));
		vehiculo1.setCilindraje(0.6f);
		vehiculo1.setEstado("D");
		vehiculo1.setMarca("Toyota");
		vehiculo1.setModelo("Prius");
		vehiculo1.setPaisFabricacion("japon");
		vehiculo1.setPlaca("PCopppT-1245");
		vehiculo1.setValorPorDia( new BigDecimal(40));
		this.vehiculoService.create(vehiculo1);
		
		Vehiculo vehiculoRead=this.vehiculoService.read(vehiculo1.getId());
		assertThat(vehiculoRead).isEqualTo(vehiculo1);
	}

	@Test
	void testUpdate() {
		//fail("Not yet implemented");
		Vehiculo vehiculo1=new Vehiculo();
		vehiculo1.setAnioFabricacion("2015");
		vehiculo1.setAvaluo(new BigDecimal(12500));
		vehiculo1.setCilindraje(0.6f);
		vehiculo1.setEstado("D");
		vehiculo1.setMarca("Toyota");
		vehiculo1.setModelo("Prius");
		vehiculo1.setPaisFabricacion("japon");
		vehiculo1.setPlaca("PCyyyyT-1245");
		vehiculo1.setValorPorDia( new BigDecimal(40));
		this.vehiculoService.create(vehiculo1);
		
		vehiculo1.setEstado("ND");
		this.vehiculoService.update(vehiculo1);
		assertThat(vehiculo1.getEstado()).isEqualTo("ND");
	}

	@Test
	void testDelete() {
		//fail("Not yet implemented");
		Vehiculo vehiculo1=new Vehiculo();
		vehiculo1.setAnioFabricacion("2015");
		vehiculo1.setAvaluo(new BigDecimal(12500));
		vehiculo1.setCilindraje(0.6f);
		vehiculo1.setEstado("D");
		vehiculo1.setMarca("Toyota");
		vehiculo1.setModelo("Prius");
		vehiculo1.setPaisFabricacion("japon");
		vehiculo1.setPlaca("PCTlllll-1245");
		vehiculo1.setValorPorDia( new BigDecimal(40));
		this.vehiculoService.create(vehiculo1);
		
		this.vehiculoService.delete(vehiculo1.getId());
		Vehiculo vehic=this.vehiculoService.read(vehiculo1.getId());
		assertThat(vehic).isNull();
	}

	@Test
	void testBuscarVehiculosDisponibles() {
		//fail("Not yet implemented");
		Vehiculo vehiculo1=new Vehiculo();
		vehiculo1.setAnioFabricacion("2015");
		vehiculo1.setAvaluo(new BigDecimal(12500));
		vehiculo1.setCilindraje(0.6f);
		vehiculo1.setEstado("D");
		vehiculo1.setMarca("Toyota");
		vehiculo1.setModelo("Prius");
		vehiculo1.setPaisFabricacion("japon");
		vehiculo1.setPlaca("PCTmmmm-1245");
		vehiculo1.setValorPorDia( new BigDecimal(40));
		this.vehiculoService.create(vehiculo1);
		
		
		List<VehiculoBuscar> listaVechiculos=this.vehiculoService.buscarVehiculosDisponibles(vehiculo1.getMarca(), vehiculo1.getModelo());
		assertThat(listaVechiculos).isNotEmpty();
	}

	@Test
	void testBuscarPlaca() {
		//fail("Not yet implemented");
		Vehiculo vehiculo1=new Vehiculo();
		vehiculo1.setAnioFabricacion("2015");
		vehiculo1.setAvaluo(new BigDecimal(12500));
		vehiculo1.setCilindraje(0.6f);
		vehiculo1.setEstado("D");
		vehiculo1.setMarca("Toyota");
		vehiculo1.setModelo("Prius");
		vehiculo1.setPaisFabricacion("japon");
		vehiculo1.setPlaca("PCnnnnnT-1247");
		vehiculo1.setValorPorDia( new BigDecimal(40));
		this.vehiculoService.create(vehiculo1);
		
		Vehiculo vehiculo=this.vehiculoService.buscarPlaca(vehiculo1.getPlaca());
		assertThat(vehiculo).isEqualTo(vehiculo1);
	}

	@Test
	void testReservarVehiculo() {
		//fail("Not yet implemented");
		Vehiculo vehiculo1=new Vehiculo();
		vehiculo1.setAnioFabricacion("2015");
		vehiculo1.setAvaluo(new BigDecimal(12500));
		vehiculo1.setCilindraje(0.6f);
		vehiculo1.setEstado("D");
		vehiculo1.setMarca("Toyota");
		vehiculo1.setModelo("Prius");
		vehiculo1.setPaisFabricacion("japon");
		vehiculo1.setPlaca("PAWggggT-1298");
		vehiculo1.setValorPorDia( new BigDecimal(40));
		this.vehiculoService.create(vehiculo1);
		
		
		Cliente cliente=new Cliente();
		cliente.setApellido("yanez");
		cliente.setCedula("7899pppp");
		cliente.setFechaNacimiento(LocalDate.of(1992, 06, 24));
		cliente.setGenero("femenino");	
		cliente.setNombre("Jessica");
		this.clienteService.create(cliente);
		
		Reserva reserva=this.vehiculoService.reservarVehiculo(vehiculo1.getPlaca(), cliente.getCedula(), LocalDateTime.of(2022, 5, 10, 0, 0) , LocalDateTime.of(2022, 5, 11, 0, 0), null);
		assertThat(reserva.getVehiculo()).isEqualTo(vehiculo1);
		
	}



	@Test
	void testRetirarVehiculoReservado() {
		//fail("Not yet implemented");
		Vehiculo vehiculo1=new Vehiculo();
		vehiculo1.setAnioFabricacion("2015");
		vehiculo1.setAvaluo(new BigDecimal(12500));
		vehiculo1.setCilindraje(0.6f);
		vehiculo1.setEstado("D");
		vehiculo1.setMarca("Toyota");
		vehiculo1.setModelo("Prius");
		vehiculo1.setPaisFabricacion("japon");
		vehiculo1.setPlaca("PAWiTeeeee-1298");
		vehiculo1.setValorPorDia( new BigDecimal(40));
		this.vehiculoService.create(vehiculo1);
		
		
		Cliente cliente=new Cliente();
		cliente.setApellido("yanez");
		cliente.setCedula("7899000ee");
		cliente.setFechaNacimiento(LocalDate.of(1992, 06, 24));
		cliente.setGenero("femenino");	
		cliente.setNombre("Jessica");
		this.clienteService.create(cliente);
		
		Reserva reserva=this.vehiculoService.reservarVehiculo(vehiculo1.getPlaca(), cliente.getCedula(), LocalDateTime.of(2022, 5, 10, 0, 0) , LocalDateTime.of(2022, 5, 11, 0, 0), "111");
		Reserva reservaRetiro=this.vehiculoService.retirarVehiculoReservado(reserva.getNumero());
		assertThat(reservaRetiro.getEstado()).isEqualTo("E");
	}

	@Test
	void testReporteVEhiculosVIP() {
		//fail("Not yet implemented");
		Vehiculo vehiculo1=new Vehiculo();
		vehiculo1.setAnioFabricacion("2015");
		vehiculo1.setAvaluo(new BigDecimal(12500));
		vehiculo1.setCilindraje(0.6f);
		vehiculo1.setEstado("D");
		vehiculo1.setMarca("Toyota");
		vehiculo1.setModelo("Prius");
		vehiculo1.setPaisFabricacion("japon");
		vehiculo1.setPlaca("PAWiTeeeerre-1298");
		vehiculo1.setValorPorDia( new BigDecimal(40));
		this.vehiculoService.create(vehiculo1);
		
		
		Cliente cliente=new Cliente();
		cliente.setApellido("yanez");
		cliente.setCedula("789900rr0ee");
		cliente.setFechaNacimiento(LocalDate.of(1992, 06, 24));
		cliente.setGenero("femenino");	
		cliente.setNombre("Jessica");
		this.clienteService.create(cliente);
		
		Reserva reserva=this.vehiculoService.reservarVehiculo(vehiculo1.getPlaca(), cliente.getCedula(), LocalDateTime.of(2022, 5, 10, 0, 0) , LocalDateTime.of(2022, 5, 11, 0, 0), "111");
		List<VehiculoVIP> listaVIP=this.vehiculoService.reporteVEhiculosVIP(5, 2022);
		assertThat(listaVIP).isNotEmpty();
	}

	@Test
	void testBuscarReservasVehiculoFecha() {
		//fail("Not yet implemented");
		Vehiculo vehiculo1=new Vehiculo();
		vehiculo1.setAnioFabricacion("2015");
		vehiculo1.setAvaluo(new BigDecimal(12500));
		vehiculo1.setCilindraje(0.6f);
		vehiculo1.setEstado("D");
		vehiculo1.setMarca("Toyota");
		vehiculo1.setModelo("Prius");
		vehiculo1.setPaisFabricacion("japon");
		vehiculo1.setPlaca("PAWiTeeeerroe-1298");
		vehiculo1.setValorPorDia( new BigDecimal(40));
		this.vehiculoService.create(vehiculo1);
		
		
		Cliente cliente=new Cliente();
		cliente.setApellido("yanez");
		cliente.setCedula("7899o00rr0ee");
		cliente.setFechaNacimiento(LocalDate.of(1992, 06, 24));
		cliente.setGenero("femenino");	
		cliente.setNombre("Jessica");
		this.clienteService.create(cliente);
		
		Reserva reserva=this.vehiculoService.reservarVehiculo(vehiculo1.getPlaca(), cliente.getCedula(), LocalDateTime.of(2022, 5, 1, 0, 0) , LocalDateTime.of(2022, 5, 11, 0, 0), "111");
		System.out.println(reserva);
		System.out.println(vehiculo1);
		System.out.println(vehiculo1.getReservas());
		List<Reserva> listR=this.vehiculoService.buscarReservasVehiculoFecha(vehiculo1, LocalDateTime.of(2022, 4, 1, 0, 0), LocalDateTime.of(2022, 6, 1, 0, 0));
		assertThat(listR).isNotEmpty();
	}

	@Test
	void testBuscarFechas() {
		//fail("Not yet implemented");
		Vehiculo vehiculo1=new Vehiculo();
		vehiculo1.setAnioFabricacion("2015");
		vehiculo1.setAvaluo(new BigDecimal(12500));
		vehiculo1.setCilindraje(0.6f);
		vehiculo1.setEstado("D");
		vehiculo1.setMarca("Toyota");
		vehiculo1.setModelo("Prius");
		vehiculo1.setPaisFabricacion("japon");
		vehiculo1.setPlaca("PCT-1245");
		vehiculo1.setValorPorDia( new BigDecimal(40));
		this.vehiculoService.create(vehiculo1);
		assertThat(vehiculo1).isNotNull();
	}

}
