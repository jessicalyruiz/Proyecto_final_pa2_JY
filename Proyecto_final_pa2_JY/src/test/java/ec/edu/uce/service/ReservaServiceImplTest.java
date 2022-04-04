package ec.edu.uce.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.uce.repository.modelo.Reserva;
@Transactional
@Rollback(true)
@SpringBootTest
class ReservaServiceImplTest {

	@Autowired
	private IReservaService reservaService;
	
	@Test
	void testCreate() {
		//fail("Not yet implemented");
		Reserva reserva=new Reserva();
		reserva.setEstado("E");
		reserva.setFechaFin(LocalDateTime.of(2022, 6, 1, 0, 0));
		reserva.setFechaInicio(LocalDateTime.now());
		reserva.setNumero("00o");
		this.reservaService.create(reserva);
		assertThat(reserva).isNotNull();
		
	}

	@Test
	void testRead() {
		//fail("Not yet implemented");
		Reserva reserva=new Reserva();
		reserva.setEstado("E");
		reserva.setFechaFin(LocalDateTime.of(2022, 6, 1, 0, 0));
		reserva.setFechaInicio(LocalDateTime.now());
		reserva.setNumero("00o");
		this.reservaService.create(reserva);
		
		Reserva reservaR=this.reservaService.read(reserva.getId());
		assertThat(reservaR).isNotNull();
	}

	@Test
	void testUpdate() {
		//fail("Not yet implemented");
		Reserva reserva=new Reserva();
		reserva.setEstado("E");
		reserva.setFechaFin(LocalDateTime.of(2022, 6, 1, 0, 0));
		reserva.setFechaInicio(LocalDateTime.now());
		reserva.setNumero("00o");
		this.reservaService.create(reserva);
		
		reserva.setNumero("000kk");
		this.reservaService.update(reserva);
		
		assertThat(reserva.getNumero()).isEqualTo("000kk");
	}


}
