package connectfour;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class EndController {

    @FXML
    private Button buttonPlayAgain;

    @FXML
    private Button buttonExit;

    @FXML
    private Text textWinnerName;

    @FXML
    private Text textPointsInGame;

    @FXML
    private GridPane gridPaneTextContainer;

    private Storage stoarge;

    public void receiveStorage(Storage storage) {
        this.stoarge = storage;
        displayWinnerName();
        displayPointsInGame();
        setColourOfContainer();
        setButtons();
    }

    public void displayWinnerName() {
        textWinnerName.setText(stoarge.getWinner().getName() + " wins!");
    }

    public void displayPointsInGame() {
        textPointsInGame.setText(stoarge.getWinner().getPoints() + ":" + stoarge.getLooser().getPoints());
    }

    public void setColourOfContainer() {
        gridPaneTextContainer.setStyle("-fx-background-color: #" + stoarge.getWinner().getPawn().getColor().toString().substring(2, 8) + ';');
    }

    public void setButtons() {
        buttonPlayAgain.setOnAction(event -> {
            try {
                playAgain();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        buttonExit.setOnAction(event -> exitGame());
    }

    private void playAgain() throws IOException {
        GameApplication.changeScene("game-view.fxml");
    }

    private void exitGame() {
        Platform.exit();
    }
}
