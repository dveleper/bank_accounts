package com.bank.domain.usecase;

import com.bank.domain.model.EstadoCuenta;
import com.bank.domain.model.EstadoCuentaInput;

import java.util.List;

public interface EstadoCuentaUseCase {
    List<EstadoCuenta> obtenerReporte(EstadoCuentaInput input);
}
