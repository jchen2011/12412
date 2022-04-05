package com.github.hanyaeger.tutorial.entities.paddle;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Newtonian;
import com.github.hanyaeger.api.entities.SceneBorderTouchingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.hanyaeger.tutorial.Brickanoid;
import com.github.hanyaeger.tutorial.entities.powerups.Powerup;
import javafx.scene.input.KeyCode;

import java.util.Set;

/**
 * This class is responsible for making the paddle in the Brickanoid game and does something based on specific actions
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public class Paddle extends DynamicSpriteEntity implements Collider, Collided, KeyListener, SceneBorderTouchingWatcher, Newtonian {
    private final int MOVEMENT_SPEED = 6;
    private final double GRAVITY = 0;
    private final double FRICTION = 0.1;
    private int lives;
    private Brickanoid brickanoid;

    public Paddle(Coordinate2D initialLocation, Brickanoid brickanoid, int lives) {
        super("sprites/VausSpacecraftLarge.png", initialLocation);
        setGravityConstant(GRAVITY);
        setFrictionConstant(FRICTION);
        this.brickanoid = brickanoid;
        this.lives = lives;
    }

    @Override
    public void notifyBoundaryTouching(SceneBorder sceneBorder) {
        switch(sceneBorder){
            case TOP:
                setAnchorLocationY(1);
                break;
            case LEFT:
                setAnchorLocationX(1);
                break;
            case RIGHT:
                setAnchorLocationX(getSceneWidth() - getWidth() - 1);
            default:
                break;
        }
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if(pressedKeys.contains(KeyCode.LEFT)){
            setMotion(MOVEMENT_SPEED,270d);
            setCurrentFrameIndex(0);
        } else if(pressedKeys.contains(KeyCode.RIGHT)){
            setMotion(MOVEMENT_SPEED,90d);
            setCurrentFrameIndex(1);
        }
    }

    @Override
    public void onCollision(Collider collider) {
        if (collider instanceof Powerup powerup) {
            powerup.activatePowerup();

        }
    }

    /**
     * Resets the gravity and friction of the paddle
     */
    public void resetGravityAndFriction() {
        setGravityConstant(GRAVITY);
        setFrictionConstant(FRICTION);
    }

    /**
     * Gets the amount of lives that are left over
     *
     * @return the current lives that are left over
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets the new lives that replaces the old lives
     *
     * @param lives the new lives that you want to replace
     */
    public void setLives(int lives) {
        this.lives = lives;
    }
}
