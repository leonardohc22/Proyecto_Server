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

import com.server.entity.Alumno;
import com.server.service.AlumnoService;
import com.server.utils.NotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alumno")
public class AlumnoController  {

	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping("/lista")
	@ResponseBody
	public ResponseEntity<List<Alumno>> listaAlumno(){
		List<Alumno> lista = alumnoService.listaAlumno();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/buscar/{codigo}")
	public ResponseEntity<Alumno> buscar(@PathVariable("codigo")Integer cod){
		Alumno bean=alumnoService.buscarAlumno(cod);
		if(bean==null)
			 new NotFoundException();
			
			return new ResponseEntity<>(bean,HttpStatus.OK );	
	}
	
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<?> insertaAlumno(@Validated @RequestBody Alumno obj, Errors errors ){
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
		
		Alumno objSalida = alumnoService.insertaAlumno(obj);
		if (objSalida == null) {
			lstMensajes.add("Error al registar Tesis");
		} else {
			lstMensajes.add("se registró la tesis de ID ==> " + objSalida.getId_Alumno());
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Alumno> actualizarAlumno(@RequestBody Alumno obj){
		Alumno bean=alumnoService.buscarAlumno(obj.getId_Alumno());
		if(bean==null)
			new NotFoundException();
		else
			bean = alumnoService.actualizarAlumno(obj);
			
		return new ResponseEntity<>(bean,HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id_Alumno}")
	public ResponseEntity<Void> eliminarAlumno(@PathVariable("id_Alumno") Integer cod){
		Alumno bean = alumnoService.buscarAlumno(cod);
		if(bean==null)
			 new NotFoundException();
		else
			alumnoService.eliminarAlumno(cod);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
