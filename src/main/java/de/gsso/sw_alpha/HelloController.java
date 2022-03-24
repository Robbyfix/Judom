package de.gsso.sw_alpha;

import de.gsso.sw_alpha.misc.keyevent.KeyPressEvent;
import de.gsso.sw_alpha.misc.keyevent.KeyReleaseEvent;
import de.gsso.sw_alpha.objects.Ground;
import de.gsso.sw_alpha.objects.Player;
import de.gsso.sw_alpha.objects.Spikes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HelloController {

    private Player spieler;
    private boolean first;
    private Ground[] grounds;
    private Spikes[] spikes;

    @FXML
    private Pane canvas;

    @FXML
    private Pane player;

    @FXML
    private Button demo;

    @FXML
    private Pane QuickMenu;

    @FXML
    private Pane background;

    @FXML
    public void handleDemoLevel() {
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
            demo.setOpacity(0);
            bg.setX(-2);
            bg.setY(-2);
            canvas.setVisible(true);
            player.setVisible(true);
            spieler.start();
            grounds = new Ground[7];
            grounds[0] = new Ground(canvas, "GrassRockHoz",1600,900);
            grounds[1] = new Ground(canvas, "GrassRockHoz",1200,900);
            grounds[2] = new Ground(canvas, "GrassRockHoz",800,900);
            grounds[3] = new Ground(canvas, "GrassRockHoz",400,900);
            grounds[4] = new Ground(canvas, "GrassRockHoz",0,900);
            grounds[5] = new Ground(canvas, "GrassRockHoz",0,700);
            grounds[6] = new Ground(canvas, "GrassRockHoz",1200,700);
            spikes = new Spikes[2];
            spikes[0] = new Spikes(canvas,600,800);
            spikes[1] = new Spikes(canvas,800,800);
            first = true;
        }
    }
}