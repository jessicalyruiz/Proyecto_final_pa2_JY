package ec.edu.uce.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.uce.repository.modelo.Tarjeta;

@Repository
@Transactional
public class TarjetaRepoImpl implements ITarjetaRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		this.entityManager.persist(tarjeta);
	}

	@Override
	public Tarjeta read(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Tarjeta.class, id);
	}

	@Override
	public void update(Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		this.entityManager.merge(tarjeta);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.read(id));
	}
}
