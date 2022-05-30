package connectfour.logic;

import javafx.scene.paint.Color;

public enum Field {
  RED_PAWN(Color.web("#FF6363")),
  YELLOW_PAWN(Color.web("#F8B400")),
  EMPTY_FIELD(Color.web("#D2CDBB"));

  private final Color color;

  public static final int RADIUS = 40;

  Field(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

}
