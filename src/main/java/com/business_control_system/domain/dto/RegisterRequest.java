package com.business_control_system.domain.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotNull(message = "El campo nombre es requerido")
    @NotBlank(message = "El campo nombre no debe estar en blaco")
    private String name;

    @NotNull(message = "El campo email es requerido")
    @NotBlank(message = "El campo email no debe estar en blanco")
    @Email(message = "El campo email no es del tipo email")
    private String email;

    @NotNull(message = "El campo contraseña es requerido")
    @NotBlank(message = "El campo contraseña no debe estar en blanco")
    @Size(
            min = 6,
            max = 16,
            message = "La contraseña debe tener al menos 6 caracteres y maximo 16"
    )
    private String password;
}
