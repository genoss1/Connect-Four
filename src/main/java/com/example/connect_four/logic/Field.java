package com.example.connect_four.logic;

import javafx.scene.paint.Color;

public enum Field {
  RED_PAWN(Color.RED),
  YELLOW_PAWN(Color.YELLOW),
  EMPTY_FIELD(Color.TRANSPARENT);

  private final Color color;

  private final int radius = 30;

  Field(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public int getRadius() { return radius; }
}
