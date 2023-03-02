package com.bank.domain.usecase;


import com.bank.domain.model.Cliente;

import java.util.List;

public interface ClienteUseCase {
    Cliente crear(Cliente cliente);
    List<Cliente> listar();
    Cliente listarPorIdentificacion(String identificacion);
    Cliente editar(Cliente cliente);
    boolean eliminar(String identificacion);
}
