package com.server.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tesis")
public class Tesis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_Tesis;
	
	private String titulo;
	private String tema;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "America/Lima")
	private Date fecha_Creacion;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_Registro;

	private int estado;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_Alumno")
	private Alumno alumno;

	public int getId_Tesis() {
		return id_Tesis;
	}

	public void setId_Tesis(int id_Tesis) {
		this.id_Tesis = id_Tesis;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Date getFechaCreacion() {
		return fecha_Creacion;
	}

	public void setFechaCreacion(Date fecha_Creacion) {
		this.fecha_Creacion = fecha_Creacion;
	}

	public Date getFechaRegistro() {
		return fecha_Registro;
	}

	public void setFechaRegistro(Date fecha_Registro) {
		this.fecha_Registro = fecha_Registro;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
	

   
}
