package com.github.hanyaeger.tutorial.entities.buttons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.tutorial.Brickanoid;
import javafx.scene.input.MouseButton;

/**
 * This class is responsible for making the play button
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */
public class PlayButton extends MenuButton {

    public PlayButton(String text, Coordinate2D initialLocation, Brickanoid brickanoid) {
        super(text, initialLocation, brickanoid);
    }
    
    @Override
    public void onMouseButtonPressed(MouseButton mouseButton, Coordinate2D coordinate2D) {
        brickanoid.startNewGame();
    }
}
