package com.server.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dao.PaisRepository;
import com.server.entity.Pais;
import com.server.service.PaisService;

@Service
public class PaisServiceImpl implements PaisService{

	@Autowired
	private PaisRepository repository;
	@Override
	public List<Pais> listaPais() {
		return repository.findAll();
	}

}
