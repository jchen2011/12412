package com.github.hanyaeger.tutorial.entities.powerups;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.tutorial.Brickanoid;

/**
 * This class is responsible for making the ball slowdown powerup
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public class PowerupSlowdownBall extends Powerup{
    public PowerupSlowdownBall(Coordinate2D initialLocation) {
        super("sprites/Arkanoid_Orange_Slow.gif", initialLocation);
    }

    @Override
    public void activatePowerup()  {
        super.activatePowerup();
        var level = Brickanoid.currentLevel;
        level.ball.activateSlowdown(this);
        System.out.println("slowdown");
    }
}
