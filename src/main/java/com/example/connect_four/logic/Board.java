package com.example.connect_four.logic;

public class Board {
    private final int MAX_ROW = 6;
    private final int MAX_COLUMN = 7;
    private final Field[][] fields = new Field[6][7];

    public Board() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                fields[i][j] = Field.EMPTY_FIELD;
            }
        }
    }

    public void setEmptyBoard() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                fields[i][j] = Field.EMPTY_FIELD;
            }
        }
    }

    public void addPawn(Field pawn, int column) {
        for (int i = (MAX_ROW - 1); i >= 0; i--) {
            if (fields[i][column] == Field.EMPTY_FIELD) {
                fields[i][column] = pawn;
                return;
            }
        }
    }

    //funkcja do wyświetlania w konsoli oczywiście do usunięcia jak już dodamy fxml, 0-puste pole, Y-Yellow, R-Red
    public void tempPrint() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                if (fields[i][j] == Field.EMPTY_FIELD) {
                    System.out.printf(" O ");
                } else if (fields[i][j] == Field.YELLOW_PAWN) {
                    System.out.printf(" Y ");
                } else {
                    System.out.printf(" R ");
                }
            }
            System.out.println();
        }
    }


    //return true if found any matching "4's"
    public boolean hasFour() {

        //sprawdzenie czy poziomo istenieje jakaś czwórka
        for (int i = 0; i < (MAX_ROW); i++) {
            for (int j = 0; j < (MAX_COLUMN - 3); j++) {
                if (fields[i][j] == Field.RED_PAWN && fields[i][j + 1] == Field.RED_PAWN && fields[i][j + 2] == Field.RED_PAWN && fields[i][j + 3] == Field.RED_PAWN) {
                    return true;
                } else if (fields[i][j] == Field.YELLOW_PAWN && fields[i][j + 1] == Field.YELLOW_PAWN && fields[i][j + 2] == Field.YELLOW_PAWN && fields[i][j + 3] == Field.YELLOW_PAWN) {
                    return true;
                }
            }
        }

        //sprawdzenie czy pionowo istnieje jakaś czwórka
        for (int i = 0; i < (MAX_ROW - 3); i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                if (fields[i][j] == Field.RED_PAWN && fields[i + 1][j] == Field.RED_PAWN && fields[i + 2][j] == Field.RED_PAWN && fields[i + 3][j] == Field.RED_PAWN) {
                    return true;
                } else if (fields[i][j] == Field.YELLOW_PAWN && fields[i + 1][j] == Field.YELLOW_PAWN && fields[i + 2][j] == Field.YELLOW_PAWN && fields[i + 3][j] == Field.YELLOW_PAWN) {
                    return true;
                }
            }
        }

        //sprawdzenie na ukos 1 "do dołu"
        for (int i = 0; i < (MAX_ROW - 3); i++) {
            for (int j = 0; j < (MAX_COLUMN - 3); j++) {
                if (fields[i][j] == Field.RED_PAWN && fields[i + 1][j + 1] == Field.RED_PAWN && fields[i + 2][j + 2] == Field.RED_PAWN && fields[i + 3][j + 3] == Field.RED_PAWN) {
                    return true;
                } else if (fields[i][j] == Field.YELLOW_PAWN && fields[i + 1][j + 1] == Field.YELLOW_PAWN && fields[i + 2][j + 2] == Field.YELLOW_PAWN && fields[i + 3][j + 3] == Field.YELLOW_PAWN) {
                    return true;
                }
            }
        }

        //sprawdzenie na ukos 2 "do góry"
        for (int i = 3; i < MAX_ROW; i++) {
            for (int j = 0; j < (MAX_COLUMN - 3); j++) {
                if (fields[i][j] == Field.RED_PAWN && fields[i - 1][j + 1] == Field.RED_PAWN && fields[i - 1][j + 2] == Field.RED_PAWN && fields[i - 3][j + 3] == Field.RED_PAWN) {
                    return true;
                } else if (fields[i][j] == Field.YELLOW_PAWN && fields[i - 1][j + 1] == Field.YELLOW_PAWN && fields[i - 2][j + 2] == Field.YELLOW_PAWN && fields[i - 3][j + 3] == Field.YELLOW_PAWN) {
                    return true;
                }
            }
        }

        return false;
    }

    public Field[][] getFields() {
        return fields;
    }

    public boolean checkIfFreeColumn(int column) {
        for (int i = 0; i < MAX_ROW; i++) {
            if (fields[i][column] == Field.EMPTY_FIELD) {
                return true;
            }
        }
        return false;
    }

}
