package com.github.hanyaeger.tutorial.entities.powerups;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.tutorial.Brickanoid;

/**
 * This class is responsible for making the extra life power-up.
 * When you pick up this power-up, you will get an extra life added to your amount of lives.
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */
public class PowerupExtraLife extends Powerup{
    public PowerupExtraLife(Coordinate2D initialLocation) {
        super("sprites/powerup_extra_life.png", initialLocation);
    }

    /**
     * Actives the extra life power-up by adding an extra life
     */
    @Override
    public void activatePowerup() {
        super.activatePowerup();
        Brickanoid.lives++;
        Brickanoid.liveText.setOverlayText(Brickanoid.lives);
    }
}
