package com.bank.infrastructure.entry_points.api;

import com.bank.domain.model.Cliente;
import com.bank.domain.usecase.ClienteUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteUseCase clienteUseCase;

    private Cliente cliente;

    private List clientes;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        buildCliente();
        buildClientes();
    }

    @Test
    void save() throws Exception {
        when(clienteUseCase.crear(any())).thenReturn(cliente);
        mockMvc.perform(post("/clientes/save").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre", is("felix the cat")))
                .andExpect(jsonPath("$.identificacion", is("123")));
        verify(clienteUseCase).crear(any());
    }

    @Test
    void getByIdentification() throws Exception {
        when(clienteUseCase.listarPorIdentificacion(anyString())).thenReturn(cliente);
        mockMvc.perform(get("/clientes/find/123").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("felix the cat"))
                .andExpect(jsonPath("$.estado").value("true"));
        verify(clienteUseCase).listarPorIdentificacion("123");
    }

    @Test
    void getAll() throws Exception {
        when(clienteUseCase.listar()).thenReturn(clientes);
        mockMvc.perform(get("/clientes/find").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("[0].nombre").value("felix the cat"))
                .andExpect(jsonPath("[0].estado").value("true"))
                .andExpect(jsonPath("[1].nombre").value("max the dog"))
                .andExpect(jsonPath("[1].estado").value("false"));

        verify(clienteUseCase).listar();
    }

    @Test
    void delete() throws Exception {
        when(clienteUseCase.eliminar(anyString())).thenReturn(true);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/clientes/delete/123")
                        .param("identificacion", "123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(clienteUseCase).eliminar("123");
    }

    @Test
    void update() throws Exception {
        Cliente client = new Cliente();
        client.setNombre("felix of the cat");
        client.setEstado("false");
        when(clienteUseCase.editar(any())).thenReturn(client);
        mockMvc.perform(put("/clientes/update/123").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre", is("felix of the cat")))
                .andExpect(jsonPath("$.estado", is("false")));
        verify(clienteUseCase).editar(any());
    }

    private void buildCliente() {
        cliente = new Cliente();
        cliente.setContrasena("12345678");
        cliente.setGenero("masculino");
        cliente.setEdad(40);
        cliente.setDireccion("cra 3 #76-20 Jardin etapa 1");
        cliente.setNombre("felix the cat");
        cliente.setIdentificacion("123");
        cliente.setEstado("true");
        cliente.setTelefono("0000000000");
    }

    private void buildClientes() {
        Cliente clientOne = new Cliente();
        clientOne.setNombre("felix the cat");
        clientOne.setIdentificacion("123");
        clientOne.setEstado("true");

        Cliente clientTwo = new Cliente();
        clientTwo.setNombre("max the dog");
        clientTwo.setIdentificacion("456");
        clientTwo.setEstado("false");

        clientes = Arrays.asList(clientOne, clientTwo);
    }
}