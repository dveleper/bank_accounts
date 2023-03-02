package com.bank.domain.model.repository;


import com.bank.domain.model.Cuenta;

import java.math.BigInteger;
import java.util.List;

public interface CuentaRepository {
    Cuenta crear(Cuenta cuenta);
    List<Cuenta> listarTodos();
    Cuenta listar(String numeroCuenta);
    List<Cuenta> listarPorCliente(String identificacionCliente);
    Cuenta editar(Cuenta cuenta);
    boolean eliminar(String numeroCuenta);
    void actualizarSaldo(String numeroCuenta, BigInteger nuevoSaldo);
}
