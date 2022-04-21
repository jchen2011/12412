package com.github.hanyaeger.tutorial.scenes.staticscenes;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.github.hanyaeger.tutorial.Brickanoid;
import com.github.hanyaeger.tutorial.entities.buttons.PlayButton;
import com.github.hanyaeger.tutorial.entities.buttons.QuitButton;
import com.github.hanyaeger.tutorial.entities.text.MainText;
import javafx.scene.Cursor;

/**
 * This class is responsible for making the menu scene (first screen)
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public class MenuScene extends StaticScene {
    private Brickanoid brickanoid;

    public MenuScene(Brickanoid brickanoid) {
        this.brickanoid = brickanoid;
    }

    @Override
    public void setupScene(){
        setBackgroundAudio("audio/menu_theme.mp3");
        setBackgroundAudioVolume(0.2);
        setBackgroundImage("backgrounds/background.jpg");
    }

    @Override
    public void setupEntities(){
        // setup title text
        var brickanoidText = new MainText(
                new Coordinate2D(getWidth() / 2, getHeight() / 6),
                "Brickanoid",
                80
        );
        addEntity(brickanoidText);

        // setup startbutton
        var startButton = new PlayButton(
                "Play game",
                new Coordinate2D(getWidth() / 2, getHeight() / 2),
                brickanoid
        );
        startButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        addEntity(startButton);

        // setup quitbutton
        var quitButton = new QuitButton(
                "Quit game",
                new Coordinate2D(getWidth() / 2, getHeight() - getHeight() / 4),
                brickanoid
        );
        quitButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);

        addEntity(quitButton);
    }

    @Override
    public void setCursor(Cursor cursor) {
        super.setCursor(cursor);
    }

    @Override
    public double getWidth() {
        return super.getWidth();
    }

    @Override
    public double getHeight() {
        return super.getHeight();
    }
}
