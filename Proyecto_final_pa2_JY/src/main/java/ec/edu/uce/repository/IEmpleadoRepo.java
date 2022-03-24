package ec.edu.uce.repository;

import ec.edu.uce.repository.modelo.Empleado;

public interface IEmpleadoRepo {

	public void create(Empleado empleado);
	public Empleado read(Integer id); 
	public void update(Empleado empleado); 
	public void delete(Integer id); 
}
