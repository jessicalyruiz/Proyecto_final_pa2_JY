package ec.edu.uce.service;

import ec.edu.uce.repository.modelo.Cobro;

public interface ICobroService {

	public void create(Cobro cobro);
	public Cobro read(Integer id); 
	public void update(Cobro cobro); 
	public void delete(Integer id); 
}
