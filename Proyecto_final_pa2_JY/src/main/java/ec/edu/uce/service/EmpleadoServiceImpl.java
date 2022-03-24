package ec.edu.uce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.repository.IClienteRepo;
import ec.edu.uce.repository.IEmpleadoRepo;
import ec.edu.uce.repository.modelo.Empleado;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{

	@Autowired
	private IEmpleadoRepo empleadoRepo;

	@Override
	public void create(Empleado empleado) {
		// TODO Auto-generated method stub
		this.empleadoRepo.create(empleado);
	}

	@Override
	public Empleado read(Integer id) {
		// TODO Auto-generated method stub
		return this.empleadoRepo.read(id);
	}

	@Override
	public void update(Empleado empleado) {
		// TODO Auto-generated method stub
		this.empleadoRepo.update(empleado);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.empleadoRepo.delete(id);
	}
}
