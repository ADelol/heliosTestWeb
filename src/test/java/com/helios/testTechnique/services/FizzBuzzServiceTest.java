package com.helios.testTechnique.services;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class FizzBuzzServiceTest {

    @Autowired
    private FizzBuzzService fizzBuzzService;

    @Test
    @DisplayName("Calls fizzBuzzService")
    public void callFizzBuzzService(){
    List<String> res = fizzBuzzService.applyFizzBuzz(2,4,4,"fizz","buzz");

    List<String> expected = new ArrayList<>();
    expected.add("1");
    expected.add("fizz");
    expected.add("3");
    expected.add("fizzbuzz");

    Assert.assertEquals(expected, res);
  }


}
