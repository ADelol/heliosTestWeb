package com.helios.testTechnique.controllers;

import com.helios.testTechnique.Utils.Constants;
import com.helios.testTechnique.services.StatisticsService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller for accessing statistics
 */
@RestController
@RequestMapping("/api")
public class StatisticsController {


    private final Logger log = LoggerFactory.getLogger(StatisticsController.class);

    private final StatisticsService statisticsService;

    private Counter metric;

    public StatisticsController(MeterRegistry meterRegistry, StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
        this.metric = meterRegistry.counter("statistics" + Constants.CUSTOM_COUNTER);
    }

    /**
     * {@code GET /v1/mostUsed} : Get which endpoint has been the most called
     *
     * @return the {@link ResponseEntity} with status {@code 200} and with the infos about the endpoint
     */
    @GetMapping("/v1/mostUsed")
    public ResponseEntity<Map<String, String>> mostUsed() {
        this.metric.increment();
        return ResponseEntity.ok(this.statisticsService.mostUsedEndpoint());
    }
}
