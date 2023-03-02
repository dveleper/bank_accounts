package com.bank.infrastructure.entry_points.health;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    private final Log LOGGER = LogFactory.getLog(HealthController.class);

    @GetMapping
    public ResponseEntity<String> health() {
        LOGGER.info("health ok");
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
