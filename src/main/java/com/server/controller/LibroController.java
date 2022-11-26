package com.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.entity.Libro;
import com.server.service.LibroService;
import com.server.utils.NotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/libro")
public class LibroController {

	
	@Autowired LibroService libroService;
	
	@GetMapping("/lista")
	@ResponseBody
	public ResponseEntity<List<Libro>> listaLibro(){
		List<Libro> lista = libroService.listaLibro();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/buscar/{codigo}")
	public ResponseEntity<Libro> buscar(@PathVariable("codigo")Integer cod){
		Libro bean = libroService.buscarLibro(cod);
		if(bean==null)
			new NotFoundException();
		
		return new ResponseEntity<>(bean,HttpStatus.OK );	
		
	}
	
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<?> insertaLibro(@Validated @RequestBody Libro obj,Errors errors){
		Map<String, Object> salida = new HashMap<>();
		List<String> lstMensajes = new ArrayList<>();
		salida.put("errores", lstMensajes);
		
		
		List<ObjectError> lstErrors = errors.getAllErrors();
		for (ObjectError objectError : lstErrors) {
			objectError.getDefaultMessage();
			lstMensajes.add(objectError.getDefaultMessage());
		}
		if (!CollectionUtils.isEmpty(lstMensajes)) {
			return ResponseEntity.ok(salida);
		}
		
		Libro objSalida = libroService.insertaLibro(obj);
		if (objSalida == null) {
			lstMensajes.add("Error al registar Libro");
		} else {
			lstMensajes.add("se registró el Libro de ID ==> " + objSalida.getId_Libro());
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Libro> actualizarLibro(@RequestBody Libro obj){
		Libro bean = libroService.buscarLibro(obj.getId_Libro());
		if(bean==null)
			new NotFoundException();
		else
			bean = libroService.actualizarLibro(obj);
		
		return new ResponseEntity<>(bean,HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id_Libro}")
	public ResponseEntity<Void> eliminarLibro(@PathVariable("id_Libro")Integer cod){
		Libro bean = libroService.buscarLibro(cod);
		if(bean==null)
			 new NotFoundException();
		else
			libroService.eliminarLibro(cod);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
