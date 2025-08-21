package com.totaliteaShop.logictotest;

import java.util.ArrayList;
import java.util.List;

public class Logic {


    public static List<Double> addBasketWithWeight(double itemWeight){
        List<Double> Basket = new ArrayList<>();
        Basket.add(itemWeight);
        return Basket;
    }

    public static double shippingCostCalculator(List<Double> Basket) {
        double Postage = 0;
        double cartWeight = 0;
        for (int i = 0; i < Basket.size(); i++) {
            cartWeight += i;
        }
        if (cartWeight < 5) {
            Postage = 1.5;
        }
        System.out.println(Basket);
        return Postage;
    }

}

