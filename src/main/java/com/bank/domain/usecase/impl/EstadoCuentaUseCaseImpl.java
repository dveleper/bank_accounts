package com.bank.domain.usecase.impl;

import com.bank.domain.model.EstadoCuenta;
import com.bank.domain.model.EstadoCuentaInput;
import com.bank.domain.model.repository.EstadoCuentaRepository;
import com.bank.domain.usecase.EstadoCuentaUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EstadoCuentaUseCaseImpl implements EstadoCuentaUseCase {

    private final EstadoCuentaRepository estadoCuentaRepository;

    @Override
    @Transactional
    public List<EstadoCuenta> obtenerReporte(EstadoCuentaInput input) {
        return estadoCuentaRepository.obtenerReporte(input);
    }
}
