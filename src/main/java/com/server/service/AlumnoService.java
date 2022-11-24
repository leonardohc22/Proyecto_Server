package com.server.service;

import java.util.List;

import com.server.entity.Alumno;

public interface AlumnoService {

	public Alumno insertaAlumno(Alumno obj);
	public Alumno actualizarAlumno(Alumno obj);
	void eliminarAlumno(Integer cod);
	public Alumno buscarAlumno(Integer cod);
	public abstract List<Alumno> listaAlumno();
}
