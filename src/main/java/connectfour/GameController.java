package connectfour;

import connectfour.logic.Board;
import connectfour.logic.Field;
import connectfour.logic.Game;
import connectfour.logic.Player;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.Objects;

public class GameController {
    @FXML
    private GridPane gridPaneBoard;
    @FXML
    private Text textPoints1;
    @FXML
    private Text textPoints2;
    @FXML
    private GridPane gridPaneSelectColumn;
    @FXML
    private GridPane gridPaneTriangles;

    private final Game game = new Game();

    @FXML
    public void initialize() {
        pointsBinding(textPoints1, game.getPlayer1());
        pointsBinding(textPoints2, game.getPlayer2());

        for(Node node : gridPaneTriangles.getChildren()) {
            ImageView triangle = (ImageView)node;
            triangle.setStyle("-fx-opacity: 0.4;");
        }

        newRound();
    }

    EventHandler<MouseEvent> displaySelectedCircle = (event) -> {
        for(Node node : gridPaneSelectColumn.getChildren()) {
            Circle circle = (Circle)node;
            circle.setFill(Color.TRANSPARENT);
        }

        for(Node node : gridPaneTriangles.getChildren()) {
            ImageView triangle = (ImageView)node;
            triangle.setStyle("-fx-opacity: 0.4;");
        }

        Node node = (Node)event.getTarget();
        Circle circle = (Circle) node;

        lightTriangle(GridPane.getColumnIndex(circle));

        Player currentPlayer = game.getCurrentPlayer();
        Color color = currentPlayer.getPawn().getColor();
        circle.setFill(color);
    };

    EventHandler<MouseEvent> selectColumn = (event) -> {

        Node node = (Node)event.getTarget();
        Circle circle = (Circle) node;

        Integer selectedColumn = GridPane.getColumnIndex(circle);
        if(selectedColumn == null) selectedColumn = 0;

        Board board = game.getBoard();
        Player currentPlayer = game.getCurrentPlayer();
        int row = board.addPawn(currentPlayer.getPawn(), selectedColumn);

        addCirclePawnToGridPaneBoard(row, selectedColumn);

        boolean isThereAnyFourOnBoard = game.getBoard().hasAnyFour();

        boolean isThereAnyColumnFree = false;

        for(int i = 0; i < Board.MAX_COLUMN; i++) {
            if(board.isColumnFree(i)) {
                isThereAnyColumnFree = true;
                break;
            }
        }

        if(isThereAnyFourOnBoard) {
            currentPlayer.addPoint();
            newRound();
        } else if(!isThereAnyColumnFree) {
            newRound();
        }else {
            game.switchPlayer();
            circle.setFill(game.getCurrentPlayer().getPawn().getColor());
            removeEventFromCircleWhenFullColumn();
        }

    };

    private void lightTriangle(Integer col) {
        for(Node node : gridPaneTriangles.getChildren()) {
            ImageView triangle = (ImageView)node;

            Integer column = GridPane.getColumnIndex(triangle);

            if(Objects.equals(col, column)) {
                triangle.setStyle("-fx-opacity: 1;");
            }
        }
    }

    private void pointsBinding(Text text, Player player) {
        text.textProperty().bind(player.getIntegerProperty());
    }

    private void addCirclePawnToGridPaneBoard(int row, int column) {

        Player currentPlayer = game.getCurrentPlayer();
        Circle circlePawn = new Circle(Field.RADIUS, currentPlayer.getPawn().getColor());
        gridPaneBoard.add(circlePawn, column, row);

    }

    private void newRound() {
        game.getBoard().setEmptyBoard();
        game.setRandomCurrentPlayer();

        clearBoard();
        addEventsToSelectingCircles();
        removeAllEventsFromCircle();

        addEventsToSelectingCircles();
    }

    private void removeEventFromCircleWhenFullColumn() {
        Board board = game.getBoard();

        for(Node node : gridPaneSelectColumn.getChildren()) {
            Circle circle = (Circle)node;

            Integer column = GridPane.getColumnIndex(circle);
            if(column == null) column = 0;

            if(!board.isColumnFree(column)) {
                circle.removeEventHandler(MouseEvent.MOUSE_CLICKED, selectColumn);
            }
        }
    }

    private void removeAllEventsFromCircle() {
        for(Node node : gridPaneSelectColumn.getChildren()) {
            Circle circle = (Circle)node;

            circle.removeEventHandler(MouseEvent.MOUSE_ENTERED, displaySelectedCircle);
            circle.removeEventHandler(MouseEvent.MOUSE_CLICKED, selectColumn);
        }
    }

    private void addEventsToSelectingCircles() {
        Player currentPlayer = game.getCurrentPlayer();

        for(Node node : gridPaneSelectColumn.getChildren()) {
            Circle circle = (Circle)node;

            if(GridPane.getRowIndex(circle) == null && GridPane.getColumnIndex(circle) == null  ) {
                circle.setFill(currentPlayer.getPawn().getColor());
            } else {
                circle.setFill(Color.TRANSPARENT);
            }

            circle.addEventHandler(MouseEvent.MOUSE_ENTERED, displaySelectedCircle);
            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, selectColumn);
        }
    }

    private void clearBoard() {
        gridPaneBoard.getChildren().clear();
        for (int i = 0; i < gridPaneBoard.getColumnCount(); i++) {
            for (int j = 0; j < gridPaneBoard.getRowCount(); j++) {
                Circle circleEmptyField = new Circle(Field.RADIUS,Field.EMPTY_FIELD.getColor());
                gridPaneBoard.add(circleEmptyField,i,j);
            }
        }
    }
}
