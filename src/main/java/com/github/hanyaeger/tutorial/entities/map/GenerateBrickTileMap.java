package com.github.hanyaeger.tutorial.entities.map;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

import java.util.Random;

/**
 * This class is responsible for generating a random brick tile map, that you can use for a level
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public class GenerateBrickTileMap extends BrickTileMap{
    private int amountOfBricks;
    int indexFillWithBricks;

    public GenerateBrickTileMap(Coordinate2D location, Size size, int amountOfBricks) {
        super(location, size);
        this.indexFillWithBricks = 0;
        this.amountOfBricks = amountOfBricks;
    }

    /**
     * Generates a tile map that is totally random, so that bricks are spawned randomly        
     * 
     * @param amountOfRows the total rows that you want to have for the tile map
     * @param amountOfColumns the total columns you want to have for the tile map
     * @param amountOfBricks the amount bricks that you want to have for the game
     *
     * @return the full randomized tile map that you can use for Brickanoid
     */
    public int[][] generateTileMap(int amountOfRows, int amountOfColumns, int amountOfBricks) {
        final int EMPTY = 0;
        final int BRICK_BLUE = 1;
        final int BRICK_GREEN = 2;
        final int BRICK_PURPLE = 3;
        final int BRICK_YELLOW = 4;
        final int BRICK_POWERUP = 5;
        final int DIFFERENT_NUMBER_OF_BRICKS = 5;

        int[][] brickMap = new int[amountOfRows][amountOfColumns];
        int[] brickList = new int[amountOfRows * amountOfColumns];

        for (int numberCounter = 0; numberCounter < amountOfBricks; numberCounter++) {
            brickList[numberCounter] = EMPTY;
        }

        brickList = fillBricklistWithBricks(brickList, BRICK_BLUE, DIFFERENT_NUMBER_OF_BRICKS);
        brickList = fillBricklistWithBricks(brickList, BRICK_GREEN, DIFFERENT_NUMBER_OF_BRICKS);
        brickList = fillBricklistWithBricks(brickList, BRICK_PURPLE, DIFFERENT_NUMBER_OF_BRICKS);
        brickList = fillBricklistWithBricks(brickList, BRICK_YELLOW, DIFFERENT_NUMBER_OF_BRICKS);
        brickList = fillBricklistWithBricks(brickList, BRICK_POWERUP, DIFFERENT_NUMBER_OF_BRICKS);
        brickList = shuffleArray(brickList, amountOfBricks);

        int counter = 0;

        for (int rijenTeller = 0; rijenTeller < amountOfRows; rijenTeller++) {
            for (int colTeller = 0; colTeller < amountOfColumns; colTeller++) {
                brickMap[rijenTeller][colTeller] = brickList[counter];
                counter++;
            }
        }

        return brickMap;
    }

    /**
     * Fills the array with bricks that depends on the given parameter.
     *
     * @param brickList the array you want to add the bricks on
     * @param brickColor the color of the brick that you want to add
     * @param numberOfDifferentBricks total numbers of bricks that you want to have in the tile map
     *
     * @return the new array with the added bricks
     */
    public int[] fillBricklistWithBricks(int[] brickList, int brickColor, int numberOfDifferentBricks) {
        for (int i = 0; i < amountOfBricks / numberOfDifferentBricks; i++) {
            indexFillWithBricks++;

            brickList[indexFillWithBricks] += brickColor;
        }
        return brickList;
    }

    /**
     * Shuffles the received array completely, see also the Fisher-Yates shuffle algorithm
     *
     * @param array the array that you want to shuffle
     * @param amountOfNumbers how many items that there are in the array
     *
     * @return the new shuffled array
     */
    int[] shuffleArray(int[] array, int amountOfNumbers) {
        for (int numberIndex = amountOfNumbers-1; numberIndex > 0; numberIndex--) {
            Random r = new Random();
            int randomInt = r.nextInt(numberIndex + 1);
            int temp = array[numberIndex];

            array[numberIndex] = array[randomInt];
            array[randomInt] = temp;
        }

        return array;
    }

    @Override
    public int[][] defineMap() {
        return generateTileMap(8, 8, amountOfBricks);
    }

}
