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

import com.server.entity.Tesis;
import com.server.service.TesisService;
import com.server.utils.NotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tesis")
public class TesisController {
	
	
	@Autowired 
	private TesisService tesisService;
	
	@GetMapping("/lista")
	@ResponseBody
	public ResponseEntity<List<Tesis>> listaTesis(){
		List<Tesis> lista = tesisService.listaTesis();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/buscar/{codigo}")
	public ResponseEntity<Tesis> buscar(@PathVariable("codigo")Integer cod){
		Tesis bean=tesisService.buscar(cod);
		if(bean==null)
			 new NotFoundException();
			
			return new ResponseEntity<>(bean,HttpStatus.OK );	
	}
	
	@PostMapping("/registrar")
	@ResponseBody
	public ResponseEntity<?> insertaTesis(@Validated @RequestBody Tesis obj, Errors errors ){
		Map<String, Object> salida = new HashMap<>();
		List<String> lstMensajes = new ArrayList<>();
		salida.put("errores", lstMensajes);
		//
		obj.setFechaRegistro(new Date());
		obj.setEstado(1);
		
		List<ObjectError> lstErrors = errors.getAllErrors();
		for (ObjectError objectError : lstErrors) {
			objectError.getDefaultMessage();
			lstMensajes.add(objectError.getDefaultMessage());
		}
		if (!CollectionUtils.isEmpty(lstMensajes)) {
			return ResponseEntity.ok(salida);
		}
		
		Tesis objSalida = tesisService.insertaTesis(obj);
		if (objSalida == null) {
			lstMensajes.add("Error al registar Tesis");
		} else {
			lstMensajes.add("se registró la tesis de ID ==> " + objSalida.getId_Tesis());
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Tesis> actualizarTesis(@RequestBody Tesis obj){
		Tesis bean=tesisService.buscar(obj.getId_Tesis());
		if(bean==null)
			new NotFoundException();
		else
			bean = tesisService.actualizarTesis(obj);
			
		return new ResponseEntity<>(bean,HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id_Tesis}")
	public ResponseEntity<Void> eliminarTesis(@PathVariable("id_Tesis") Integer cod){
		Tesis bean = tesisService.buscar(cod);
		if(bean==null)
			 new NotFoundException();
		else
			tesisService.eliminar(cod);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
