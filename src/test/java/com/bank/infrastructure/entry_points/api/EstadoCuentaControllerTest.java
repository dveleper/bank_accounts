package com.bank.infrastructure.entry_points.api;


import com.bank.domain.model.EstadoCuenta;
import com.bank.domain.model.EstadoCuentaInput;
import com.bank.domain.usecase.EstadoCuentaUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EstadoCuentaController.class)
class EstadoCuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstadoCuentaUseCase estadoCuentaUseCase;

    ObjectMapper objectMapper;

    private EstadoCuentaInput input;

    private List<EstadoCuenta> estadoCuenta;

    @BeforeEach
    void setUp() throws ParseException {
        objectMapper = new ObjectMapper();
        buildEstadoCuenta();
        input = new EstadoCuentaInput();
        input.setIdentificacion("123");
    }

    @Test
    void getEstadoCuenta() throws Exception {
        when(estadoCuentaUseCase.obtenerReporte(any())).thenReturn(estadoCuenta);
        mockMvc.perform(post("/reportes?ini=16022023&fin=17022023").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("[0].Cliente").value("Pedro Paramo"))
                .andExpect(jsonPath("[0].Movimiento").value(new BigInteger("500000")));
        verify(estadoCuentaUseCase).obtenerReporte(any());
    }

    private void buildEstadoCuenta() throws ParseException {
        EstadoCuenta movimientoOne = new EstadoCuenta();
        movimientoOne.setFecha(new SimpleDateFormat("yyyy/MM/dd").parse("2023/02/17"));
        movimientoOne.setCliente("Pedro Paramo");
        movimientoOne.setNumeroCuenta("00000024560");
        movimientoOne.setTipo("CORRIENTE");
        movimientoOne.setSaldoInicial(BigInteger.ZERO);
        movimientoOne.setMovimiento(new BigInteger("500000"));
        movimientoOne.setSaldoDisponible(new BigInteger("500000"));
        estadoCuenta = Arrays.asList(movimientoOne);
    }
}