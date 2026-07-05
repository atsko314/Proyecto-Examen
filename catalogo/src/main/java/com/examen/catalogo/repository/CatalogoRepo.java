package com.examen.catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.catalogo.model.CatalogoModel;

@Repository
public interface  CatalogoRepo extends JpaRepository<CatalogoModel, Long> {
List<CatalogoModel> findByNombreContainingIgnoreCase(String nombre);



}
