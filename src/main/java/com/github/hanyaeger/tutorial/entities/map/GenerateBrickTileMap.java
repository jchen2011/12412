package com.github.hanyaeger.tutorial.entities.map;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

import java.util.Random;

/**
 * This class is responsible for generating a brick tile map
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public class GenerateBrickTileMap extends BrickTileMap{
    private int amountOfBricks;

    public GenerateBrickTileMap(Coordinate2D location, Size size, int amountOfBricks) {
        super(location, size);
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
    int[][] generateTileMap(int amountOfRows, int amountOfColumns, int amountOfBricks) {
        final int EMPTY = 0;
        final int BRICK_BLUE = 1;
        final int BRICK_GREEN = 2;
        final int BRICK_PURPLE = 3;
        final int BRICK_YELLOW = 4;
        final int BRICK_POWERUP = 5;
        final int DIFFERENT_NUMBER_OF_BRICKS = 5;

        int[][] brickMap = new int[amountOfRows][amountOfColumns];
        int[] brickList = new int[amountOfRows * amountOfColumns];

        // Creeër een variabele die elke keer 1 omhoog telt wanneer een element wordt toegevoegd aan de array,
        // deze kan je gebruiken in verschillende loops en de waarde blijft geupdated,  hiermee zorg je ervoor dat waardes niet in dezelfde cell geplaatst worden.
        int indexFillWithBricks = 0;

        for (int numberCounter = 0; numberCounter < amountOfBricks; numberCounter++) {
            brickList[numberCounter] = EMPTY;
        }

        for (int i = 0; i < amountOfBricks / DIFFERENT_NUMBER_OF_BRICKS; i++) {
            indexFillWithBricks++;

            brickList[indexFillWithBricks] += BRICK_BLUE;
        }
        for (int i = 0; i < amountOfBricks / DIFFERENT_NUMBER_OF_BRICKS; i++) {
            indexFillWithBricks++;

            brickList[indexFillWithBricks] += BRICK_GREEN;
        }
        for (int i = 0; i < amountOfBricks / DIFFERENT_NUMBER_OF_BRICKS; i++) {
            indexFillWithBricks++;

            brickList[indexFillWithBricks] += BRICK_PURPLE;
        }
        for (int i = 0; i < amountOfBricks / DIFFERENT_NUMBER_OF_BRICKS; i++) {
            indexFillWithBricks++;

            brickList[indexFillWithBricks] += BRICK_YELLOW;
        }
        for (int i = 0; i < amountOfBricks / DIFFERENT_NUMBER_OF_BRICKS; i++) {
            indexFillWithBricks++;

            brickList[indexFillWithBricks] += BRICK_POWERUP;
        }

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
     * Shuffles the received array completely, see Fisher-Yates shuffle algorithm
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
