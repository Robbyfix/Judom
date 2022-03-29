package de.gsso.sw_alpha;

import de.gsso.sw_alpha.misc.keyevent.KeyPressEvent;
import de.gsso.sw_alpha.misc.keyevent.KeyReleaseEvent;
import de.gsso.sw_alpha.objects.Ground;
import de.gsso.sw_alpha.objects.Player;
import de.gsso.sw_alpha.objects.Spikes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    private Player spieler;
    private boolean first;
    private Ground[] grounds;
    private Spikes[] spikes;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Pane canvas;

    @FXML
    private Pane playerPane;

    @FXML
    private Button demo;

    @FXML
    private Pane QuickMenu;

    @FXML
    private Pane background;

    @FXML
    private Button MaMe;

    @FXML
    private Button Cont;

    @FXML
    private Button Settings;

    @FXML
    private Button Quit;

    @FXML
    private Button Ret;

    public HelloController(){

    }

    @FXML
    public void handleDemoLevel() {
        if(first==false) {
            ImageView bg = new ImageView(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/Background/GrassLand_Background_Guide.png")));
            background.getChildren().add(bg);
            if (spieler == null) {                            //Check, ob spieler nicht null ist
                spieler = new Player(canvas, playerPane, QuickMenu);               //Neuer Spieler instanziiert
                canvas.getScene().getRoot().setOnKeyPressed(new KeyPressEvent(spieler));
                canvas.getScene().getRoot().setOnKeyReleased(new KeyReleaseEvent(spieler));
            }
            spieler.getSpielerfig().setX(900);
            spieler.getSpielerfig().setY(0);
            spieler.setStartPosX(1600);
            spieler.setStartPosY(0);
            spieler.setAufBoden(false);
            demo.setOpacity(0);
            bg.setX(-2);
            bg.setY(-2);
            canvas.setVisible(true);
            playerPane.setVisible(true);
            spieler.start();
            grounds = new Ground[6];
            grounds[0] = new Ground(canvas, "GrassRockHoz",1600,900);
            grounds[1] = new Ground(canvas, "GrassRockHoz",1200,900);
            grounds[2] = new Ground(canvas, "GrassRockHoz",800,900);
            grounds[3] = new Ground(canvas, "GrassRockHoz",400,900);
            grounds[4] = new Ground(canvas, "GrassRockHoz",0,900);
            grounds[5] = new Ground(canvas, "GrassRockHoz",0,700);
            spikes = new Spikes[2];
            spikes[0] = new Spikes(canvas,1200,800);
            spikes[1] = new Spikes(canvas,900,800);
            first = true;
        }
    }

    public void handleMainMenuAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleContinueAction(){
        QuickMenu.setVisible(false);
        spieler.setqMenu(false);
    }

    public void handleOpenSettingsAction(){
        MaMe.setVisible(false);
        Cont.setVisible(false);
        Settings.setVisible(false);
        Quit.setVisible(false);
        Ret.setVisible(true);
    }

    public void handleCloseSettingsAction(){
        MaMe.setVisible(true);
        Cont.setVisible(true);
        Settings.setVisible(true);
        Quit.setVisible(true);
        Ret.setVisible(false);
    }

    public void handleQuitAction(ActionEvent event){
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}