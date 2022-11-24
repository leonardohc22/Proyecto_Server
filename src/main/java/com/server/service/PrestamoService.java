package com.server.service;

import java.util.List;

import com.server.entity.Prestamo;

public interface PrestamoService {

	public Prestamo insertaPrestamo(Prestamo obj);
	public Prestamo actualizarPrestamo(Prestamo obj);
	void eliminarPrestamo(Integer cod);
	public Prestamo buscarPrestamo(Integer cod);
	public abstract List<Prestamo> listaPrestamo();
}
