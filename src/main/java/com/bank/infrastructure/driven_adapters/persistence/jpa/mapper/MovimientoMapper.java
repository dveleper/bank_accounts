package com.bank.infrastructure.driven_adapters.persistence.jpa.mapper;

import com.bank.domain.model.Movimiento;
import com.bank.infrastructure.driven_adapters.persistence.jpa.MovimientoData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    @Mappings({
            @Mapping(source = "movimientoId", target = "idMovimiento"),
            @Mapping(source = "cuenta.numero", target = "cuenta")
    })
    Movimiento toMovimiento(MovimientoData movimiento);

    List<Movimiento> toMovimientos(List<MovimientoData> movimientos);

    @InheritInverseConfiguration
    MovimientoData toMovimientoData(Movimiento movimiento);

}
