package com.github.hanyaeger.tutorial.entities.brick;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class BrickSprite extends SpriteEntity {
    protected BrickSprite(String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation);
    }
}
