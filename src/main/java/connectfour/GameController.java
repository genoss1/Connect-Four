package connectfour;

import connectfour.logic.Board;
import connectfour.logic.Field;
import connectfour.logic.Game;
import connectfour.logic.Player;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
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
            Parent endScene;
            if (game.isWinner()) {
                try {
                    endScene = sendStorage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                GameApplication.changeScene(endScene);
            }else{
                setWinningFour();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer finalSelectedColumn = selectedColumn;
                Platform.runLater(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    addCirclePawnToGridPaneBoard(row, finalSelectedColumn);
                    newRound();
                });
            }

        } else if(!isThereAnyColumnFree) {
            newRound();
        }else {
            game.switchPlayer();
            circle.setFill(game.getCurrentPlayer().getPawn().getColor());
            removeEventFromCircleWhenFullColumn();
        }
    };

    private Parent sendStorage() throws IOException {
        Storage storage = new Storage(game.getWinner(), game.getLooser()); //or assign seperatly each objects

        FXMLLoader loader = new FXMLLoader(getClass().getResource("end-view.fxml"));
        Parent scene = loader.load();

        EndController endController = loader.getController();
        endController.receiveStorage(storage);

        return scene;
    }

    private void setWinningFour() {
        if (game.getBoard().hasFourVertical(game.getCurrentPlayer().getPawn())) {
            for (int i = 0; i < (6 - 3); i++) {
                for (int j = 0; j < 7; j++) {
                    if (game.getBoard().getFields()[i][j] == game.getCurrentPlayer().getPawn() && game.getBoard().getFields()[i + 1][j] == game.getCurrentPlayer().getPawn() && game.getBoard().getFields()[i + 2][j] == game.getCurrentPlayer().getPawn() && game.getBoard().getFields()[i + 3][j] == game.getCurrentPlayer().getPawn()) {
                        addCirclePawnToGridPaneBoardWin(i, j);
                        addCirclePawnToGridPaneBoardWin(i + 1, j);
                        addCirclePawnToGridPaneBoardWin(i + 2, j);
                        addCirclePawnToGridPaneBoardWin(i + 3, j);
                    }
                }
            }
        } else if (game.getBoard().hasFourBiasUp(game.getCurrentPlayer().getPawn())) {
            for (int i = 3; i < 6; i++) {
                for (int j = 0; j < (7 - 3); j++) {
                    if (game.getBoard().getFields()[i][j] == game.getCurrentPlayer().getPawn() && game.getBoard().getFields()[i - 1][j + 1] == game.getCurrentPlayer().getPawn() && game.getBoard().getFields()[i - 2][j + 2] == game.getCurrentPlayer().getPawn() && game.getBoard().getFields()[i - 3][j + 3] == game.getCurrentPlayer().getPawn()) {
                        addCirclePawnToGridPaneBoardWin(i, j);
                        addCirclePawnToGridPaneBoardWin(i - 1, j + 1);
                        addCirclePawnToGridPaneBoardWin(i - 2, j + 2);
                        addCirclePawnToGridPaneBoardWin(i - 3, j + 3);
                    }
                }
            }
        } else if (game.getBoard().hasFourBiasDown(game.getCurrentPlayer().getPawn())) {
            for (int i = 0; i < (6 - 3); i++) {
                for (int j = 0; j < (7 - 3); j++) {
                    if (game.getBoard().getFields()[i][j] == game.getCurrentPlayer().getPawn() && game.getBoard().getFields()[i + 1][j + 1] == game.getCurrentPlayer().getPawn() && game.getBoard().getFields()[i + 2][j + 2] == game.getCurrentPlayer().getPawn() && game.getBoard().getFields()[i + 3][j + 3] == game.getCurrentPlayer().getPawn()) {
                        addCirclePawnToGridPaneBoardWin(i, j);
                        addCirclePawnToGridPaneBoardWin(i + 1, j + 1);
                        addCirclePawnToGridPaneBoardWin(i + 2, j + 2);
                        addCirclePawnToGridPaneBoardWin(i + 3, j + 3);
                    }
                }
            }
        } else if (game.getBoard().hasFourHorizontal(game.getCurrentPlayer().getPawn())) {
            for (int i = 0; i < (6); i++) {
                for (int j = 0; j < (7 - 3); j++) {
                    if (game.getBoard().getFields()[i][j] == game.getCurrentPlayer().getPawn() && game.getBoard().getFields()[i][j + 1] == game.getCurrentPlayer().getPawn() && game.getBoard().getFields()[i][j + 2] == game.getCurrentPlayer().getPawn() && game.getBoard().getFields()[i][j + 3] == game.getCurrentPlayer().getPawn()) {
                        addCirclePawnToGridPaneBoardWin(i, j);
                        addCirclePawnToGridPaneBoardWin(i, j + 1);
                        addCirclePawnToGridPaneBoardWin(i, j + 2);
                        addCirclePawnToGridPaneBoardWin(i, j + 3);
                    }
                }
            }
        }
    }

    private void addCirclePawnToGridPaneBoardWin(int row, int column) {
        Player currentPlayer = game.getCurrentPlayer();
        Circle circlePawn = new Circle(Field.RADIUS, currentPlayer.getPawn().getColor());
        circlePawn.setStroke(Color.web("#000000"));
        circlePawn.setStrokeWidth(5.0);
        gridPaneBoard.add(circlePawn, column, row);
    }

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
