package com.example.connect_four.logic;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private final int MAX_POINTS = 10;
    private final Player player1 = new Player(Field.RED_PAWN);
    private final Player player2 = new Player(Field.YELLOW_PAWN);
    private Player currentPlayer;
    private final Board board = new Board();
    private final Random random = new Random();

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

    public int getMAX_POINTS() {
        return MAX_POINTS;
    } //zapis jest zły, a po za tym po co getter do czegoś co powinno być public static final

    public void setRandomCurrentPlayer() {
        Player[] players = {player1, player2};
        currentPlayer = players[random.nextInt(1)];
    }

    public void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }
}
