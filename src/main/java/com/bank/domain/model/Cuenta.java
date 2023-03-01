package com.bank.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@NoArgsConstructor
@Getter
@Setter
public class Cuenta {
    private String numero;
    private String tipoCuenta;
    private BigInteger saldo;
    private String estado;
    private Cliente cliente;

}
