package com.github.hanyaeger.tutorial.entities.brick;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.tutorial.Brickanoid;

/**
 * This class is responsible for making a normal/simple brick with no extra things
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */
public class SimpleBrick extends Brick{
    public SimpleBrick(Coordinate2D initialLocation, Size size, String resource) {
        super(initialLocation, size, resource);
    }

    /**
     * Removes the brick from the field and updates the score
     */
    @Override
    public void removeBrickFromField() {
        Brickanoid.score += this.getScoreValue();
        Brickanoid.scoreText.setOverlayText(Brickanoid.score);
        this.remove();
    }
}
