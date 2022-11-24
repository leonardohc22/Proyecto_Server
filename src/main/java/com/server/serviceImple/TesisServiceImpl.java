package com.server.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dao.TesisRepository;
import com.server.entity.Tesis;
import com.server.service.TesisService;

@Service
public class TesisServiceImpl implements TesisService{

	@Autowired
	private TesisRepository repository;
	
	@Override
	public Tesis insertaTesis(Tesis obj) {
		return repository.save(obj);
	}

	@Override
	public List<Tesis> listaTesis() {
		return repository.findAll();
	}

	@Override
	public Tesis actualizarTesis(Tesis obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminar(Integer cod) {
		repository.deleteById(cod);
	}

	@Override
	public Tesis buscar(Integer cod) {
		return repository.findById(cod).orElse(null);
	}
}
