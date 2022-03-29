package de.gsso.sw_alpha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button start;

    @FXML
    private Button quit;

    @FXML
    private Button settings;

    @FXML
    private Button Ret;

    public void handleStartGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("Game.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleOpenSettingsAction(){
        start.setVisible(false);
        quit.setVisible(false);
        settings.setVisible(false);
        Ret.setVisible(true);
    }

    public void handleCloseSettingsAction(){
        start.setVisible(true);
        quit.setVisible(true);
        settings.setVisible(true);
        Ret.setVisible(false);
    }

    public void handleQuitGame(ActionEvent event) throws IOException{
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
