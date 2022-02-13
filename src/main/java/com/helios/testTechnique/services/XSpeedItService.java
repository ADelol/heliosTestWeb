package com.helios.testTechnique.services;

public interface XSpeedItService {

    /**
     * @param items the list of items weight
     * @return An ordering solution for the items and the numbers of containers used
     */
    String getXSpeedItSolution(String items);
}
