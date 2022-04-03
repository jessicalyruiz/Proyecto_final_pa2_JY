package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import ec.edu.uce.repository.modelo.Cliente;
import ec.edu.uce.repository.modelo.Reserva;

@Repository
@Transactional
public class ClienteRepoImpl implements IClienteRepo{

	//private static final Logger LOG =  LoggerFactory.getLogger(ClienteRepoImpl.class);
	private static Logger LOG=Logger.getLogger(ClienteRepoImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void create(Cliente cliente) {
		// TODO Auto-generated method stub
		this.entityManager.persist(cliente);
	}

	@Override
	public Cliente read(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Cliente.class, id);
	}

	@Override
	public void update(Cliente cliente) {
		// TODO Auto-generated method stub
		this.entityManager.merge(cliente);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.read(id));
	}

	@Override
	public Cliente buscarCedula(String cedula) {
		TypedQuery<Cliente> myQuery=this.entityManager.createQuery("Select c from Cliente c  where c.cedula=:valor", Cliente.class);
		myQuery.setParameter("valor", cedula);
		List<Reserva> reservas=myQuery.getSingleResult().getReserva();
		if(reservas!=null) {
		for (Reserva reserva : reservas) {
			LOG.info("Resercas rep"+reserva);
		}
		}
		return myQuery.getSingleResult();
	}

}
