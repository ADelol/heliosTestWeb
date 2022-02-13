package com.helios.testTechnique.controllers;

import com.helios.testTechnique.Utils.Constants;
import com.helios.testTechnique.controllers.errors.BadRequestException;
import com.helios.testTechnique.services.XSpeedItService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for getting xSpeedit solution
 */
@RestController
@RequestMapping("/api")
public class XSpeedItController {


    private final Logger log = LoggerFactory.getLogger(XSpeedItController.class);

    private final XSpeedItService xSpeedItService;

    private Counter xSpeedIt;


    public XSpeedItController(XSpeedItService xSpeedItService, MeterRegistry meterRegistry) {
        this.xSpeedItService = xSpeedItService;
        this.xSpeedIt = meterRegistry.counter("xSpeedIt" + Constants.CUSTOM_COUNTER);
    }

    /**
     * {@code GET /v1/xspeedit} : Get solution for xspeedit problem
     * @param items : the string of items weight
     * @return the {@link ResponseEntity} with the string solution and with status {@code 200}
     * or with status {@code 400 (Bad Request)} if the string is not only composed of numbers
     */
    @GetMapping(path="/v1/xspeedit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> binPackingAlgorithm(@RequestParam(value = "items") String items) {
        this.xSpeedIt.increment();
        if(!items.chars().allMatch(i -> Character.isDigit(i)))
            throw new BadRequestException("Input string must only be composed of digits");
        return ResponseEntity.ok(this.xSpeedItService.getXSpeedItSolution(items));

    }
}
