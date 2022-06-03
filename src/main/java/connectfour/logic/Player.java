package connectfour.logic;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player {
    private final Field pawn;
    private final String name;
    private final IntegerProperty points = new SimpleIntegerProperty(this, "points", 0);

    public Player(Field pawnColor, String playerName) {
        pawn = pawnColor;
        name = playerName;
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

    public String getName() { return name; }
}
