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

import com.server.entity.Prestamo;
import com.server.service.PrestamoService;
import com.server.utils.NotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

	@Autowired
	PrestamoService prestamoService;

	@GetMapping("/lista")
	@ResponseBody
	public ResponseEntity<List<Prestamo>> listaPrestamo() {
		List<Prestamo> lista = prestamoService.listaPrestamo();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/buscar/{codigo}")
	public ResponseEntity<Prestamo> buscar(@PathVariable("codigo") Integer cod) {
		Prestamo bean = prestamoService.buscarPrestamo(cod);
		if (bean == null)
			new NotFoundException();

		return new ResponseEntity<>(bean, HttpStatus.OK);
	}

	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<?> insertaAlumno(@Validated @RequestBody Prestamo obj, Errors errors) {
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

		Prestamo objSalida = prestamoService.insertaPrestamo(obj);
		if (objSalida == null) {
			lstMensajes.add("Error al registrar el prestamo");
		} else {
			lstMensajes.add("se registró el prestamo de ID ==> " + objSalida.getId_Prestamo());
		}
		return ResponseEntity.ok(salida);
	}

	@PutMapping("/actualizar")
	public ResponseEntity<Prestamo> actualizarPrestamo(@RequestBody Prestamo obj){
		Prestamo bean = prestamoService.buscarPrestamo(obj.getId_Prestamo());
		if(bean==null)
			new NotFoundException();
		else
			bean = prestamoService.actualizarPrestamo(obj);
		
		return new ResponseEntity<>(bean,HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id_Prestamo}")
	public ResponseEntity<Void> eliminarPrestamo(@PathVariable("id_Prestamo")Integer cod){
		Prestamo bean = prestamoService.buscarPrestamo(cod);
		if(bean==null)
			 new NotFoundException();
		else 
			prestamoService.eliminarPrestamo(cod);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
