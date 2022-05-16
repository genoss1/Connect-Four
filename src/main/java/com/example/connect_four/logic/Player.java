package com.example.connect_four.logic;

public class Player {
  private final Field pawn;
  private int points = 0;

  public Player(Field pawnColor) {
    pawn = pawnColor;
  }

  public int getPoints() {
    return points;
  }

  public void addPoint() {
    this.points ++;
  }

  public Field getPawn() {
    return pawn;
  }
}
