package com.github.hanyaeger.tutorial.entities.map;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.scenes.TileMap;
import com.github.hanyaeger.tutorial.entities.brick.PowerupBrick;
import com.github.hanyaeger.tutorial.entities.brick.SimpleBrick;

/**
 * This class is responsible for associating the different bricks with an identifier.
 * The identifier is used for the brick tile maps.
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public abstract class BrickTileMap extends TileMap {

    public BrickTileMap(Coordinate2D location, Size size) {
        super(location, size);
    }

    @Override
    public void setupEntities() {
        addEntity(1, SimpleBrick.class, "sprites/brick1.png");
        addEntity(2, SimpleBrick.class, "sprites/brick2.png");
        addEntity(3, SimpleBrick.class, "sprites/brick3.png");
        addEntity(4, SimpleBrick.class, "sprites/brick4.png");
        addEntity(5, PowerupBrick.class, "sprites/brick2.png");
    }
}
