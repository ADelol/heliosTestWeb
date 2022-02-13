package com.helios.testTechnique.services.impl;

import com.helios.testTechnique.services.FizzBuzzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FizzBuzzServiceImpl implements FizzBuzzService {

    private final Logger log = LoggerFactory.getLogger(FizzBuzzServiceImpl.class);

    /**
     * Apply FizzBuzz
     *
     * @param int1 the first number to replace
     * @param int2 the second number to replace
     * @param limit the range
     * @param str1 the first string to use for replacement
     * @param str2 the second string to use for replacement
     * @return the fizzbuzz response
     */
    @Override
    public List<String> applyFizzBuzz(int int1, int int2, int limit, String str1, String str2) {
        List<String> res = IntStream.rangeClosed(1, limit)
                .mapToObj(i -> i % int1 == 0 ? (i % int2 == 0 ? str1 + str2 : str1) : (i % int2 == 0 ? str2 : String.valueOf(i)))
                .collect(Collectors.toList());
        return res;
    }
}
