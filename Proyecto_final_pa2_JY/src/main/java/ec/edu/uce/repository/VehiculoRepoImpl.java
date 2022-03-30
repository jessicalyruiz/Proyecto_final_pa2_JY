package ec.edu.uce.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.uce.repository.modelo.Reserva;
import ec.edu.uce.repository.modelo.Vehiculo;
import ec.edu.uce.repository.modelo.VehiculoBuscar;

@Repository
@Transactional
public class VehiculoRepoImpl implements IVehiculoRepo{

	private static final Logger LOG =  LoggerFactory.getLogger(VehiculoRepoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.persist(vehiculo);
	}

	@Override
	public Vehiculo read(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Vehiculo.class, id);
	}

	@Override
	public void update(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.merge(vehiculo);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.read(id));
	}

	@Override
	public List<VehiculoBuscar> buscarVehiculosDisponibles(String marca, String modelo) {
		TypedQuery<VehiculoBuscar> myQuery=this.entityManager.createQuery("Select NEW ec.edu.uce.repository.modelo.VehiculoBuscar(v.placa, v.modelo, v.marca, v.anioFabricacion, v.estado,v.valorPorDia) FROM Vehiculo v where v.marca=:valor1 and v.modelo=: valor2 ", VehiculoBuscar.class);
		myQuery.setParameter("valor1", marca);
		myQuery.setParameter("valor2", modelo);
		return myQuery.getResultList();
	}

	@Override
	public Vehiculo buscarPlaca(String placa) {
		TypedQuery<Vehiculo> myQuery=this.entityManager.createQuery("Select v from Vehiculo v where v.placa=:valor", Vehiculo.class);
		myQuery.setParameter("valor", placa);
		List<Reserva> reservas=myQuery.getSingleResult().getReservas();
		for (Reserva reserva : reservas) {
			LOG.info("Resercas rep"+reserva);
		}
		return myQuery.getSingleResult();
	}
}
