package com.example.connect_four.logic;

import javafx.scene.paint.Color;

public enum Field {
  RED_PAWN(Color.web("#FF6363")),
  YELLOW_PAWN(Color.web("#F8B400")),
  EMPTY_FIELD(Color.web("#D2CDBB"));

  private final Color color;

  private final int radius = 40; //czy ten promień będzie się zmieniać w trakcie działania programu?, bo jeśli nie to powinien być static

  Field(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public int getRadius() { return radius; }
}
