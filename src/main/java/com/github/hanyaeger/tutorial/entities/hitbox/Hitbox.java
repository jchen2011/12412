package com.github.hanyaeger.tutorial.entities.hitbox;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;
import javafx.scene.paint.Color;

public class Hitbox extends RectangleEntity implements Collider {
    private int width, height;
    public Hitbox(Coordinate2D initialLocation, int width, int height) {
        super(initialLocation);
        this.width = width;
        this.height = height;
        setWidth(width);
        setHeight(height);
        setFill(Color.RED);
    }
}
