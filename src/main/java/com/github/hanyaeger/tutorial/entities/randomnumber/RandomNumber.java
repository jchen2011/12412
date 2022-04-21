package com.github.hanyaeger.tutorial.entities.randomnumber;

import java.util.Random;

/**
 * This class is responsible for generating a random number.
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */
public class RandomNumber {
    private final int value;

    public RandomNumber(int lowerBound, int upperBound) {
        Random r = new Random();
        this.value = r.nextInt((upperBound - lowerBound) + lowerBound) + lowerBound;
    }

    /**
     * Returns the value of the random number that was decided based off the constructor
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
