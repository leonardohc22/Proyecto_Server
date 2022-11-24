package com.server.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dao.AutorRepository;
import com.server.entity.Autor;
import com.server.service.AutorService;

@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	private AutorRepository repository;
	
	@Override
	public Autor insertaAutor(Autor obj) {
		return repository.save(obj);
	}
	
	@Override
	public List<Autor> listaAutor(){
		return repository.findAll();
	}

	@Override
	public Autor actualizar(Autor aut) {
		// TODO Auto-generated method stub
		return repository.save(aut);
	}

	@Override
	public void eliminar(Integer cod) {
		repository.deleteById(cod);
		
	}

	@Override
	public Autor buscar(Integer cod) {
		return repository.findById(cod).orElse(null);
	}

	




}
