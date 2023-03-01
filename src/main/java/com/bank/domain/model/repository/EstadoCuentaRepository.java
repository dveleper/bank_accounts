package com.bank.domain.model.repository;

import com.bank.domain.model.EstadoCuenta;
import com.bank.domain.model.EstadoCuentaInput;

import java.util.List;

public interface EstadoCuentaRepository {
    List<EstadoCuenta> obtenerReporte(EstadoCuentaInput input);
}
