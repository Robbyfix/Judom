package de.gsso.sw_alpha;

import de.gsso.sw_alpha.misc.keyevent.KeyPressEvent;
import de.gsso.sw_alpha.misc.keyevent.KeyReleaseEvent;
import de.gsso.sw_alpha.objects.Ground;
import de.gsso.sw_alpha.objects.Player;
import de.gsso.sw_alpha.objects.Room;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HelloController {

    private Player spieler;
    private Ground grh1;
    private Ground grh2;
    private Ground grh3;
    private Ground grh4;
    private boolean first;
    private Room rm;

    @FXML
    private Pane canvas;

    @FXML
    private Pane player;

    @FXML
    private Button start;

    @FXML
    private Pane QuickMenu;

    @FXML
    private Pane background;

    @FXML
    public void handleStartAction() {
        if(first==false) {
            ImageView bg = new ImageView(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/Background/GrassLand_Background_Guide.png")));
            background.getChildren().add(bg);
            if (spieler == null) {                            //Check, ob spieler nicht null ist
                spieler = new Player(canvas, player);               //Neuer Spieler instanziiert
                canvas.getScene().getRoot().setOnKeyPressed(new KeyPressEvent(spieler));
                canvas.getScene().getRoot().setOnKeyReleased(new KeyReleaseEvent(spieler));
            }
            spieler.getSpielerfig().setX(900);
            spieler.getSpielerfig().setY(0);
            spieler.setStartPosX(1600);
            spieler.setStartPosY(0);
            spieler.setAufBoden(false);
            start.setOpacity(0);
            bg.setX(-2);
            bg.setY(-2);
            canvas.setVisible(true);
            player.setVisible(true);
            spieler.start();
            first = true;
        }
        rm = new Room(canvas);
        rm.createRoom(0);
    }
}