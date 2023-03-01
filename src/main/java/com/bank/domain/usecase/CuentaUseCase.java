package com.bank.domain.usecase;


import com.bank.domain.model.Cuenta;

import java.math.BigInteger;
import java.util.List;

public interface CuentaUseCase {
    Cuenta crear(Cuenta cuenta);
    List<Cuenta> listar();
    Cuenta listarPorNumeroCuenta(String numeroCuenta);
    List<Cuenta> listarPorCliente(String identificacionCliente);
    Cuenta editar(Cuenta cuenta);
    boolean eliminar(String numeroCuenta);
    void actualizarSaldo(String numeroCuenta, BigInteger nuevoSaldo);
    BigInteger realizarDebito(BigInteger saldoActual, BigInteger monto);
    BigInteger realizarCredito(BigInteger saldoActual, BigInteger monto);
}
