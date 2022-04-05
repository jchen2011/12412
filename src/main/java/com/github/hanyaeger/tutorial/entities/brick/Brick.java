package com.github.hanyaeger.tutorial.entities.brick;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.tutorial.Brickanoid;
import com.github.hanyaeger.tutorial.entities.ball.Ball;
import com.github.hanyaeger.tutorial.entities.randomnumber.RandomNumber;
import com.github.hanyaeger.tutorial.scenes.dynamicscenes.Level;

/**
 * This class is responsible for making the bricks in the levels
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public abstract class Brick extends DynamicSpriteEntity implements Collided, Collider {
    private final int LOWEST_HP = 1;
    private final int HIGHEST_HP = 3;
    private final int SCORE_MULTIPLIER = 50;
    private int scoreValue;
    private int hitPoints;

    public Brick(Coordinate2D initialLocation, Size size, String resource) {
        super(resource, initialLocation, new Size(60, 25));
        RandomNumber randomHp = new RandomNumber(LOWEST_HP, HIGHEST_HP);
        this.hitPoints = randomHp.getValue();
        this.scoreValue = SCORE_MULTIPLIER * this.hitPoints;
        System.out.println(hitPoints);
    }


    @Override
    public void onCollision(Collider collider) {
        if (collider instanceof Ball) {
            hitPoints--;

            if (hitPoints <= 0) {
                removeBrickFromField();
                Brickanoid.currentLevel.determineLevelStatus(this);
                Brickanoid.updateHighScore();
            } else if (hitPoints >= 1) {
                this.setSaturation(this.getSaturation() - 0.2);
                this.setBrightness(this.getBrightness() - 0.2);
            }
        }
    }

    /**
     * Returns the current hit point of a brick
     *
     * @return the amount of hit points
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Returns the value which gets added to the current score when the brick gets removed from the screen
     */
    public int getScoreValue() {
        return scoreValue;
    }

    /**
     * Removes the brick from the field (depends on the subclass)
     */
    public abstract void removeBrickFromField();
}
