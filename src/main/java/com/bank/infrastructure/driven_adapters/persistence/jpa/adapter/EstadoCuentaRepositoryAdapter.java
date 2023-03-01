package com.bank.infrastructure.driven_adapters.persistence.jpa.adapter;

import com.bank.domain.model.EstadoCuenta;
import com.bank.domain.model.EstadoCuentaInput;
import com.bank.domain.model.repository.EstadoCuentaRepository;
import com.bank.infrastructure.driven_adapters.persistence.jpa.MovimientoData;
import com.bank.infrastructure.driven_adapters.persistence.jpa.mapper.EstadoCuentaMapper;
import com.bank.infrastructure.driven_adapters.persistence.jpa.repository.MovimientoDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstadoCuentaRepositoryAdapter implements EstadoCuentaRepository {

    @Autowired
    private MovimientoDataRepository movimientoDataRepository;

    @Autowired
    private EstadoCuentaMapper estadoCuentaMapper;

    @Override
    public List<EstadoCuenta> obtenerReporte(EstadoCuentaInput input) {
        List<MovimientoData> movimientos =
                movimientoDataRepository.findAllByCuentaClienteIdentificacionAndFechaBetweenOrderByCuentaNumeroAsc(
                         input.getIdentificacion(),
                         input.getFechaInicial(),
                         input.getFechaFinal());

        return estadoCuentaMapper.toEstadoCuentas(movimientos);
    }
}
