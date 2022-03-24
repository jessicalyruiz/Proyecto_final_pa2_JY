package ec.edu.uce.repository;


import ec.edu.uce.repository.modelo.Cobro;

public interface ICobroRepo {

	public void create(Cobro cobro);
	public Cobro read(Integer id); 
	public void update(Cobro cobro); 
	public void delete(Integer id); 
}
