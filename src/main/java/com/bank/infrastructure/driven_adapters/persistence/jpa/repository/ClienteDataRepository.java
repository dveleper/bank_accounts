package com.bank.infrastructure.driven_adapters.persistence.jpa.repository;

import com.bank.infrastructure.driven_adapters.persistence.jpa.ClienteData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteDataRepository extends CrudRepository<ClienteData, Integer> {
    Optional<ClienteData> findByIdentificacion(String identificacion);
}
