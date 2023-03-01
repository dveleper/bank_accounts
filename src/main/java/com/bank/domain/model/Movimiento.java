package com.bank.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movimiento {
    private Integer idMovimiento;
    private Date fecha;
    private String tipoMovimiento;
    private BigInteger valor;
    private BigInteger saldo;
    private Cuenta cuenta;
}
