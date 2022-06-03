package connectfour;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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


    public void displayWinnerName(String playerName) {
        textWinnerName.setText(playerName + " wins!");
    }

    public void displayPointsInGame(int firstPlayerPoints, int secondPlayerPoints) {
        textPointsInGame.setText(firstPlayerPoints + ":" + secondPlayerPoints);
    }

    private static String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public void setColourOfContainer(Color color) {
        gridPaneTextContainer.setStyle("-fx-background-color: " + toHexString(color) + ';');
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
