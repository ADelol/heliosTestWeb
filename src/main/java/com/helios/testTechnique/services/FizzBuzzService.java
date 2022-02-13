package com.helios.testTechnique.services;

import java.util.List;

public interface FizzBuzzService {
    /**
     * @param int1 the first number to replace
     * @param int2 the second number to replace
     * @param limit the range
     * @param str1 the first string to use for replacement
     * @param str2 the second string to use for replacement
     * @return the fizzbuzz response
     */
    List<String> applyFizzBuzz(int int1, int int2, int limit, String str1, String str2);
}
