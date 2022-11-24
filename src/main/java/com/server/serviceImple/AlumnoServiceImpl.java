package com.server.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dao.AlumnoRepository;
import com.server.entity.Alumno;
import com.server.service.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService{
	
	@Autowired
	private AlumnoRepository repository;

	@Override
	public List<Alumno> listaAlumno() {
		return repository.findAll();
	}

	@Override
	public Alumno insertaAlumno(Alumno obj) {
		return repository.save(obj);
	}

	@Override
	public Alumno actualizarAlumno(Alumno obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminarAlumno(Integer cod) {
		repository.deleteById(cod);
	}

	@Override
	public Alumno buscarAlumno(Integer cod) {
		return repository.findById(cod).orElse(null);
	}

}
