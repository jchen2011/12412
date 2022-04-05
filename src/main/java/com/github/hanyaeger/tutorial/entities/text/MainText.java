package com.github.hanyaeger.tutorial.entities.text;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class is responsible for making the text in the menu scene and game over scene with some styling
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public class MainText extends TextEntity {

    public MainText(Coordinate2D initialLocation, String text, int size) {
        super(initialLocation, text);
        setAnchorPoint(AnchorPoint.CENTER_CENTER);
        setFill(Color.WHITE);
        setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, size));
    }
}
