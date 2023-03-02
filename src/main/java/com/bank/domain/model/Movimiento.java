package com.bank.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class Movimiento {
    private Integer idMovimiento;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date fecha;
    @NotBlank
    private String tipoMovimiento;
    @Min(1)
    private BigInteger valor;
    private BigInteger saldo;
    @NotBlank
    private String cuenta;
}
