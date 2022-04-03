package ec.edu.uce.repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.uce.ProyectoFinalPa2JyApplication;
import ec.edu.uce.repository.modelo.Reserva;

@Repository
@Transactional
public class ReservaRepoImpl implements IReservaRepo {

	private static final Logger LOG=Logger.getLogger(ReservaRepoImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.persist(reserva);
	}

	@Override
	public Reserva read(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Reserva.class, id);
	}

	@Override
	public void update(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.merge(reserva);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.read(id));
	}

	@Override
	public Reserva obtenerUltimoRegistro() {
		TypedQuery<Reserva> myQuery=this.entityManager.createQuery("Select r from Reserva r ORDER BY r.numero DESC", Reserva.class);
		myQuery.setMaxResults(1);
		return myQuery.getSingleResult();
	}

	@Override
	public BigInteger obtenerTotalRegistros() {
		Query myQuery=this.entityManager.createNativeQuery("SELECT COUNT(*) FROM RESERVA");
		
		return  (BigInteger) myQuery.getSingleResult();
	}

	@Override
	public Reserva buscarNumero(String numero) {
		TypedQuery<Reserva> myQuery=this.entityManager.createQuery("Select r from Reserva r where r.numero=:valor", Reserva.class);
		myQuery.setParameter("valor", numero);
		return myQuery.getSingleResult();
	}

	@Override
	public List<Reserva> reporteReservas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		TypedQuery<Reserva> myQuery=this.entityManager.createQuery("Select r from Reserva r where r.fechaInicio>=:valor1 and r.fechaFin<=:valor2", Reserva.class);
		myQuery.setParameter("valor1", fechaInicio);
		myQuery.setParameter("valor2", fechaFin);
		
		//relacionamientos
		List<Reserva> listaReservas=myQuery.getResultList();
		for (Reserva r : listaReservas) {
			LOG.info("Cliente"+r.getCliente());
			LOG.info("Cobro"+r.getCobro());
			LOG.info("vehiculo"+r.getVehiculo());
		}
		
		return listaReservas;
	}
}
