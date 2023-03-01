package com.bank.util;

import com.bank.exception.BankAccountCuentaException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

public class Utils {
    public static final String CREDITO = "CREDITO";
    public static final String DEBITO = "DEBITO";

    public static void validarTipoCuenta(String tipoCuenta) {
        for (TipoCuenta tipo: new ArrayList<>(Arrays.asList(TipoCuenta.values()))) {
            if (tipo.name().equals(tipoCuenta.toUpperCase())) return;
        }
        throw new BankAccountCuentaException("No se puede crear cuenta, tipo de cuenta : " + tipoCuenta + " no existe!");
    }

    public static Date getDateFormat(String fecha) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
        return formato.parse(fecha);
    }

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .toList();
    }
}
