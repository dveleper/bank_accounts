package com.bank.domain.usecase;


import com.bank.domain.model.Movimiento;

import java.util.List;

public interface MovimientoUseCase {
    Movimiento crear(Movimiento movimiento);
    List<Movimiento> listar();
    Movimiento listarPorId(Integer idMovimiento);
    boolean eliminar(Integer idMovimiento);
    List<Movimiento> listarPorCuenta(String cuenta);

}
