package com.examen.inventario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.inventario.model.InventarioModel;


@Repository
public interface InventarioRepo extends JpaRepository<InventarioModel, Long>{

    Optional<InventarioModel> findByIdProducto(Long idProducto);

}
