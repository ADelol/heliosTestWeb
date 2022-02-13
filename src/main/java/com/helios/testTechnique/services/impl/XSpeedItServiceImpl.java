package com.helios.testTechnique.services.impl;

import com.helios.testTechnique.services.XSpeedItService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class XSpeedItServiceImpl implements XSpeedItService {
    private final Logger log = LoggerFactory.getLogger(FizzBuzzServiceImpl.class);

    /**
     *  Sort in reverse order the items weight, then put each item in the first container
     *  it can fit in
     *
     * @param items the list of items weight
     * @return An ordering solution for the items and the numbers of containers used
     */
    @Override
    public String getXSpeedItSolution(String items) {
        final Integer[] weight = items.chars()
                .map(x -> x - '0')
                .boxed()
                .sorted(Collections.reverseOrder())
                .toArray(Integer[]::new);

        int res = 0;
        int capacity = 10;
        int n = weight.length;

        // At most as many containers as items
        int[] containers = new int[n];
        String[] containersContent = new String[n];
        Arrays.fill(containersContent, "");

        for (int i = 0; i < n; i++) {
            int j;
            //Find first container which can take the item
            for ( j = 0; j < res; j++) {
                if (containers[j] >= weight[i]) {
                    containers[j] = containers[j] - weight[i];
                    containersContent[j] += weight[i];
                    break;
                }
            }

            // Add a new container if all the containers are too full
            if (j == res) {
                containers[res] = capacity - weight[i];
                containersContent[res] += weight[i];
                res++;
            }
        }
        // Give a solution and number of containers
        String resList = Arrays.stream(containersContent)
                .filter(x -> !x.isEmpty())
                .collect(Collectors.joining("/"));
        resList += " " + (resList.chars()
                .filter(ch -> ch == '/')
                .count() + 1);
        return resList;
    }


}
