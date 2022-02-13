package com.helios.testTechnique.services.impl;

import com.helios.testTechnique.Utils.Constants;
import com.helios.testTechnique.services.StatisticsService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final MeterRegistry meterRegistry;

    public StatisticsServiceImpl(MeterRegistry meterRegistry){
        this.meterRegistry = meterRegistry;
    }

    /**
     * Use counters set in controllers to know which endpoint has been the most used
     *
     * @return the informations on the most called endpoint
     */
    @Override
    public Map<String, String> mostUsedEndpoint() {
        Map<String, Integer> countersValue = new HashMap<>();

        this.meterRegistry.getMeters().stream()
                .filter(x -> x.getId().getName().contains(Constants.CUSTOM_COUNTER))
                .forEach(x -> countersValue
                        .put(x.getId().getName(),(int) x.measure().iterator().next().getValue()));

        Optional<Map.Entry<String, Integer>> entry = countersValue
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));


        Map<String, String> resMap = new HashMap<>();
        if (entry.isPresent()) {
            resMap.put("Most used endpoint", entry.get().getKey());
            resMap.put("Number of calls", String.valueOf(entry.get().getValue()));
        } else {
            resMap.put("Most used endpoint", "Pas d'appels");
            resMap.put("Number of calls", "0");
        }
        return resMap;
    }
}
