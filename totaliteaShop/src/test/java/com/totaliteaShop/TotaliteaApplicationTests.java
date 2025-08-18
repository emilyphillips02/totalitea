package com.totaliteaShop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        BasketSize basketSize = new BasketSize();
        assertEquals(0, basketSize(0));
    }

    @Test
    void givenBasketHas4_thenReturnListIs4() {
        BasketSize basketSize = new BasketSize();
        assertEquals(4, basketSize(4));
    }

    @Test
    void givenObjectAddedToCart_thenReturnCartContains1() {
        //needs the AddToBasket method. adds item to
        AddToBasket addToBasket = new AddToBasket();
        //custom list with 1 object
        String[] expectedBasketSizeOne = new String[0];
        actualBasketSize = addToBasket();
        assertEquals(expectedBasketSizeOne, actualBasketSize);

    }

    @Test
    void givenCartWeightLessThan5KG_returnCost1point5() {

        List<Double> lessThan5kgCart = new ArrayList<>();
        double expectedCost = 1.5;
        double weight = 1;

        lessThan5kgCart.add(weight);

        double actualCost = shippingCostCalculator(lessThan5kgCart);

        assertEquals(expectedCost, actualCost);
    }

//    double shippingCostCalculator(List<Double> Cart) {
//        double Postage = 0;
//        double cartWeight = 0;
//        for (int i = 0; i < Cart.size(); i++) {
//            cartWeight += i;
//        }
//        if (cartWeight < 5) {
//            Postage = 1.5;
//        }
//        System.out.println(Cart);
//        return Postage;
//    }
//}