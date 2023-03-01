package com.bank.infrastructure.driven_adapters.persistence.jpa.mapper;


import com.bank.domain.model.Cliente;
import com.bank.infrastructure.driven_adapters.persistence.jpa.ClienteData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toCliente(ClienteData clienteData);

    List<Cliente> toClientes(List<ClienteData> clienteDataList);

    @InheritInverseConfiguration
    ClienteData toClienteData(Cliente cliente);
}
