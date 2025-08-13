package com.totaliteaShop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class TotaliteaApplicationTests {

    @Test
    void contextLoads() {
        // no-op: verifies the Spring context starts with the test profile
    }

    //when basket size methods come in
    @Test
    void testBasketEmpty() {
//        BasketSize basketSize = new BasketSize();
//        assertEquals(0, basketSize(0));
    }

    @Test
    void testBasketFour() {
//        BasketSize basketSize = new BasketSize();
//        assertEquals(4, basketSize(4));
    }

    @Test
    void testAddToCart() {

    }

    void shippingCostCalculator() {
        List<Integer> Cart = new ArrayList<>();
        double Postage = 0;
        Cart.add(2);
        double cartWeight = 0;
        for (int i = 0; i < Cart.size(); i++) {
            cartWeight = +i;
        }
        if (cartWeight < 5) {
            Postage = 1.5;
        }
        System.out.println(Cart);
    }

    @Test
    void testShipCostWeightLessThanFivekg() {

////        ShippingCostCalculator shippingCostCalculator = new ShippingCostCalculator();
//
//        List<Integer> lessThan5kgCart = new ArrayList<>();
//
//        double expectedCost = 1.5;
//        double actualCost = shippingCostCalculator(lessThan5kgCart);
//        assertEquals(expectedCost, actualCost);
//    }


    }
}