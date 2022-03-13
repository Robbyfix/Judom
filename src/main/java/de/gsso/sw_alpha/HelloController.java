package de.gsso.sw_alpha;

import de.gsso.sw_alpha.misc.keyevent.KeyPressEvent;
import de.gsso.sw_alpha.misc.keyevent.KeyReleaseEvent;
import de.gsso.sw_alpha.objects.Player;
import de.gsso.sw_alpha.objects.ground.BlSteelHor;
import de.gsso.sw_alpha.objects.ground.BlSteelVer;
import de.gsso.sw_alpha.objects.ground.GrassRockHoz;
import de.gsso.sw_alpha.objects.ground.TestGrnd;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.animation.Animation;

public class HelloController {

    private Player spieler;
    private GrassRockHoz grh1;
    private GrassRockHoz grh2;
    private GrassRockHoz grh3;
    private GrassRockHoz grh4;
    private BlSteelHor bsh1;
    private BlSteelVer bsv1;
    private TestGrnd tg1;

    @FXML
    private Pane canvas;

    @FXML
    protected void handleStartAction() {
        if(spieler == null){                            //Check, ob spieler nicht null ist
            spieler = new Player(canvas);               //Neuer Spieler instanziiert
            canvas.getScene().getRoot().setOnKeyPressed(new KeyPressEvent(spieler));
            canvas.getScene().getRoot().setOnKeyReleased(new KeyReleaseEvent(spieler));
        }
        spieler.getSpielerfig().setX(900);
        spieler.getSpielerfig().setY(0);
        grh1 = new GrassRockHoz(canvas,1500,800);
        grh2 = new GrassRockHoz(canvas,1800,628);
        grh3 = new GrassRockHoz(canvas,1200,628);
        grh3 = new GrassRockHoz(canvas,1200,128);
        bsh1 = new BlSteelHor(canvas,1000, 600);
        bsv1 = new BlSteelVer(canvas, 500,800);
        tg1 = new TestGrnd(canvas,300,800);
        spieler.setAufBoden(false);
        spieler.start();
    }
}