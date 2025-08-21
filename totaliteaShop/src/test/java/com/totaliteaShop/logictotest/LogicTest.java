package com.totaliteaShop.logictotest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

class LogicTest {

    @Test
    void addBasketWithWeightTest() {
        List<Double> expectedWeight = new ArrayList<>();
        expectedWeight.add(4.9);

        List<Double> actualWeight = Logic.addBasketWithWeight(4.9);

        assertEquals("weight", expectedWeight, actualWeight);

    }

    @Test
    void shippingCostCalculatorTest() {
        double expectedShipping = 1.5;

        double actualShipping = Logic.shippingCostCalculator(Logic.addBasketWithWeight(4.0));

        System.out.println(actualShipping);
        assertEquals("shipping cost", expectedShipping, actualShipping);

    }
}
