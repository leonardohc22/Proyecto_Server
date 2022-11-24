package com.server.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dao.GradoRepository;
import com.server.entity.Grado;
import com.server.service.GradoService;

@Service
public class GradoServiceImpl implements GradoService{

	@Autowired
	private GradoRepository Repository;

	@Override
	public List<Grado> listaTodos() {
		return Repository.findAll();

	}
}
