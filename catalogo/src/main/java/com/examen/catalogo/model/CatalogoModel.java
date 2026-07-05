package com.examen.catalogo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CatalogoModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCatalogo;

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
