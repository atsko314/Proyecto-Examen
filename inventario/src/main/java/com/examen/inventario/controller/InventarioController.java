package com.examen.inventario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.inventario.dto.InventarioDTO;
import com.examen.inventario.model.InventarioModel;
import com.examen.inventario.service.InventarioService;

import jakarta.validation.Valid;






@RestController
@RequestMapping("/inventario")
public class InventarioController {


    @Autowired
    private InventarioService inventarioService;


    @GetMapping("/listar")
    public ResponseEntity<List<InventarioModel>> listarInventario(){
        List<InventarioModel> lista = inventarioService.mostrarInventario();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorIdInventario(@PathVariable("id") Long idInventario) {
        Optional<InventarioModel> resultado = inventarioService.buscarPorId(idInventario);
    
        if (resultado.isPresent()) {
            return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Error: no se encontro registro", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscarP/{idP}")
    public ResponseEntity<?> buscar(@PathVariable("idP") Long idProducto){
        Optional<InventarioModel> resultado = inventarioService.buscarPorIdProducto(idProducto);

        if (resultado.isPresent()) {
            return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Error: no se encontro registro", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregar(@Valid@RequestBody InventarioDTO dto){
        inventarioService.agregarProducto(dto);
        return new ResponseEntity<>("Producto agregado correctamente", HttpStatus.OK);

    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id ,@Valid@RequestBody InventarioDTO dto){
        try{
            boolean actualizado = inventarioService.actualizarProducto(id, dto);
            if(actualizado){
                return new ResponseEntity<>("El producto: "+id+" fue actualizado",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Error: el producto no existe",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>("Error al intentar actualizar", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long idInventario){
        try{
            boolean eliminado = inventarioService.eliminarProducto(idInventario);
            if(eliminado){
                return new ResponseEntity<>("El producto: fue eliminado",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Error: el producto no existe",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>("Error al intentar eliminar", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
