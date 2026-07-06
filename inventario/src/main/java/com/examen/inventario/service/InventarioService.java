package com.examen.inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.inventario.dto.InventarioDTO;
import com.examen.inventario.model.InventarioModel;
import com.examen.inventario.repository.InventarioRepo;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepo inventarioRepo;

    public List<InventarioModel> mostrarInventario(){
        return inventarioRepo.findAll();
    }

    public Optional<InventarioModel> buscarPorId(Long idInventario){
        return inventarioRepo.findById(idInventario);
        
    }

    public Optional<InventarioModel> buscarPorIdProducto(Long idProducto){
        return inventarioRepo.findByIdProducto(idProducto);
    }

    public void agregarProducto(InventarioDTO dto){
        InventarioModel model = new InventarioModel();

        model.setIdProducto(dto.getIdProducto());
        model.setNombreProducto(dto.getNombreProducto());
        model.setStockMinimo(dto.getStockMinimo());
        model.setCantidadDispo(dto.getCantidadDispo());

        inventarioRepo.save(model);
        System.out.println("Producto guardado con exito");
    }

    public boolean actualizarProducto(Long id, InventarioDTO dto){
        if(inventarioRepo.existsById(id)){
            InventarioModel model = new InventarioModel();

            model.setIdInventario(id);
            model.setIdProducto(dto.getIdProducto());
            model.setNombreProducto(dto.getNombreProducto());
            model.setStockMinimo(dto.getStockMinimo());
            model.setCantidadDispo(dto.getCantidadDispo());

            inventarioRepo.save(model);
            System.out.println("Producto actualizada con exito");
            return true;
        }else{
            System.out.println("Error: el ID: "+id+" no existe en el inventario");
            return false;
        }
    }

    public boolean eliminarProducto(Long idInventario){
        if(inventarioRepo.existsById(idInventario)){
            inventarioRepo.deleteById(idInventario);
            System.out.println("Producto eliminado");
            return true;
        }else{
            System.out.println("Error: el ID: "+idInventario+" no existe en el inventario");
            return false;
        }
        

    }

}
