package com.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

}
