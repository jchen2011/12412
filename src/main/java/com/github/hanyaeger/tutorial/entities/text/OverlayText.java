package com.github.hanyaeger.tutorial.entities.text;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicTextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class is responsible for making the text in the overlay, that can update
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public class OverlayText extends DynamicTextEntity {
    private String text;
    private int value;

    public OverlayText(Coordinate2D initialLocation, String text, int value) {
        super(initialLocation, text + ": " + value);
        this.text = text;
        this.value = value;
        this.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        this.setFill(Color.WHITE);
        this.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 20));
    }

    public void setOverlayText(int sentValue){
        this.value = sentValue;
        this.setText(text + ": " + value);
    }
}
