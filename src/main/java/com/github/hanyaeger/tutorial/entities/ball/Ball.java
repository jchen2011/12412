package com.github.hanyaeger.tutorial.entities.ball;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.tutorial.Brickanoid;
import com.github.hanyaeger.tutorial.entities.brick.Brick;
import com.github.hanyaeger.tutorial.entities.paddle.Paddle;
import com.github.hanyaeger.tutorial.entities.powerups.Powerup;
import com.github.hanyaeger.tutorial.entities.powerups.PowerupMultiball;
import com.github.hanyaeger.tutorial.entities.powerups.PowerupSlowdownBall;
import com.github.hanyaeger.tutorial.entities.randomnumber.RandomNumber;
import com.github.hanyaeger.tutorial.scenes.dynamicscenes.Level;

import java.util.Random;
import java.util.Vector;

/**
 * This class is responsible for making the ball object that does something based on a specific action
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */
public class Ball extends DynamicSpriteEntity implements SceneBorderTouchingWatcher, SceneBorderCrossingWatcher, Newtonian, Collided, Collider {
    private final double GRAVITY = 0.0001;
    private final double FRICTION = 0.0001;
    private int speed;
    private int direction;
    private Brickanoid brickanoid;
    private int powerupHitBricksCounter;
    public static int amountOfBallsOnScreen = 0;
    private boolean slowDownActive;

    public Ball(Coordinate2D initialLocation, Brickanoid brickanoid, int speed, int direction) {
        super("sprites/round_ball.png", initialLocation, new Size(10, 10));
        System.out.println("new Ball: " + brickanoid);
        this.speed = speed;
        this.direction = direction;
        this.brickanoid = brickanoid;
        this.powerupHitBricksCounter = 0;
        this.slowDownActive = false;
        setMotion(speed, direction);
        setGravityConstant(GRAVITY);
        setFrictionConstant(FRICTION);
        amountOfBallsOnScreen++;
    }

    @Override
    public void onCollision(Collider collider) {
        setDirection(-getDirection() + 180);
        System.out.println("balls in level:" + amountOfBallsOnScreen);

        if (collider instanceof Brick) {
            SoundClip ballhitbrick = new SoundClip("audio/soundeffect_destroy_brick.mp3", 1);
            ballhitbrick.setVolume(0.15);
            ballhitbrick.play();

            if (powerupHitBricksCounter >= 1) {
                this.powerupHitBricksCounter--;
            } else if (powerupHitBricksCounter <= 0 && slowDownActive) {
                deactivateSlowdown();
            }
        } else {
            SoundClip ballhitany = new SoundClip("audio/soundeffect_ball_hit_any.mp3", 1);
            ballhitany.setVolume(0.15);
            ballhitany.play();
        }

    }

    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        SoundClip ballLeavesScreen = new SoundClip("audio/soundeffect_ball_leaves_screen.mp3", 1);
        ballLeavesScreen.setVolume(0.15);
        ballLeavesScreen.play();

        if (sceneBorder == SceneBorder.BOTTOM && amountOfBallsOnScreen <= 1) {
            if (Brickanoid.lives >= 1) {
                Brickanoid.lives--;
                Brickanoid.liveText.setOverlayText(Brickanoid.lives);
                this.resetBall();
            }

            if (Brickanoid.lives <= 0) {
                brickanoid.endGameAfterLoss();
            }
        } else if (amountOfBallsOnScreen > 1 && sceneBorder == SceneBorder.BOTTOM) {
            this.remove();
            amountOfBallsOnScreen--;
        }
    }

    @Override
    public void notifyBoundaryTouching(SceneBorder sceneBorder) {
        SoundClip ballHitAny = new SoundClip("audio/soundeffect_ball_hit_any.mp3", 1);
        ballHitAny.setVolume(0.15);

        switch (sceneBorder) {
            case TOP -> {
                setDirection(-getDirection() + 180);
                ballHitAny.play();
            }
            case LEFT, RIGHT -> {
                setDirection(360 - getDirection());
                ballHitAny.play();
            }
        }
    }

    /**
     * Sets the old speed to the new given speed
     *
     * @param speed the new current speed that replaces the old speed
     */
    public void setSpeed(int speed) {
        setMotion(speed, direction);
    }

    /**
     * Actives the ball slowdown powerup, where the ball is significant slower than usual
     *
     * @param powerup the powerup that should be activated
     */
    public void activateSlowdown(Powerup powerup) {
        this.powerupHitBricksCounter += 5;

        if (!slowDownActive) {
            this.setSpeed(this.getSpeed() - 2.0);
        }
        this.slowDownActive = true;
    }

    /**
     * Deactives the ball slowdown when there are no bricks left over
     */
    public void deactivateSlowdown() {
        slowDownActive = false;
        this.setSpeed(this.getSpeed() + 2.0);
    }

    /**
     * Resets the ball instance for the next level so that it would not fall en will start above the paddle
     */
    public void resetBall() {
        powerupHitBricksCounter = 0;
        slowDownActive = false;
        setMotion(speed, generateRandomDirection());
        setGravityConstant(GRAVITY);
        setFrictionConstant(FRICTION);
        resetLocation();
    }

    public void resetLocation() {
        double newSpawnLocationX = brickanoid.getSCREEN_WIDTH() / 2.0;
        double newSpawnLocationY = brickanoid.getSCREEN_HEIGHT() / 2.0 + (brickanoid.getSCREEN_HEIGHT() / 4.0);
        this.setAnchorLocation(new Coordinate2D(newSpawnLocationX, newSpawnLocationY));
    }

    public int generateRandomDirection() {
        int randomDirection = 0;
        int lowerBound = 135;
        int upperBound = 225;
        RandomNumber randomDirectionNumber = new RandomNumber(1, 2);

        if(randomDirectionNumber.getValue() == 1) {
            randomDirection = lowerBound;
        }
        // We moeten nog kijken of we multiball wel in de game laten
        else if(randomDirectionNumber.getValue() == 2) {
            randomDirection = upperBound;
        }

        return randomDirection;
    }
}
