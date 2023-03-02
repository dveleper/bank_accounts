package com.bank.util;

public enum TipoCuenta {
    AHORROS("AHORROS"),
    CORRIENTE("CORRIENTE");

    private final String nombre;

    TipoCuenta(String nombre) {
        this.nombre = nombre;
    }

}
