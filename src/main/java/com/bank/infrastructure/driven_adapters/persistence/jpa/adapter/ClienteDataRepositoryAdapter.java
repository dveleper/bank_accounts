package com.bank.infrastructure.driven_adapters.persistence.jpa.adapter;

import com.bank.domain.model.Cliente;
import com.bank.domain.model.repository.ClienteRepository;
import com.bank.exception.BankAccountException;
import com.bank.infrastructure.driven_adapters.persistence.jpa.ClienteData;
import com.bank.infrastructure.driven_adapters.persistence.jpa.mapper.ClienteMapper;
import com.bank.infrastructure.driven_adapters.persistence.jpa.mapper.PersonaMapper;
import com.bank.infrastructure.driven_adapters.persistence.jpa.repository.ClienteDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteDataRepositoryAdapter implements ClienteRepository {

    @Autowired
    private ClienteDataRepository repository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private PersonaMapper personaMapper;

    @Override
    public Cliente crear(Cliente cliente) {
        ClienteData data = clienteMapper.toClienteData(cliente);
        return clienteMapper.toCliente(repository.save(data));
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = clienteMapper.toClientes((List<ClienteData>) repository.findAll());
        if(clientes.size() == 0) throw new BankAccountException("No existen registros de Clientes!");
        return clientes;
    }

    @Override
    public Cliente listarPorIdentificacion(String identificacion) {
        return repository.findByIdentificacion(identificacion)
                .map(clienteData -> clienteMapper.toCliente(clienteData))
                .orElseThrow(()->new BankAccountException("Cliente :"+identificacion+" Not Found!"));
    }

    @Override
    public Cliente editar(Cliente cliente) {
        ClienteData data = clienteMapper.toClienteData(cliente);
        return clienteMapper.toCliente(repository.save(data));
    }

    @Override
    public boolean eliminar(String identification) {
        return repository.findByIdentificacion(identification)
                .map(clienteData -> {
                    repository.delete(clienteData);
                    return true;
                }).orElseThrow(()->new BankAccountException("Cliente :"+identification+" Not Found!"));
    }
}
