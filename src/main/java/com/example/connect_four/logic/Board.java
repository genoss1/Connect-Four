package com.example.connect_four.logic;

import java.util.ArrayList;

public class Board {
  private final int  MAX_ROW = 6;
  private final int MAX_COLUMN = 7;
  private final Field [][] fields = new Field[6][7];

  public Board() {
    for(int i = 0; i < MAX_ROW; i++) {
      for(int j = 0; j < MAX_COLUMN; j++) {
        fields[i][j] = Field.EMPTY_FIELD;
      }
    }
  }

  public void setEmptyBoard() {
    for(int i = 0; i < MAX_ROW; i++) {
      for(int j = 0; j < MAX_COLUMN; j++) {
        fields[i][j] = Field.EMPTY_FIELD;
      }
    }
  }

  public void addPawn (Field pawn, int column) {
    for(int i = 0; i < MAX_ROW; i++) {
      if(fields[i][column] == Field.EMPTY_FIELD){
        fields[i][column] = pawn;
        return;
      }
    }
  }

  public boolean findFour() {
    /*
      TODO: algorithm for finding four
   */
    return false;
  }

  public Field[][] getFields() {
    return fields;
  }

  public ArrayList<Integer> getIndexesOFFreeColumns () {
    ArrayList<Integer> columns = new ArrayList<>();

    for(int i = 0; i < MAX_COLUMN; i++) {
      if(fields[0][i] == Field.EMPTY_FIELD) {
        columns.add(i);
      }
    }

    return columns;
  }
}
