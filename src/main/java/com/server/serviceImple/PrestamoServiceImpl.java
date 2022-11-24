package com.server.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dao.PrestamoRepository;
import com.server.entity.Prestamo;
import com.server.service.PrestamoService;

@Service
public class PrestamoServiceImpl implements PrestamoService {

	@Autowired
	private PrestamoRepository repository;
	
	@Override
	public Prestamo insertaPrestamo(Prestamo obj) {
		return repository.save(obj);
	}

	@Override
	public Prestamo actualizarPrestamo(Prestamo obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminarPrestamo(Integer cod) {
		repository.deleteById(cod);
		
	}

	@Override
	public Prestamo buscarPrestamo(Integer cod) {
		return repository.findById(cod).orElse(null);
	}

	@Override
	public List<Prestamo> listaPrestamo() {
		return repository.findAll();
	}

}
