package com.server.controller;

import java.util.ArrayList;


import java.util.Date;
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

import com.server.entity.Autor;
import com.server.service.AutorService;
import com.server.utils.NotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/autor")
public class AutorController {
//
	@Autowired 
	private AutorService autorService;
	
	@GetMapping("/lista")
	@ResponseBody
	public ResponseEntity<List<Autor>> listaAutor(){
		List<Autor> lista = autorService.listaAutor();
		return ResponseEntity.ok(lista);
	}
	//Buscar
	@GetMapping("/buscar/{id_Autor}")
	public ResponseEntity<Autor> buscar(@PathVariable("id_Autor")Integer cod){
		Autor bean=autorService.buscar(cod);
		if(bean==null)
			 new NotFoundException();
			
			return new ResponseEntity<>(bean,HttpStatus.OK );	
	}
	
	
	//
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<?> insertaAutor(@Validated @RequestBody Autor obj, Errors errors ){
		Map<String, Object> salida = new HashMap<>();
		List<String> lstMensajes = new ArrayList<>();
		salida.put("errores", lstMensajes);
		//
		obj.setFecha_Registro(new Date());
		obj.setEstado(1);
		
		List<ObjectError> lstErrors = errors.getAllErrors();
		for (ObjectError objectError : lstErrors) {
			objectError.getDefaultMessage();
			lstMensajes.add(objectError.getDefaultMessage());
		}
		if (!CollectionUtils.isEmpty(lstMensajes)) {
			return ResponseEntity.ok(salida);
		}
		
		Autor objSalida = autorService.insertaAutor(obj);
		if (objSalida == null) {
			lstMensajes.add("Error al registar Autor");
		} else {
			lstMensajes.add("se registró el Autor de ID ==> " + objSalida.getId_Autor());
		}
		
		return ResponseEntity.ok(salida);
	}
	//Actualizar
	@PutMapping("/actualizar")
	public ResponseEntity<Autor> actualizar(@RequestBody Autor aut){
		Autor bean=autorService.buscar(aut.getId_Autor());
		if(bean==null)
			new NotFoundException();
		else
			bean = autorService.actualizar(aut);
		
		return new ResponseEntity<>(bean,HttpStatus.OK);
	}
	//eliminar
	@DeleteMapping("/eliminar/{id_Autor}")
	public ResponseEntity<Void> eliminar(@PathVariable("id_Autor") Integer cod){
		Autor bean = autorService.buscar(cod);
		if(bean==null)
			 new NotFoundException();
		else
			autorService.eliminar(cod);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	}