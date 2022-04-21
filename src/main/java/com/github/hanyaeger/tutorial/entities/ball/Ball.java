package com.github.hanyaeger.tutorial.entities.ball;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.tutorial.Brickanoid;
import com.github.hanyaeger.tutorial.entities.brick.Brick;
import com.github.hanyaeger.tutorial.entities.powerups.Powerup;
import com.github.hanyaeger.tutorial.entities.randomnumber.RandomNumber;
import com.github.hanyaeger.tutorial.scenes.dynamicscenes.Level;

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
    private boolean slowDownActive;
    private Level level;

    public Ball(Coordinate2D initialLocation, Brickanoid brickanoid, int speed, int direction, Level level) {
        super("sprites/round_ball.png", initialLocation, new Size(10, 10));
        this.speed = speed;
        this.direction = direction;
        this.brickanoid = brickanoid;
        this.level = level;
        this.powerupHitBricksCounter = 0;
        this.slowDownActive = false;
        setMotion(speed, direction);
        setGravityConstant(GRAVITY);
        setFrictionConstant(FRICTION);
        level.setBallsOnScreen(level.getBallsOnScreen() + 1);
    }

    @Override
    public void onCollision(Collider collider) {
        setDirection(-getDirection() + 180);

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

        if (sceneBorder == SceneBorder.BOTTOM && level.getBallsOnScreen() <= 1) {
            if (Brickanoid.lives >= 1) {
                Brickanoid.lives--;
                Brickanoid.liveText.setOverlayText(Brickanoid.lives);
                this.resetBall();
            }

            if (Brickanoid.lives <= 0) {
                brickanoid.endGameAfterLoss();
            }
        } else if (level.getBallsOnScreen() > 1 && sceneBorder == SceneBorder.BOTTOM) {
            this.remove();
            level.setBallsOnScreen(level.getBallsOnScreen() - 1);
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
     * Deactives the ball slowdown when there are no bricks left over in the level
     */
    public void deactivateSlowdown() {
        slowDownActive = false;
        this.setSpeed(this.getSpeed() + 2.0);
    }

    /**
     * Resets the ball and spawns it randomly above the paddle into the game, so that the game can start again. This will only be used when the ball got out of the screen below
     */
    public void resetBall() {
        powerupHitBricksCounter = 0;
        slowDownActive = false;
        setMotion(speed, generateRandomDirection());
        setGravityConstant(GRAVITY);
        setFrictionConstant(FRICTION);
        resetLocation();
    }

    /**
     * Resets the location of the ball to the middle of the screen
     */
    public void resetLocation() {
        double newSpawnLocationX = brickanoid.getSCREEN_WIDTH() / 2.0;
        double newSpawnLocationY = brickanoid.getSCREEN_HEIGHT() / 2.0 + (brickanoid.getSCREEN_HEIGHT() / 4.0);
        this.setAnchorLocation(new Coordinate2D(newSpawnLocationX, newSpawnLocationY));
    }

    /**
     * Generates a random direction and chooses either the lowerbound or the upperbound.
     * Lowerbound for the left-side and upperBound for the right-side.
     * This will be used for shooting the ball after spawning the ball left or right.
     *
     * @return the random generated direction, either left or right
     */
    public int generateRandomDirection() {
        int randomDirection = 0;
        int lowerBound = 135;
        int upperBound = 225;
        RandomNumber randomDirectionNumber = new RandomNumber(1, 2);

        if(randomDirectionNumber.getValue() == 1) {
            randomDirection = lowerBound;
        }

        else if(randomDirectionNumber.getValue() == 2) {
            randomDirection = upperBound;
        }

        return randomDirection;
    }
}
