package com.server.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dao.LibroRepository;
import com.server.entity.Libro;
import com.server.service.LibroService;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroRepository repository;
	
	@Override
	public Libro insertaLibro(Libro obj) {
		return repository.save(obj);
	}

	@Override
	public Libro actualizarLibro(Libro obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminarLibro(Integer cod) {
		repository.deleteById(cod);
		
	}

	@Override
	public Libro buscarLibro(Integer cod) {
		return repository.findById(cod).orElse(null);
	}

	@Override
	public List<Libro> listaLibro() {
		return repository.findAll();
		
	}

}
