package com.github.hanyaeger.tutorial.entities.buttons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import com.github.hanyaeger.api.userinput.MouseEnterListener;
import com.github.hanyaeger.api.userinput.MouseExitListener;
import com.github.hanyaeger.tutorial.Brickanoid;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class is representative for every button in the menu that inherits from the MenuButton class.
 * When you hover over the button there will be another color then normal.
 *
 * @author Johnny Chen
 * @author Daniël Roth
 */

public abstract class MenuButton extends TextEntity implements MouseButtonPressedListener, MouseEnterListener, MouseExitListener {
    final int FONTWEIGHT = 30;
    protected Brickanoid brickanoid;

    public MenuButton(String text, Coordinate2D initialLocation, Brickanoid brickanoid) {
        super(initialLocation, text);
        setFill(Color.WHITE);
        setFont(Font.font("Roboto", FontWeight.BOLD, FONTWEIGHT));
        this.brickanoid = brickanoid;
    }

    @Override
    public void onMouseEntered(){
        setFill(Color.VIOLET);
        setCursor(Cursor.HAND);
    }

    @Override
    public void onMouseExited(){
        setFill(Color.WHITE);
        setCursor(Cursor.DEFAULT);
    }
}
