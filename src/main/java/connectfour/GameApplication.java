package connectfour;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameApplication extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.show();
    }

    public static void  changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(GameApplication.class.getResource(fxml)));
        primaryStage.getScene().setRoot(pane);
    }

    public static void  changeScene(Parent scene) {
        primaryStage.getScene().setRoot(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}