package com.example.connect_four.logic;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player {
    private final Field pawn;
    private final IntegerProperty points = new SimpleIntegerProperty(this, "points", 0);

    public Player(Field pawnColor) {
        pawn = pawnColor;
    }

    public int getPoints() {
        return points.getValue();
    }

    public void addPoint() {
        points.setValue(getPoints() + 1);
    }

    public StringBinding getIntegerProperty() {
        return points.asString();
    }

    public Field getPawn() {
        return pawn;
    }
}
