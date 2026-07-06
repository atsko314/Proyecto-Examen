package com.examen.inventario.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventarioDTO {

    @NotNull(message="El id del producto es obligatorio")
    private Long idProducto;

    @NotBlank(message="El nombre no puede estar en blanco")
    private String nombreProducto;


    @NotNull(message="La cantidad es obligatoria")
    @Min(value = 0, message="El stock no puede ser menor a 0")
    private Integer cantidadDispo;


    @Min(value=0, message="El stock minimo no puede ser menor a 0")
    private Integer stockMinimo;
}
