package com.bank.infrastructure.driven_adapters.persistence.jpa.mapper;

import com.bank.domain.model.EstadoCuenta;
import com.bank.infrastructure.driven_adapters.persistence.jpa.MovimientoData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoCuentaMapper {

    @Mappings({
            @Mapping(source = "fecha", target = "fecha"),
            @Mapping(source = "cuenta.cliente.nombre", target = "cliente"),
            @Mapping(source = "cuenta.numero", target = "numeroCuenta"),
            @Mapping(source = "cuenta.tipoCuenta", target = "tipo"),
            @Mapping(source = "saldo", target = "saldoInicial"),
            @Mapping(source = "valor", target = "movimiento"),
            @Mapping(source = "cuenta.saldo", target = "saldoDisponible")
    })
    EstadoCuenta toEstadoCuenta(MovimientoData movimientoData);

    List<EstadoCuenta> toEstadoCuentas(List<MovimientoData> movimientos);
}
