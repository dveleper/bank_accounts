package com.bank.infrastructure.entry_points.api;

import com.bank.domain.model.EstadoCuenta;
import com.bank.domain.model.EstadoCuentaInput;
import com.bank.domain.usecase.EstadoCuentaUseCase;
import com.bank.util.Utils;
import jakarta.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping
public class EstadoCuentaController {

    @Autowired
    private EstadoCuentaUseCase estadoCuentaUseCase;
    private final Log LOGGER = LogFactory.getLog(EstadoCuentaController.class);

    @PostMapping("/reportes")
    public ResponseEntity<List<EstadoCuenta>> getEstadoCuenta(@RequestParam(name = "ini") String fecha_inicial,
                                                              @RequestParam(name = "fin") String fecha_final,
                                                              @RequestBody @Valid EstadoCuentaInput input) throws ParseException {
        LOGGER.debug("generando reporte ... ");
        input.setFechaInicial(Utils.getDateFormat(fecha_inicial));
        input.setFechaFinal(Utils.getDateFormat(fecha_final));
        return new ResponseEntity<>(estadoCuentaUseCase.obtenerReporte(input), HttpStatus.OK);
    }
}
