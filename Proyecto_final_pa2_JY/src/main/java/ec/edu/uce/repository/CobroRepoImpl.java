package ec.edu.uce.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.uce.repository.modelo.Cobro;

@Repository
@Transactional
public class CobroRepoImpl implements ICobroRepo{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Cobro cobro) {
		// TODO Auto-generated method stub
		this.entityManager.persist(cobro);
	}

	@Override
	public Cobro read(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Cobro.class, id);
	}

	@Override
	public void update(Cobro cobro) {
		// TODO Auto-generated method stub
		this.entityManager.merge(cobro);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.read(id));
	}
}
