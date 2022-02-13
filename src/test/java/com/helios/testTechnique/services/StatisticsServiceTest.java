package com.helios.testTechnique.services;

import com.helios.testTechnique.Utils.Constants;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class StatisticsServiceTest {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private MeterRegistry meterRegistry;

    @Test
    @DisplayName("Calls fizzBuzzService")
    public void callFizzBuzzService(){
    Counter counter = meterRegistry.counter("test" + Constants.CUSTOM_COUNTER);
    counter.increment();

    Map<String, String> res = statisticsService.mostUsedEndpoint();

    Map<String, String> expected = new HashMap<>();
    expected.put("Most used endpoint", "test.custom.counter");
    expected.put("Number of calls", "1");

    Assert.assertEquals(expected, res);
  }


}
