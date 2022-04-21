package com.github.hanyaeger.tutorial.entities.powerups;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.tutorial.Brickanoid;
import com.github.hanyaeger.tutorial.entities.ball.Ball;

/**
 * This class is responsible for making the multi ball power-up
 * When you pick up this power-up, you will get multiple balls into the game.
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public class PowerupMultiball extends Powerup{
    public PowerupMultiball(Coordinate2D initialLocation) {
        super("sprites/Arkanoid_Blue_Enlarge.gif", initialLocation);
    }

    /**
     * Actives the multi ball power-up by adding multiple balls into the current level.
     * It spawns next to the ball to prevent that the ball will get stuck
     */
    @Override
    public void activatePowerup() {
        super.activatePowerup();
        var level = Brickanoid.currentLevel;
        var ball = level.ball;

        double newX = ball.getAnchorLocation().getX() + 20;
        double newY = ball.getAnchorLocation().getY() + 20;
        Ball b = new Ball(new Coordinate2D(newX, newY), level.brickanoid, (int) ball.getSpeed(), (int) ball.getDirection() + 10, Brickanoid.currentLevel);
        level.addEntity(b);
    }
}

