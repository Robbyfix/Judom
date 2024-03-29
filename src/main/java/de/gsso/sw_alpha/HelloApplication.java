package de.gsso.sw_alpha;

import de.gsso.sw_alpha.objects.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Judom V1.0");
        stage.setScene(scene);
        stage.show();
        MenuController.mediaPlayer.setVolume(50);
        MenuController.mediaPlayer.setOnEndOfMedia(() -> MenuController.mediaPlayer.seek(new javafx.util.Duration(30)));
        MenuController.mediaPlayer.play();
        Player.mediaPlayer.setVolume(50);
    }

    public static void main(String[] args) {
        launch();
    }
}