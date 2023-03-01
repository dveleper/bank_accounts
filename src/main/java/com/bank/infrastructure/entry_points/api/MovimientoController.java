package com.bank.infrastructure.entry_points.api;

import com.bank.domain.model.Movimiento;
import com.bank.domain.usecase.MovimientoUseCase;
import jakarta.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final Log LOGGER = LogFactory.getLog(MovimientoController.class);

    @Autowired
    private MovimientoUseCase movimientoUseCase;

    @PostMapping("/save")
    public ResponseEntity<Movimiento> save(@Valid @RequestBody Movimiento movimiento) {
        LOGGER.debug(String.format("creando transaccion %s", movimiento.getTipoMovimiento()));
        return new ResponseEntity<>(movimientoUseCase.crear(movimiento), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Movimiento>> getAll() {
        return new ResponseEntity<>(movimientoUseCase.listar(), HttpStatus.OK);
    }

    @GetMapping("/find/{numero}")
    public ResponseEntity<Movimiento> getByIdMovimiento(@PathVariable Integer numero) {
        return new ResponseEntity<>(movimientoUseCase.listarPorId(numero), HttpStatus.OK);
    }

    @GetMapping("/find/cuenta/{cuenta}")
    public ResponseEntity<List<Movimiento>> getByIdAccount(@PathVariable String cuenta) {
        return new ResponseEntity<>(movimientoUseCase.listarPorCuenta(cuenta), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{numero}")
    public ResponseEntity delete(@PathVariable Integer numero) {
        movimientoUseCase.eliminar(numero);
        return new ResponseEntity<>(String.format("Movimiento %s deleted.", numero), HttpStatus.OK);

    }

}
