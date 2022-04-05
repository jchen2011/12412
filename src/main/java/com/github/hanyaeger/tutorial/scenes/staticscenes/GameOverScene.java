package com.github.hanyaeger.tutorial.scenes.staticscenes;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.github.hanyaeger.tutorial.Brickanoid;
import com.github.hanyaeger.tutorial.entities.buttons.PlayButton;
import com.github.hanyaeger.tutorial.entities.buttons.QuitButton;
import com.github.hanyaeger.tutorial.entities.text.MainText;

/**
 * This class shows the game over scene when there are no lives left anymore or when the game is finished
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public class GameOverScene extends StaticScene {
    private Brickanoid brickanoid;

    public GameOverScene(Brickanoid brickanoid) {
        this.brickanoid = brickanoid;
    }
    @Override
    public void setupScene() {
        setBackgroundAudio("audio/menu_theme.mp3");
        setBackgroundAudioVolume(0);
        setBackgroundImage("backgrounds/gameover_bg.jpg");
    }

    @Override
    public void setupEntities() {
        // setup title text
        var brickanoidText = new MainText(
                new Coordinate2D(getWidth() / 2, getHeight() / 6),
                "Brickanoid",
                80
        );
        addEntity(brickanoidText);

        // setup game over (you won/you lost) text
        String gameOver = brickanoid.getPlayerWon() ? "You Won!" : "You Lost!";
        var gameOverText = new MainText(
                new Coordinate2D(getWidth() / 2, getHeight() / 4 + (getHeight() / 20)),
                gameOver,
                40
        );
        addEntity(gameOverText);

        // setup score text
        var scoreText = new MainText(
                new Coordinate2D(getWidth() / 2, getHeight() / 2 - (getHeight() / 20) - 40),
                "Score: " + Brickanoid.score,
                40
        );
        addEntity(scoreText);

        // setup highscore text
        var highscoreText = new MainText(
                new Coordinate2D(getWidth() / 2, getHeight() / 2 - (getHeight() / 20) + 40),
                "Highscore: " + Brickanoid.highscore,
                40
        );
        addEntity(highscoreText);

        // setup startbutton
        var playAgainButton = new PlayButton(
                "Play again",
                new Coordinate2D(getWidth() / 2, getHeight() / 2 + (getHeight()/ 8)),
                brickanoid
        );
        playAgainButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        addEntity(playAgainButton);

        // setup quitbutton
        var quitButton = new QuitButton(
                "Quit game",
                new Coordinate2D(getWidth() / 2, getHeight() - getHeight() / 4),
                brickanoid
        );
        quitButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);

        addEntity(quitButton);
    }
}
