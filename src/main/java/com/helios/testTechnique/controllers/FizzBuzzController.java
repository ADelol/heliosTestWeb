package com.helios.testTechnique.controllers;

import com.helios.testTechnique.Utils.Constants;
import com.helios.testTechnique.controllers.errors.BadRequestException;
import com.helios.testTechnique.services.FizzBuzzService;
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

import java.util.*;

/**
 * Controller for getting fizzbuzz response
 */
@RestController
@RequestMapping("/api")
public class FizzBuzzController {


    private final Logger log = LoggerFactory.getLogger(FizzBuzzController.class);

    private final FizzBuzzService fizzBuzzService;

    private Counter fizzbuzz;

    public FizzBuzzController(FizzBuzzService fizzBuzzService, MeterRegistry meterRegistry) {
        this.fizzBuzzService = fizzBuzzService;
        this.fizzbuzz = meterRegistry.counter("fizzbuzz" + Constants.CUSTOM_COUNTER);
    }

    /**
     * {@code GET /v1/fizzbuzz} : Get fizzbuzz response
     *
     * @param int1 the first number to replace
     * @param int2 the second number to replace
     * @param limit the range
     * @param str1 the first string to use for replacement
     * @param str2 the second string to use for replacement
     * @return the {@link ResponseEntity} with status {@code 200}
     * or with status {@code 400 (Bad Request)} if one parameter is wrong
     */
    @GetMapping(path ="/v1/fizzbuzz", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> activateAccount(@RequestParam(value = "int1") int int1,
                                        @RequestParam(value = "int2") int int2,
                                        @RequestParam(value = "limit") int limit,
                                        @RequestParam(value = "str1") String str1,
                                        @RequestParam(value = "str2") String str2)  {
        this.fizzbuzz.increment();
        log.info(String.valueOf(limit));
        if (int1 < 1 || int2 < 1 || limit < 1 || str1.equals("") || str2.equals("")) {
            throw new BadRequestException("Integers must be superior to 0 and strings not empty");
        }
        return ResponseEntity.ok(this.fizzBuzzService.applyFizzBuzz(int1, int2, limit, str1, str2));
    }
}
