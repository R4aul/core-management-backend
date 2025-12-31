package com.business_control_system.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull(message = "El email de usuario debe ser obligatorio")
        @NotBlank(message = "El email no debe estar en balnco")
        @Email(message = "El campo no es de tipo email")
        String username,

        @NotNull(message = "La campo contraseña es obligatorio")
        @NotBlank(message = "El campo contraseña no debe estar vacio")
        String password
) {
}
