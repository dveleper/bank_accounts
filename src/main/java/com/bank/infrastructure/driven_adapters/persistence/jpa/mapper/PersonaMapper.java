package com.bank.infrastructure.driven_adapters.persistence.jpa.mapper;

import com.bank.domain.model.Persona;
import com.bank.infrastructure.driven_adapters.persistence.jpa.PersonaData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    Persona toPersona(PersonaData personaData);

    @InheritInverseConfiguration
    PersonaData toPersonaData(Persona persona);
}
