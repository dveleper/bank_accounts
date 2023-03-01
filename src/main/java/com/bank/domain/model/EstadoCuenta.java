package com.bank.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class EstadoCuenta {

    @JsonFormat(pattern="dd/MM/yyyy")
    @JsonProperty("Fecha")
    private Date fecha;
    @JsonProperty("Cliente")
    private String cliente;
    @JsonProperty("Numero Cuenta")
    private String numeroCuenta;
    @JsonProperty("Tipo")
    private String tipo;
    @JsonProperty("Saldo Inicial")
    private BigInteger saldoInicial;
    @JsonProperty("Movimiento")
    private BigInteger movimiento;
    @JsonProperty("Saldo Disponible")
    private BigInteger saldoDisponible;

}
