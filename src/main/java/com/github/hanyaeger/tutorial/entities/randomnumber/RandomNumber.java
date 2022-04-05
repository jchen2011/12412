package com.github.hanyaeger.tutorial.entities.randomnumber;

import java.util.Random;

public class RandomNumber {
    private final int value;

    public RandomNumber(int lowerBound, int upperBound) {
        Random r = new Random();
        this.value = r.nextInt((upperBound - lowerBound) + lowerBound) + lowerBound;
    }

    public int getValue() {
        return value;
    }
}
