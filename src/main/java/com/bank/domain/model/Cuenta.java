package com.bank.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@NoArgsConstructor
@Getter
@Setter
public class Cuenta {
    @NotBlank
    private String numero;
    @NotBlank
    private String tipoCuenta;
    private BigInteger saldo = BigInteger.ZERO;
    private String estado = "true";
    private Cliente cliente;

}
