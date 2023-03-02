package com.bank.infrastructure.driven_adapters.persistence.jpa.repository;


import com.bank.infrastructure.driven_adapters.persistence.jpa.MovimientoData;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface MovimientoDataRepository extends CrudRepository<MovimientoData, Integer> {
    List<MovimientoData> findByCuentaNumero(String cuentaId);
    List<MovimientoData> findAllByCuentaClienteIdentificacionAndFechaBetweenOrderByCuentaNumeroAsc(String identificacion, Date ini, Date fin);
}
