package com.bank.domain.usecase.impl;

import com.bank.domain.model.Cuenta;
import com.bank.domain.model.repository.CuentaRepository;
import com.bank.domain.usecase.CuentaUseCase;
import com.bank.exception.BankAccountException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
@AllArgsConstructor
public class CuentaUseCaseImpl implements CuentaUseCase {

    private final CuentaRepository cuentaRepository;
    @Override
    @Transactional
    public Cuenta crear(Cuenta cuenta) {
        return cuentaRepository.crear(cuenta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cuenta> listar() {
        return cuentaRepository.listarTodos();
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta listarPorNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.listar(numeroCuenta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cuenta> listarPorCliente(String identificacionCliente) {
        return cuentaRepository.listarPorCliente(identificacionCliente);
    }

    @Override
    @Transactional
    public Cuenta editar(Cuenta cuenta) {
        return cuentaRepository.editar(cuenta);
    }

    @Override
    @Transactional
    public boolean eliminar(String numeroCuenta) {
        return cuentaRepository.eliminar(numeroCuenta);
    }

    @Override
    @Transactional
    public void actualizarSaldo(String numeroCuenta, BigInteger nuevoSaldo) {
        cuentaRepository.actualizarSaldo(numeroCuenta, nuevoSaldo);
    }

    @Override
    public BigInteger realizarDebito(BigInteger saldoActual, BigInteger monto) {
        BigInteger nuevoSaldo = saldoActual.subtract(monto);
        if (nuevoSaldo.compareTo(BigInteger.ZERO) < 0 ) {
            throw new BankAccountException("Saldo no disponible");
        }
        return nuevoSaldo;
    }

    @Override
    public BigInteger realizarCredito(BigInteger saldoActual, BigInteger monto) {
        return saldoActual.add(monto);
    }
}
