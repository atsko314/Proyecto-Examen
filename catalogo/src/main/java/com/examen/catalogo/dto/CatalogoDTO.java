package com.examen.catalogo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CatalogoDTO {


    @NotBlank(message="El nombre no puede estar en blanco")
    @Size(min=8, max=50, message="El nombre debe cumplir con los parametros de caracteres")
    private String nombre;

    @NotBlank(message="El detalle no puede estar en blanco")
    @Size(min=30, max=400, message="El detalle debe cumplir con los parametros de caracteres")
    private String detalle;


    @NotNull(message="El precio no puede estar vacio")
    @Min(value=0, message="El precio no puede ser negativo")
    private Integer precio;

    @NotNull(message="El estado de disponibilidad es obligatorio")
    private boolean disponible;

}
