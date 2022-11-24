package com.server.service;

import java.util.List;

import com.server.entity.Autor;

public interface AutorService {
	public Autor insertaAutor(Autor obj);
	public Autor actualizar(Autor aut);
	void eliminar(Integer cod);
	public Autor buscar(Integer cod);
	public List<Autor> listaAutor();	
}
