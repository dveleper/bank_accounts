package com.bank.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class EstadoCuentaInput {
    @NotBlank
    private String identificacion;
    private Date fechaInicial;
    private Date fechaFinal;
}
