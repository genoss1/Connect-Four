package connectfour.logic;

public class Board {
    public static final int MAX_ROW = 9;
    public static final int MAX_COLUMN = 9;
    private final Field[][] fields = new Field[MAX_ROW][MAX_COLUMN];

    public Board() {
        setEmptyBoard();
    }

    public void setEmptyBoard() {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                fields[i][j] = Field.EMPTY_FIELD;
            }
        }
    }

    public int addPawn(Field pawn, int column) {
        for (int i = (MAX_ROW - 1); i >= 0; i--) {
            if (fields[i][column] == Field.EMPTY_FIELD) {
                fields[i][column] = pawn;
                return i;
            }
        }
        return -1;
    }

    public boolean hasAnyFour() {
        return hasFour(Field.YELLOW_PAWN) || hasFour(Field.RED_PAWN);
    }

    public boolean hasFour(Field pawn) {
        return hasFourVertical(pawn) || hasFourHorizontal(pawn) || hasFourBiasUp(pawn) || hasFourBiasDown(pawn);
    }

    public boolean hasFourVertical(Field pawn) {
        for (int i = 0; i < (MAX_ROW - 3); i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                if (fields[i][j] == pawn && fields[i + 1][j] == pawn && fields[i + 2][j] == pawn && fields[i + 3][j] == pawn) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasFourHorizontal(Field pawn)
    {
        for (int i = 0; i < (MAX_ROW); i++) {
            for (int j = 0; j < (MAX_COLUMN - 3); j++) {
                if (fields[i][j] == pawn && fields[i][j + 1] == pawn && fields[i][j + 2] == pawn && fields[i][j + 3] == pawn) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasFourBiasUp(Field pawn)
    {
        for (int i = 3; i < MAX_ROW; i++) {
            for (int j = 0; j < (MAX_COLUMN - 3); j++) {
                if (fields[i][j] == pawn && fields[i - 1][j + 1] == pawn && fields[i - 2][j + 2] == pawn && fields[i - 3][j + 3] == pawn) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasFourBiasDown(Field pawn)
    {
        for (int i = 0; i < (MAX_ROW - 3); i++) {
            for (int j = 0; j < (MAX_COLUMN - 3); j++) {
                if (fields[i][j] == pawn && fields[i + 1][j + 1] == pawn && fields[i + 2][j + 2] == pawn && fields[i + 3][j + 3] == pawn) {
                    return true;
                }
            }
        }
        return false;
    }

    public Field[][] getFields() {
        return fields;
    }

    public boolean isColumnFree(int column) {
        for (int i = 0; i < MAX_ROW; i++) {
            if (fields[i][column] == Field.EMPTY_FIELD) {
                return true;
            }
        }
        return false;
    }

    public boolean hasFreeColumn() {
        for (int i = 0; i < Board.MAX_COLUMN; i++) {
            if (isColumnFree(i)) {
                return true;
            }
        }
        return false;
    }
}
