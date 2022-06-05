package connectfour;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class EndController {

    @FXML
    private Text textWinnerName;

    @FXML
    private Text textPointsInGame;

    @FXML
    private GridPane gridPaneTextContainer;

    private Storage storage;

    public void receiveStorage(Storage storage) {
        this.storage = storage;
        displayWinnerName();
        displayPointsInGame();
        setColourOfContainer();
    }

    private void displayWinnerName() {
        textWinnerName.setText(storage.getWinner().getName() + " wins!");
    }

    private void displayPointsInGame() {
        textPointsInGame.setText(storage.getWinner().getPoints() + ":" + storage.getLooser().getPoints());
    }

    private void setColourOfContainer() {
        Color winnerColor = storage.getWinner().getPawn().getColor();
        gridPaneTextContainer.setStyle("-fx-background-color: #" + winnerColor.toString().substring(2, 8) + ';');
    }

    public void playAgain() throws IOException {
        GameApplication.changeScene("game-view.fxml");
    }

    public void exitGame() {
        Platform.exit();
    }
}
