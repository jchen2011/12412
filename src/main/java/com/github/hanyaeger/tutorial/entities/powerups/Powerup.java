package com.github.hanyaeger.tutorial.entities.powerups;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Newtonian;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.tutorial.entities.paddle.Paddle;

/**
 * This class is responsible for making the powerup and that it removes itself when it collides with the paddle
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public abstract class Powerup extends DynamicSpriteEntity implements Collider, Collided, Newtonian {

    public Powerup(String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation, new Size(40, 30));
        setGravityConstant(0.05);
    }

    public void activatePowerup() {
        SoundClip pickuppowerup = new SoundClip("audio/soundeffect_powerup.mp3" , 1);
        pickuppowerup.setVolume(0.25);
        pickuppowerup.play();
    }

    @Override
    public void onCollision(Collider collider) {
        if (collider instanceof Paddle) {
            this.remove();
        }
    }
}
