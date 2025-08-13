package com.totaliteaShop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TotaliteaApplicationTests {

    @Test
    void contextLoads() {
        // no-op: verifies the Spring context starts with the test profile
    }

    //when basket size methods come in
    @org.junit.jupiter.api.Test
    void whenBasketEmpty(){
        BasketSize basketSize = new BasketSize();
        assertEquals(0, basketSize(0));

    @org.junit.jupiter.api.Test
    void whenBasket4(){
        BasketSize basketSize = new BasketSize();
        assertEquals(4, basketSize(4));

   void

}