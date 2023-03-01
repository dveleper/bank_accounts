package com.bank.infrastructure.driven_adapters.persistence.jpa.adapter;

import com.bank.domain.model.Cuenta;
import com.bank.domain.model.repository.CuentaRepository;
import com.bank.exception.BankAccountCuentaException;
import com.bank.infrastructure.driven_adapters.persistence.jpa.CuentaData;
import com.bank.infrastructure.driven_adapters.persistence.jpa.mapper.CuentaMapper;
import com.bank.infrastructure.driven_adapters.persistence.jpa.repository.ClienteDataRepository;
import com.bank.infrastructure.driven_adapters.persistence.jpa.repository.CuentaDataRepository;
import com.bank.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
public class CuentaDataRepositoryAdapter implements CuentaRepository {

    @Autowired
    private CuentaMapper cuentaMapper;

    @Autowired
    private CuentaDataRepository cuentaDataRepository;

    @Autowired
    private ClienteDataRepository clienteDataRepository;

    @Override
    public Cuenta crear(Cuenta cuenta) {
        // valida identificacion de cliente
        clienteDataRepository.findByIdentificacion(cuenta.getCliente().getIdentificacion())
                .orElseThrow(() ->
                        new BankAccountCuentaException("No se puede crear cuenta, persona con documento :"
                                + cuenta.getCliente().getIdentificacion() + " No es cliente!"));
        Utils.validarTipoCuenta(cuenta.getTipoCuenta());
        CuentaData data = cuentaMapper.toCuentaData(cuenta);
        return cuentaMapper.toCuenta(cuentaDataRepository.save(data));
    }

    @Override
    public List<Cuenta> listarTodos() {
        List<CuentaData> cuentaDataList = (List<CuentaData>) cuentaDataRepository.findAll();
        if (cuentaDataList.size() == 0) throw new BankAccountCuentaException("No existen registros de Cuentas!");
        return cuentaMapper.toCuentas(cuentaDataList);
    }

    @Override
    public Cuenta listar(String numeroCuenta) {
        return cuentaDataRepository.findById(numeroCuenta)
                .map(cuentaData -> cuentaMapper.toCuenta(cuentaData))
                .orElseThrow(() -> new BankAccountCuentaException("Cuenta :" + numeroCuenta + " Not Found!"));
    }

    @Override
    public List<Cuenta> listarPorCliente(String identificacionCliente) {
        clienteDataRepository.findByIdentificacion(identificacionCliente)
                .orElseThrow(() ->
                        new BankAccountCuentaException("Persona con documento :" + identificacionCliente + " No es cliente!"));
        return cuentaMapper.toCuentas(cuentaDataRepository.findByClienteIdentificacion(identificacionCliente));
    }

    @Override
    public Cuenta editar(Cuenta cuenta) {
        clienteDataRepository.findByIdentificacion(cuenta.getCliente().getIdentificacion())
                .orElseThrow(() ->
                        new BankAccountCuentaException("No se puede crear cuenta, persona con documento :"
                                + cuenta.getCliente().getIdentificacion() + " No es cliente!"));
        Utils.validarTipoCuenta(cuenta.getTipoCuenta());
        CuentaData data = cuentaMapper.toCuentaData(cuenta);
        return cuentaMapper.toCuenta(cuentaDataRepository.save(data));
    }

    @Override
    public boolean eliminar(String numeroCuenta) {
        return cuentaDataRepository.findById(numeroCuenta)
                .map(cuentaData -> {
                    cuentaDataRepository.delete(cuentaData);
                    return true;
                }).orElseThrow(() -> new BankAccountCuentaException("Cuenta :" + numeroCuenta + " Not Found!"));
    }

    @Override
    public void actualizarSaldo(String numeroCuenta, BigInteger nuevoSaldo) {
        cuentaDataRepository.findById(numeroCuenta).map(cuentaData -> {
                    cuentaData.setSaldo(nuevoSaldo);
                    cuentaDataRepository.save(cuentaData);
                    return cuentaData;
                })
                .orElseThrow(() -> new BankAccountCuentaException("Cuenta :" + numeroCuenta + " Not Found!"));
    }
}
