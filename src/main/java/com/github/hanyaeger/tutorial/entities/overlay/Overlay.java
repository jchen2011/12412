package com.github.hanyaeger.tutorial.entities.overlay;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.tutorial.Brickanoid;
import com.github.hanyaeger.tutorial.entities.text.OverlayText;

/**
 * This class is responsible for making the overlay when you are ingame like score, highscore and lives
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public class Overlay {
    private TextEntity scoreText;
    private TextEntity highscoreText;
    private TextEntity lifeText;
    private Brickanoid brickanoid;

    public Overlay(Coordinate2D location, Brickanoid brickanoid, OverlayText lifeText, OverlayText scoreText, OverlayText highscoreText) {
        this.brickanoid = brickanoid;
        this.lifeText = lifeText;
        this.scoreText = scoreText;
        this.highscoreText = highscoreText;
    }

    /**
     * Returns the amount of score that a player has
     *
     * @return the current score
     */
    public TextEntity getScoreText() {
        return scoreText;
    }

    /**
     * Returns the amount of highscore that a player has
     *
     * @return the current highscore
     */
    public TextEntity getHighscoreText() {
        return highscoreText;
    }

    /**
     * Returns the amount of lives that a player has
     *
     * @return the current lives
     */
    public TextEntity getLifeText() {
        return lifeText;
    }
}
