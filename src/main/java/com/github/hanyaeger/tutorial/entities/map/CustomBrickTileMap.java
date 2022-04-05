package com.github.hanyaeger.tutorial.entities.map;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

/**
 * This class is responsible for creating the tile map hard-coded into an integer array
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public class CustomBrickTileMap extends BrickTileMap {

    public CustomBrickTileMap(Coordinate2D location, Size size) {
        super(location, size);
    }

    @Override
    public int[][] defineMap() {
                return new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 5, 2, 3, 4, 4, 1, 2, 5, 0},
                {0, 1, 2, 3, 4, 4, 1, 2, 1, 0},
                {0, 1, 2, 3, 4, 4, 1, 2, 1, 0},
                {0, 1, 2, 3, 4, 4, 1, 2, 1, 0},
                {0, 5, 2, 5, 4, 4, 5, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
    }
}
