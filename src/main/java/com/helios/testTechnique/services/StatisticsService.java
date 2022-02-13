package com.helios.testTechnique.services;

import java.util.Map;

public interface StatisticsService {

    /**
     * @return the informations on the most called endpoint
     */
    public Map<String, String> mostUsedEndpoint();
}
