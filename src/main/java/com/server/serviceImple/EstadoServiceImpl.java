package com.server.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dao.EstadoRepository;
import com.server.entity.Estado;
import com.server.service.EstadoService;

@Service
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	private EstadoRepository repository;

	@Override
	public List<Estado> listaEstado() {
		return repository.findAll();
	}
}
