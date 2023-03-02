package com.bank.infrastructure.entry_points.api;

import com.bank.domain.model.Cliente;
import com.bank.domain.model.Cuenta;
import com.bank.domain.usecase.CuentaUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(CuentaController.class)
class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaUseCase cuentaUseCase;

    private Cuenta cuenta;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        buildCuenta();
    }

    @Test
    void save() throws Exception {
        when(cuentaUseCase.crear(any())).thenReturn(cuenta);
        mockMvc.perform(post("/cuentas/save").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numero", is("00000024560")))
                .andExpect(jsonPath("$.tipoCuenta", is("AHORROS")));
        verify(cuentaUseCase).crear(any());
    }

    @Test
    void getByIdentification() throws Exception {
        when(cuentaUseCase.listarPorCliente(anyString())).thenReturn(buildCuentas());
        mockMvc.perform(get("/cuentas/find/cliente/123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].numero").value("00000024560"))
                .andExpect(jsonPath("$[1].numero").value("00000024561"));
        verify(cuentaUseCase).listarPorCliente(any());
    }

    @Test
    void getByNumeroCuenta() throws Exception {
        when(cuentaUseCase.listarPorNumeroCuenta(anyString())).thenReturn(cuenta);
        mockMvc.perform(get("/cuentas/find/00000024560").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.tipoCuenta").value("AHORROS"))
                .andExpect(jsonPath("$.cliente.identificacion").value("123"));
        verify(cuentaUseCase).listarPorNumeroCuenta("00000024560");
    }

    @Test
    void getAll() throws Exception {
        when(cuentaUseCase.listar()).thenReturn(buildCuentas());
        mockMvc.perform(get("/cuentas/find").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("[0].tipoCuenta").value("AHORROS"))
                .andExpect(jsonPath("[0].estado").value("true"))
                .andExpect(jsonPath("[1].tipoCuenta").value("CORRIENTE"))
                .andExpect(jsonPath("[1].estado").value("false"));
        verify(cuentaUseCase).listar();
    }

    @Test
    void delete() throws Exception {
        when(cuentaUseCase.eliminar(anyString())).thenReturn(true);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/cuentas/delete/00000024560")
                        .param("numero", "00000024560")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(cuentaUseCase).eliminar("00000024560");
    }

    @Test
    void update() throws Exception {
        Cuenta cuentaTest =  new Cuenta();
        cuentaTest.setNumero("00000024560");
        cuentaTest.setEstado("false");
        when(cuentaUseCase.editar(any())).thenReturn(cuentaTest);
        mockMvc.perform(put("/cuentas/update/00000024560").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.estado", is("false")));
        verify(cuentaUseCase).editar(any());
    }

    private void buildCuenta() {
        cuenta = new Cuenta();
        cuenta.setNumero("00000024560");
        cuenta.setTipoCuenta("AHORROS");
        cuenta.setSaldo(BigInteger.ZERO);
        cuenta.setEstado("true");
        cuenta.setCliente(buildCliente());
    }

    private Cliente buildCliente() {
        Cliente cliente = new Cliente();
        cliente.setContrasena("12345678");
        cliente.setGenero("masculino");
        cliente.setEdad(40);
        cliente.setDireccion("cra 3 #76-20 Jardin etapa 1");
        cliente.setNombre("felix the cat");
        cliente.setIdentificacion("123");
        cliente.setEstado("true");
        cliente.setTelefono("0000000000");
        return cliente;
    }

    private List<Cuenta> buildCuentas() {
        Cuenta cuentaOne = new Cuenta();
        cuentaOne.setNumero("00000024560");
        cuentaOne.setTipoCuenta("AHORROS");
        cuentaOne.setSaldo(BigInteger.ZERO);
        cuentaOne.setEstado("true");
        cuentaOne.setCliente(buildCliente());

        Cuenta cuentaTwo = new Cuenta();
        cuentaTwo.setNumero("00000024561");
        cuentaTwo.setTipoCuenta("CORRIENTE");
        cuentaTwo.setSaldo(BigInteger.ZERO);
        cuentaTwo.setEstado("false");
        cuentaTwo.setCliente(buildCliente());

        return Arrays.asList(cuentaOne, cuentaTwo);
    }
}