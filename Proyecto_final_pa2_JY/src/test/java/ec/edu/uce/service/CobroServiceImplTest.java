package ec.edu.uce.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.uce.repository.modelo.Cobro;

@Transactional
@Rollback(true)
@SpringBootTest
class CobroServiceImplTest {

	@Autowired
	private ICobroService cobroService;
	
	@Test
	void testCreate() {
		//fail("Not yet implemented");
		Cobro cobro=new Cobro();
		cobro.setFecha(LocalDateTime.now());
		cobro.setTarjeta("111-1");
		this.cobroService.create(cobro);
		assertThat(cobro).isNotNull();
	}

	@Test
	void testRead() {
		//fail("Not yet implemented");
		Cobro cobro=new Cobro();
		cobro.setFecha(LocalDateTime.now());
		cobro.setTarjeta("111-1");
		this.cobroService.create(cobro);
		
		Cobro cobroR=this.cobroService.read(cobro.getId());
		assertThat(cobroR).isExactlyInstanceOf(Cobro.class);
	}

	@Test
	void testUpdate() {
		//fail("Not yet implemented");
		Cobro cobro=new Cobro();
		cobro.setFecha(LocalDateTime.now());
		cobro.setTarjeta("111-1");
		this.cobroService.create(cobro);
		
		cobro.setValorIVA(new BigDecimal(12.5));
		this.cobroService.update(cobro);
		
		assertThat(cobro.getValorIVA()).isEqualTo(new BigDecimal(12.5));
	}

	@Test
	void testDelete() {
		//fail("Not yet implemented");
		Cobro cobro=new Cobro();
		cobro.setFecha(LocalDateTime.now());
		cobro.setTarjeta("111-1");
		this.cobroService.create(cobro);
		
		this.cobroService.delete(cobro.getId());
		Cobro cobroR=this.cobroService.read(cobro.getId());
		assertThat(cobroR).isNull();
		
	}

}
