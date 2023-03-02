package com.bank.domain.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Persona {
    @NotBlank
    private String nombre;
    @NotBlank
    private String genero;
    @Min(1)
    @Max(100)
    private Integer edad;
    @NotBlank
    private String identificacion;
    @NotBlank
    private String direccion;
    @NotBlank
    private String telefono;
}
