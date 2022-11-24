package com.server.service;

import java.util.List;

import com.server.entity.Tesis;

public interface TesisService {
	public Tesis insertaTesis(Tesis obj);
	public Tesis actualizarTesis(Tesis obj);
	void eliminar(Integer cod);
	public Tesis buscar(Integer cod);
	public List<Tesis> listaTesis();
}
