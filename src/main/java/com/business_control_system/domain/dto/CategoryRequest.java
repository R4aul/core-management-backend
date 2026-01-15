package com.business_control_system.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotNull(message = "El campo nombre es requerido")
        @NotBlank(message = "El campo nombre no debe estar en blanco")
        String name,

        @NotNull(message = "El campo descripcion es requerido")
        @NotBlank(message = "El campo descripcion no debe estar en blanco")
        String description
) {
}
