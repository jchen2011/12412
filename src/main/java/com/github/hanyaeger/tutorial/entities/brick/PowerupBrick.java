package com.github.hanyaeger.tutorial.entities.brick;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.tutorial.Brickanoid;
import com.github.hanyaeger.tutorial.entities.powerups.Powerup;
import com.github.hanyaeger.tutorial.entities.powerups.PowerupMultiball;
import com.github.hanyaeger.tutorial.entities.powerups.PowerupSlowdownBall;
import com.github.hanyaeger.tutorial.entities.randomnumber.RandomNumber;

/**
 * This class is responsible for making the powerup brick
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public class PowerupBrick extends Brick{
    public PowerupBrick(Coordinate2D initialLocation, Size size, String resource) {
        super(initialLocation, size, resource);
    }

    /**
     * Drops a powerup and then removes the brick
     */
    @Override
    public void removeBrickFromField() {
        Brickanoid.score += this.getScoreValue();
        Brickanoid.scoreText.setOverlayText(Brickanoid.score);
        dropPowerup();
        this.remove();
    }

    /**
     * Drops the powerup in the game
     * Use a random number to pick between the different possible powerups the play can pick up
     */
    public void dropPowerup() {
        RandomNumber randomPowerupNumber = new RandomNumber(1, 2);

        Powerup p = null;
        if(randomPowerupNumber.getValue() == 1) {
            p = new PowerupSlowdownBall(this.getLocationInScene());
        }
        // We moeten nog kijken of we multiball wel in de game laten
        else if(randomPowerupNumber.getValue() == 2) {
            p = new PowerupMultiball(this.getLocationInScene());
        }

        Brickanoid.currentLevel.addEntity(p);
    }
}
