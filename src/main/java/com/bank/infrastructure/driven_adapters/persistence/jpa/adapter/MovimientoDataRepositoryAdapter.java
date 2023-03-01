package com.bank.infrastructure.driven_adapters.persistence.jpa.adapter;

import com.bank.domain.model.Movimiento;
import com.bank.domain.model.repository.MovimientoRepository;
import com.bank.exception.BankAccountMovimientosException;
import com.bank.infrastructure.driven_adapters.persistence.jpa.MovimientoData;
import com.bank.infrastructure.driven_adapters.persistence.jpa.mapper.MovimientoMapper;
import com.bank.infrastructure.driven_adapters.persistence.jpa.repository.MovimientoDataRepository;
import com.bank.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovimientoDataRepositoryAdapter implements MovimientoRepository {

    @Autowired
    private MovimientoMapper movimientoMapper;

    @Autowired
    private MovimientoDataRepository movimientoDataRepository;

    @Override
    public Movimiento crear(Movimiento movimiento) {
        MovimientoData data = movimientoMapper.toMovimientoData(movimiento);
        return movimientoMapper.toMovimiento(movimientoDataRepository.save(data));
    }

    @Override
    public List<Movimiento> listar() {
        List<MovimientoData> movimientos = (List<MovimientoData>) movimientoDataRepository.findAll();
        if (movimientos.size() == 0) throw new BankAccountMovimientosException("No existen registros de Movimientos!");
        return movimientoMapper.toMovimientos(Utils.toList(movimientos));
    }

    @Override
    public Movimiento listarPorId(Integer idMovimiento) {
        MovimientoData data = movimientoDataRepository.findById(idMovimiento)
                .orElseThrow(() ->
                        new BankAccountMovimientosException("Movimiento con id :" + idMovimiento + " No existe!"));
        return movimientoMapper.toMovimiento(data);
    }

    @Override
    public boolean eliminar(Integer idMovimiento) {
        return movimientoDataRepository.findById(idMovimiento)
                .map(movimientoData -> {
                    movimientoDataRepository.delete(movimientoData);
                    return true;
                }).orElseThrow(() ->
                        new BankAccountMovimientosException(String.format("Movimiento con id : %s No existe!", idMovimiento)));
    }

    @Override
    public List<Movimiento> consultaPorCuenta(String cuenta) {
        List<MovimientoData> data = movimientoDataRepository.findByCuentaNumero(cuenta);
        if (data.size() == 0) throw new BankAccountMovimientosException(
                String.format("No existen registros de Movimientos para la cuenta %s", cuenta));
        return movimientoMapper.toMovimientos(data);
    }
}
