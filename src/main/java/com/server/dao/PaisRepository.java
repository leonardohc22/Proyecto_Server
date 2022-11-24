package com.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.entity.Pais;

public interface PaisRepository extends JpaRepository<Pais, Integer> {

}
