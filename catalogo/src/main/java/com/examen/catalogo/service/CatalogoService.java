package com.examen.catalogo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.catalogo.dto.CatalogoDTO;
import com.examen.catalogo.model.CatalogoModel;
import com.examen.catalogo.repository.CatalogoRepo;

@Service
public class CatalogoService {

    @Autowired
    private CatalogoRepo catalogoRepo;

    public void agregarPizza(CatalogoDTO dto){
        CatalogoModel model = new CatalogoModel();
        model.setNombre(dto.getNombre());
        model.setDetalle(dto.getDetalle());
        model.setPrecio(dto.getPrecio());
        model.setDisponible(dto.isDisponible());

        catalogoRepo.save(model);
        System.out.println("Pizza agregada con exito");
    }

    public List<CatalogoModel> mostrarCatalogo(){
        return catalogoRepo.findAll();

    }

    public List<CatalogoModel> buscarPorNombre(String nombre){
        return catalogoRepo.findByNombreContainingIgnoreCase(nombre);
    }

    
    public void eliminarPizzaPorId(Long idCatalogo){
        catalogoRepo.deleteById(idCatalogo);
        System.out.println("Pizza eliminada");
    }

    public void actualizar(Long id, CatalogoDTO dto){
        CatalogoModel model = new CatalogoModel();

        model.setIdCatalogo(id);

        model.setNombre(dto.getNombre());
        model.setDetalle(dto.getDetalle());
        model.setPrecio(dto.getPrecio());
        model.setDisponible(dto.isDisponible());

        catalogoRepo.save(model);
        System.out.println("Pizza actualizada con exito en la base de datos");
    }

}
