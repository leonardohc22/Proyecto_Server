package com.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.entity.Alumno;
import com.server.entity.Grado;
import com.server.entity.Pais;
import com.server.entity.Tesis;
import com.server.service.AlumnoService;
import com.server.service.GradoService;
import com.server.service.PaisService;
import com.server.service.TesisService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/url/util")
public class UtilController {

	// Grado -> Inés
		@Autowired
		private GradoService gradoService;
		
		@Autowired
		private PaisService paisService;
		
		@Autowired
		private AlumnoService alumnoService;
		
		@Autowired
		private TesisService tesisService;
		
		@GetMapping("/grado")
		@ResponseBody
		public ResponseEntity<List<Grado>>listaTodos(){
			List<Grado> lista = gradoService.listaTodos();
			return ResponseEntity.ok(lista);
		}
		
		@GetMapping("/pais")
		@ResponseBody
		public ResponseEntity<List<Pais>>listaPaises(){
			List<Pais> lista = paisService.listaPais();
			return ResponseEntity.ok(lista);
		}
		
		@GetMapping("/alumno")
		@ResponseBody
		public ResponseEntity<List<Alumno>>listaAlumnos(){
			List<Alumno> lista = alumnoService.listaAlumno();
			return ResponseEntity.ok(lista);
		}
		
		@GetMapping("/tesis")
		@ResponseBody
		public ResponseEntity<List<Tesis>>listaTesis(){
			List<Tesis> lista = tesisService.listaTesis();
			return ResponseEntity.ok(lista);
		}
}
