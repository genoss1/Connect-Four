package connectfour.logic;

import java.util.Random;

public class Game {
    public static final int MAX_POINTS = 5;
    private final Player player1 = new Player(Field.RED_PAWN, "Player 1");
    private final Player player2 = new Player(Field.YELLOW_PAWN, "Player 2");
    private Player currentPlayer;
    private final Board board = new Board();

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void setRandomCurrentPlayer() {
        Random random = new Random();
        Player[] players = {player1, player2};
        currentPlayer = players[random.nextInt(2)];
    }

    public void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public boolean isWinner() {
        return player1.getPoints() == MAX_POINTS || player2.getPoints() == MAX_POINTS;
    }

    public Player getWinner() {
        if (player1.getPoints() == MAX_POINTS) {
            return player1;
        }
        return player2;
    }

    public Player getLooser() {
        if (player1.getPoints() != MAX_POINTS) {
            return player1;
        }
        return player2;
    }
}
