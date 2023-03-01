package com.bank.domain.usecase.impl;

import com.bank.domain.model.Cuenta;
import com.bank.domain.model.Movimiento;
import com.bank.domain.model.repository.MovimientoRepository;
import com.bank.domain.usecase.CuentaUseCase;
import com.bank.domain.usecase.MovimientoUseCase;
import com.bank.exception.BankAccountException;
import com.bank.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class MovimientoUseCaseImpl implements MovimientoUseCase {
    private final MovimientoRepository movimientoRepository;
    private final CuentaUseCase cuentaUseCase;


    @Override
    @Transactional
    public Movimiento crear(Movimiento movimiento) {
        movimiento.setFecha(new Date());
        Cuenta cuenta = cuentaUseCase.listarPorNumeroCuenta(movimiento.getCuenta().getNumero());
        afectarSaldo(movimiento, cuenta);
        return movimientoRepository.crear(movimiento);
    }


    private void afectarSaldo(Movimiento movimiento, Cuenta cuenta) {
        movimiento.setSaldo(cuenta.getSaldo());
        BigInteger nuevoSaldo;
        switch (movimiento.getTipoMovimiento()) {
            case Utils.DEBITO -> nuevoSaldo = cuentaUseCase.realizarDebito(cuenta.getSaldo(), movimiento.getValor());
            case Utils.CREDITO -> nuevoSaldo = cuentaUseCase.realizarCredito(cuenta.getSaldo(), movimiento.getValor());
            default -> throw new BankAccountException(String.format("tipo de movimiento: %s no disponible", movimiento.getTipoMovimiento()));
        }
        cuentaUseCase.actualizarSaldo(movimiento.getCuenta().getNumero(), nuevoSaldo);
        cuenta.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);
    }



    @Override
    @Transactional(readOnly = true)
    public List<Movimiento> listar() {
        return movimientoRepository.listar();
    }

    @Override
    @Transactional(readOnly = true)
    public Movimiento listarPorId(Integer idMovimiento) {
        return movimientoRepository.listarPorId(idMovimiento);
    }


    @Override
    @Transactional
    public boolean eliminar(Integer idMovimiento) {
        return movimientoRepository.eliminar(idMovimiento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movimiento> listarPorCuenta(String cuenta) {
        return movimientoRepository.consultaPoCuenta(cuenta);
    }
}
