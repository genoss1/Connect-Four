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
        buttonPlayAgain.setOnAction(event -> playAgain());
        buttonExit.setOnAction(event -> exitGame());
    }


    private void playAgain() {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("game-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 320, 240);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.show();
        buttonPlayAgain.getScene().getWindow().hide();
    }

    private void exitGame() {
        Platform.exit();
    }
}
