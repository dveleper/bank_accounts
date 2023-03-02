package com.bank.infrastructure.entry_points.api;

import com.bank.domain.model.Cuenta;
import com.bank.domain.usecase.CuentaUseCase;
import jakarta.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final Log LOGGER = LogFactory.getLog(CuentaController.class);

    @Autowired
    private CuentaUseCase cuentaUseCase;

    @PostMapping("/save")
    public ResponseEntity<Cuenta> save(@Valid @RequestBody Cuenta cuenta) {
        LOGGER.debug(String.format("creando cuenta %s", cuenta.getTipoCuenta()));
        return new ResponseEntity<>(cuentaUseCase.crear(cuenta), HttpStatus.CREATED);
    }

    @GetMapping("/find/cliente/{identificacion}")
    public ResponseEntity<List<Cuenta>> getByIdentification(@PathVariable String identificacion) {
        return new ResponseEntity<>(cuentaUseCase.listarPorCliente(identificacion), HttpStatus.OK);
    }

    @GetMapping("/find/{numero}")
    public ResponseEntity<Cuenta> getByNumeroCuenta(@PathVariable String numero) {
        return new ResponseEntity<>(cuentaUseCase.listarPorNumeroCuenta(numero), HttpStatus.OK);
    }


    @GetMapping("/find")
    public ResponseEntity<List<Cuenta>> getAll() {
        return new ResponseEntity<>(cuentaUseCase.listar(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{numero}")
    public ResponseEntity delete (@PathVariable String numero) {
        cuentaUseCase.eliminar(numero);
        return new ResponseEntity<>(String.format("Cuenta %s deleted.", numero), HttpStatus.OK);
    }

    @PutMapping("/update/{identificacion}")
    public ResponseEntity<Cuenta> update (@Valid @RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(cuentaUseCase.editar(cuenta), HttpStatus.OK);
    }
}
