package com.bank.util;

import com.bank.exception.BankAccountCuentaException;

import java.util.ArrayList;
import java.util.Arrays;

public class Utils {
    public static void validarTipoCuenta(String tipoCuenta) {
        for (TipoCuenta tipo: new ArrayList<>(Arrays.asList(TipoCuenta.values()))) {
            if (tipo.name().equals(tipoCuenta.toUpperCase())) return;
        }
        throw new BankAccountCuentaException("No se puede crear cuenta, tipo de cuenta : " + tipoCuenta + " no existe!");
    }
}
