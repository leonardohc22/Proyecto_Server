package com.server.service;

import java.util.List;

import com.server.entity.Libro;

public interface LibroService {

	public Libro insertaLibro(Libro obj);
	public Libro actualizarLibro(Libro obj);
	void eliminarLibro(Integer cod);
	public Libro buscarLibro(Integer cod);
	public abstract List<Libro> listaLibro();
}
