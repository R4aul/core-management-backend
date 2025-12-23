package com.business_control_system.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {

    @NotBlank(message = "El nombre no debe estar vacio")
    @NotNull(message = "El campo nombre es oblicagotio")
    private String name;

    @NotBlank(message = "La descripcion no debe estar vacio")
    @NotNull(message = "El capo descripcion es obligatorio")
    private String description;

    private Double price;

    @Min(value = 1, message = "Deve de existir almenos un producto en excistencia")
    private Integer stock;

    @NotNull(message = "La categoria es requerida")
    private Integer categoryId;

}
