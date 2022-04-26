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
                if(player.isAnimSprungR()){
                    player.getSpielerfig().setImage(new Image(KeyReleaseEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigFallRight.gif")));
                    player.setAnimSprungR(false);
                    player.setAnimFallenR(true);
                }
                else if(player.isAnimSprungL()){
                    player.getSpielerfig().setImage(new Image(KeyReleaseEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigFallLeft.gif")));
                    player.setAnimSprungL(false);
                    player.setAnimFallenL(true);
                }
                break;
            case A:
                player.setLinks(Richtung.NULL);
                player.setAnimLaufenL(false);
                if(player.isAufBoden()) {
                    player.setAnimStehenL(true);
                    player.getSpielerfig().setImage(new Image(KeyReleaseEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigStandingLeft.gif")));
                }
                else{
                    player.setAnimFallenL(true);
                    player.getSpielerfig().setImage(new Image(KeyReleaseEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigFallLeft.gif")));
                }
                break;
            case D:
                player.setRechts(Richtung.NULL);
                player.setAnimLaufenR(false);
                if(player.isAufBoden()) {
                    player.setAnimStehenR(true);
                    player.getSpielerfig().setImage(new Image(KeyReleaseEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigStandingRight.gif")));
                }
                else{
                    player.setAnimFallenR(true);
                    player.getSpielerfig().setImage(new Image(KeyReleaseEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigFallRight.gif")));
                }
                break;
        }
    }
}