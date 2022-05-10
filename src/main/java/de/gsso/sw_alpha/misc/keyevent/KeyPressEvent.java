package de.gsso.sw_alpha.misc.keyevent;

import de.gsso.sw_alpha.misc.Richtung;
import de.gsso.sw_alpha.objects.Ground;
import de.gsso.sw_alpha.objects.Player;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;

import java.security.Key;
import java.time.Instant;

public class KeyPressEvent implements EventHandler<KeyEvent> {

    private Player player;

    public KeyPressEvent(Player player) {
        this.player = player;
    }

    @Override
    public void handle(KeyEvent event) {
        if(!player.isQuickMenu()) {
            switch (event.getCode()) {
                case W:
                    if (player.isAufBoden()) {
                        player.setSprung(Richtung.SPRINGEN);
                        if (player.isAnimStehenL() || player.isAnimLaufenL()) {
                            player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigJumpLeft.gif")));
                            player.setAnimStehenL(false);
                            player.setAnimLaufenL(false);
                            player.setAnimSprungL(true);
                        } else if (player.isAnimStehenR() || player.isAnimLaufenR()) {
                            player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigJumpRight.gif")));
                            player.setAnimStehenR(false);
                            player.setAnimStehenL(false);
                            player.setAnimSprungR(true);
                        }
                        player.setJumpbegin(Instant.now());
                    }
                    break;
                case A:
                    player.setLinks(Richtung.LINKS);
                    if(player.isAnimStehenR()||player.isAnimStehenL()){
                        player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigWalkLeft.gif")));
                        player.setAnimStehenR(false);
                        player.setAnimStehenL(false);
                        player.setAnimLaufenL(true);
                    }
                    else if(!player.isAufBoden()){
                        if(player.getSprung()!=Richtung.SPRINGEN){
                            player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigFallLeft.gif")));
                            player.setAnimFallenL(true);
                            player.setAnimSprungL(false);
                        }
                        else {
                            player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigJumpLeft.gif")));
                            player.setAnimSprungL(true);
                            player.setAnimFallenL(false);
                        }
                        player.setAnimStehenL(false);
                        player.setAnimLaufenL(false);
                    }
                    break;
                case D:
                    player.setRechts(Richtung.RECHTS);
                    if(player.isAnimStehenR()||player.isAnimStehenL()){
                        player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigWalkRight.gif")));
                        player.setAnimStehenR(false);
                        player.setAnimStehenL(false);
                        player.setAnimLaufenR(true);
                    }
                    else if(!player.isAufBoden()){
                        if(player.getSprung()!=Richtung.SPRINGEN){
                            player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigFallRight.gif")));
                            player.setAnimFallenR(true);
                            player.setAnimSprungR(false);
                        }
                        else {
                            player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigJumpRight.gif")));
                            player.setAnimSprungR(true);
                            player.setAnimFallenR(false);
                        }
                        player.setAnimStehenR(false);
                        player.setAnimLaufenR(false);
                    }
                    break;
                case ESCAPE:
                    player.getQuickMenu().setVisible(true);
                    player.setqMenu(true);
                    player.stop();
                    break;
                //case O: //Debug

            }
        }
        else{
            if(event.getCode().equals(KeyCode.ESCAPE)){
                player.getQuickMenu().setVisible(false);
                player.setqMenu(false);
                player.start();
            }
        }
    }
}
