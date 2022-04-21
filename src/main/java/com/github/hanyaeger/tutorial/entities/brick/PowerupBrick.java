package com.github.hanyaeger.tutorial.entities.brick;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.tutorial.Brickanoid;
import com.github.hanyaeger.tutorial.entities.powerups.Powerup;
import com.github.hanyaeger.tutorial.entities.powerups.PowerupExtraLife;
import com.github.hanyaeger.tutorial.entities.powerups.PowerupMultiball;
import com.github.hanyaeger.tutorial.entities.powerups.PowerupSlowdownBall;
import com.github.hanyaeger.tutorial.entities.randomnumber.RandomNumber;

/**
 * This class is responsible for making the powerup brick.
 * When you collide with this brick, the brick will drop a power-up
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public class PowerupBrick extends Brick{
    public PowerupBrick(Coordinate2D initialLocation, Size size, String resource) {
        super(initialLocation, size, resource);
    }

    /**
     * Drops a power-up and then removes the brick, the score will also be updated
     */
    @Override
    public void removeBrickFromField() {
        Brickanoid.score += this.getScoreValue();
        Brickanoid.scoreText.setOverlayText(Brickanoid.score);
        dropPowerup();
        this.remove();
    }

    /**
     * Drops the power-up in the game
     * Uses a random number to pick between the different possible power-ups that the brick can drop down
     * The only power-ups that are available are; slowdown, multiball and extra life
     */
    public void dropPowerup() {
        RandomNumber randomPowerupNumber = new RandomNumber(1, 3);

        Powerup p = null;
        if(randomPowerupNumber.getValue() == 1) {
            p = new PowerupSlowdownBall(this.getLocationInScene());
        }
        else if(randomPowerupNumber.getValue() == 2) {
            p = new PowerupMultiball(this.getLocationInScene());
        }
        else if(randomPowerupNumber.getValue() == 3) {
            p = new PowerupExtraLife(this.getLocationInScene());
        }

        Brickanoid.currentLevel.addEntity(p);
    }
}
