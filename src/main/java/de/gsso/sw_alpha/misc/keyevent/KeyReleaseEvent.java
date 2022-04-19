package de.gsso.sw_alpha.misc.keyevent;

import de.gsso.sw_alpha.misc.Richtung;
import de.gsso.sw_alpha.objects.Player;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
public class KeyReleaseEvent implements EventHandler<KeyEvent> {


    private Player player;


    public KeyReleaseEvent(Player player) {
        this.player = player;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                player.setSprung(Richtung.NULL);
                if(player.isAnimSprungLinks()){
                    player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigFallLeft.gif")));
                    player.setAnimFallenLinks(true);
                }
                else if(player.isAnimSprungRechts()){
                    player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigFallRight.gif")));
                    player.setAnimFallenRechts(true);
                }
                player.setAnimSprungRechts(false);
                player.setAnimSprungLinks(false);
                break;
            case A:
                player.setLinks(Richtung.NULL);
                player.setAnimLinksLauf(false);
                player.setAnimRechtsLauf(false);
                if(player.isAufBoden()){
                    player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigStandingLeft.gif")));
                    player.setAnimStehenLinks(true);
                }
                break;
            case D:
                player.setRechts(Richtung.NULL);
                player.setAnimRechtsLauf(false);
                player.setAnimLinksLauf(false);
                if(player.isAufBoden()){
                    player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigStandingRight.gif")));
                    player.setAnimStehenRechts(true);
                }
                break;
        }
    }
}