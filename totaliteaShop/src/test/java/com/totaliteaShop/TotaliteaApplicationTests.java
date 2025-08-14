package com.totaliteaShop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.AssertionErrors;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class TotaliteaApplicationTests {

    @Test
    void contextLoads() {
        // no-op: verifies the Spring context starts with the test profile
    }

    //when basket size methods come in
    @Test
    void given_BasketEmpty_thenReturnEmptyBasket() {
//        BasketSize basketSize = new BasketSize();
//        assertEquals(0, basketSize(0));
    }

    @Test
    void givenBasketHas4_thenReturnListIs4() {
//        BasketSize basketSize = new BasketSize();
//        assertEquals(4, basketSize(4));
    }

    @Test
    void givenObjectAddedToCart_thenReturnCartContains1() {
        AddToBasket addToBasket = new AddToBasket();
        expectedBasketSizeOne[] = 1;
        actualBasketSize = addToBasket();
        assertEquals(expectedBasketSizeOne, actualBasketSize)

    }

    @Test
    void givenCartWeightLessThan5KG_returnCost1point5() {
//
//        List<Double> lessThan5kgCart = new ArrayList<>();
//
//        double expectedCost = 1.5;
//        double actualCost = shippingCostCalculator(lessThan5kgCart);
//        AssertionErrors.assertEquals(expectedCost, actualCost);
    }

    double shippingCostCalculator(List<Double> Cart) {
//        List<Integer> Cart = new ArrayList<>();
        double Postage = 0;
        Cart.add(2.0);
        double cartWeight = 0;
        for (int i = 0; i < Cart.size(); i++) {
            cartWeight = +i;
        }
        if (cartWeight < 5) {
            Postage = 1.5;
        }
        System.out.println(Cart);
        return Postage;
    }
}