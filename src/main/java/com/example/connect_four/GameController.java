package com.example.connect_four;

import com.example.connect_four.logic.Board;
import com.example.connect_four.logic.Field;
import com.example.connect_four.logic.Game;
import com.example.connect_four.logic.Player;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class GameController {
    @FXML
    public GridPane gridPaneBoard;
    @FXML
    public Text textPoints1;
    @FXML
    public Text textPoints2;
    @FXML
    private GridPane gridPaneSelectColumn;

    Game game = new Game();


    @FXML
    public void initialize() {

        pointsBinding(textPoints1, game.getPlayer1());
        pointsBinding(textPoints2, game.getPlayer2());

        newRound();


    }



    EventHandler<MouseEvent> displayHideSelectingCircle = (event) -> {
        for(Node node : gridPaneSelectColumn.getChildren()) {
            Circle circle = (Circle)node;
            circle.setFill(Color.TRANSPARENT);
        }

        Node node = (Node)event.getTarget();
        Circle circle = (Circle) node;

        Player currentPlayer = game.getCurrentPlayer();
        Color color = currentPlayer.getPawn().getColor();
        circle.setFill(color);


    };

    EventHandler<MouseEvent> selectCircle = (event) -> {

        Node node = (Node)event.getTarget();
        Circle circle = (Circle) node;


        int selectedColumn = GridPane.getColumnIndex(circle);

        Board board = game.getBoard();
        Player currentPlayer = game.getCurrentPlayer();
        int row = board.addPawn(currentPlayer.getPawn(), selectedColumn);

        addCircleToView(row, selectedColumn);

        boolean isThereAnyFourOnBoard = game.getBoard().hasAnyFour();
        if(isThereAnyFourOnBoard) {
            currentPlayer.addPoint();
            newRound();
        }


        game.switchPlayer();
        /*
        FIXME: nie działa
         */
        circle.setFill(currentPlayer.getPawn().getColor());

        removeEventFromCircleWhenFullColumn();

    };

    void pointsBinding(Text text, Player player) {
        text.textProperty().bind(player.getIntegerProperty());
    }

    void addCircleToView(int row, int column) {

        Player currentPlayer = game.getCurrentPlayer();
        Circle circle = new Circle(30, currentPlayer.getPawn().getColor());
        gridPaneBoard.add(circle, column, row);

    }

    private void newRound() {
        game.getBoard().setEmptyBoard();
        game.setRandomCurrentPlayer();

        clearBoard();
        addEventsToCircles();
        removeAllEventsFromCircle();

        addEventsToCircles();


    }

    private void removeEventFromCircleWhenFullColumn() {
        Board board = game.getBoard();

        for(Node node : gridPaneSelectColumn.getChildren()) {
            Circle circle = (Circle)node;

            Integer column = GridPane.getColumnIndex(circle);
            if(column == null) column = 0;

            if(!board.isColumnFree(column)) {
                circle.removeEventHandler(MouseEvent.MOUSE_CLICKED, selectCircle);
            }
        }
    }

    private void removeAllEventsFromCircle() {
        for(Node node : gridPaneSelectColumn.getChildren()) {
            Circle circle = (Circle)node;

            circle.removeEventHandler(MouseEvent.MOUSE_ENTERED, displayHideSelectingCircle);
            circle.removeEventHandler(MouseEvent.MOUSE_CLICKED, selectCircle);
        }
    }

    private void addEventsToCircles() {
        Player currentPlayer = game.getCurrentPlayer();

        for(Node node : gridPaneSelectColumn.getChildren()) {
            Circle circle = (Circle)node;

            if(GridPane.getRowIndex(circle) == null && GridPane.getColumnIndex(circle) == null  ) {
                circle.setFill(currentPlayer.getPawn().getColor());
            } else {
                circle.setFill(Color.TRANSPARENT);
            }

            circle.addEventHandler(MouseEvent.MOUSE_ENTERED, displayHideSelectingCircle);
            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, selectCircle);
        }
    }

    private void clearBoard() {
        /*
        FIXME: NIE DZIALA
         */
        gridPaneBoard.getChildren().removeAll();
    }


}