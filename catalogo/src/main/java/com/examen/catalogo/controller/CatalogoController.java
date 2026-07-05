package com.examen.catalogo.controller;

import java.util.List;

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

import com.examen.catalogo.dto.CatalogoDTO;
import com.examen.catalogo.model.CatalogoModel;
import com.examen.catalogo.service.CatalogoService;

import jakarta.validation.Valid;







@RestController
@RequestMapping("/catalogo")
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;


    @GetMapping("/listar")
    public ResponseEntity<List<CatalogoModel>> listarPizzas(){
        List<CatalogoModel> lista = catalogoService.mostrarCatalogo();
        return new ResponseEntity<>(lista, HttpStatus.OK);//estado 200
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarPizza(@Valid@RequestBody CatalogoDTO dto){
        catalogoService.agregarPizza(dto);
        return new ResponseEntity<>("Pizza agregada correctamente", HttpStatus.CREATED);//estado 201 created
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id ,@Valid@RequestBody CatalogoDTO dto){
        try{
            catalogoService.actualizar(id, dto);
            return new ResponseEntity<>("La pizza: "+ id +" actualizada con exito",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Error al actualizar, verifica que los datos sean correctos",
                HttpStatus.INTERNAL_SERVER_ERROR);//estado 500
        }
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Long idCatalogo){

        try{
            catalogoService.eliminarPizzaPorId(idCatalogo);
            return new ResponseEntity<>("Pizza: "+idCatalogo+" eliminada",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Error, no se pudo concretar la solicitud",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CatalogoModel>> buscar(@RequestBody CatalogoDTO dto){
        List<CatalogoModel> resultado = catalogoService.buscarPorNombre(dto.getNombre());
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
    
}
