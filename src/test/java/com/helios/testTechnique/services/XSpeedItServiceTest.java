package com.helios.testTechnique.services;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class XSpeedItServiceTest {

    @Autowired
    private XSpeedItService xSpeedItService;

    @Test
    @DisplayName("Calls XSpeedItSolution")
    public void callFizzBuzzService(){
    String res = xSpeedItService.getXSpeedItSolution("97259");

    int numberOfContainersExpected = 4;
    int resNumber = (int) res.chars().filter(x -> x == '/').count() + 1;

    Assert.assertEquals(numberOfContainersExpected, resNumber);
  }


}
