package de.gsso.sw_alpha.misc.keyevent;

import de.gsso.sw_alpha.misc.Richtung;
import de.gsso.sw_alpha.objects.Player;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;

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
                        if(player.isAnimStehenLinks()||player.isAnimLinksLauf()){
                            player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigJumpLeft.gif")));
                            player.setAnimSprungLinks(true);
                        }
                        else if(player.isAnimStehenRechts()||player.isAnimRechtsLauf()){
                            player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigJumpRight.gif")));
                            player.setAnimSprungRechts(true);
                        }
                        player.setAnimStehenLinks(false);
                        player.setAnimStehenRechts(false);
                        player.setAnimLinksLauf(false);
                        player.setAnimRechtsLauf(false);
                        player.setSprung(Richtung.SPRINGEN);
                        player.setJumpbegin(Instant.now());
                    }
                    break;
                case A:
                    player.setLinks(Richtung.LINKS);
                    player.setAnimStehenLinks(false);
                    player.setAnimStehenRechts(false);
                    player.setAnimFallenRechts(false);
                    player.setAnimFallenLinks(false);
                    player.setAnimLinksLauf(false);
                    player.setAnimRechtsLauf(false);
                    if(!player.isAnimLinksLauf()){
                        if(player.isAufBoden()) {
                            player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigWalkLeft.gif")));
                        }
                        else if(player.getSprung()==Richtung.SPRINGEN){
                            player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigJumpLeft.gif")));
                        }
                        else if(!player.isAufBoden()&&player.getSprung()!=Richtung.SPRINGEN){
                            player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigFallLeft.gif")));
                        }
                        player.setAnimLinksLauf(true);
                    }

                    break;
                case D:
                    player.setRechts(Richtung.RECHTS);
                    player.setAnimStehenRechts(false);
                    player.setAnimStehenLinks(false);
                    player.setAnimFallenRechts(false);
                    player.setAnimFallenLinks(false);
                    player.setAnimLinksLauf(false);
                    player.setAnimRechtsLauf(false);
                    if(!player.isAnimRechtsLauf()){
                        if(player.isAufBoden()) {
                            player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigWalkRight.gif")));
                        }
                        else if(player.getSprung()==Richtung.SPRINGEN){
                            player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigJumpRight.gif")));
                        }
                        else if(!player.isAufBoden()&&player.getSprung()!=Richtung.SPRINGEN){
                            player.getSpielerfig().setImage(new Image(KeyPressEvent.class.getClassLoader().getResourceAsStream("Img/Player/FigFallRight.gif")));
                        }
                        player.setAnimRechtsLauf(true);
                    }
                    break;
                case ESCAPE:
                    player.getQuickMenu().setVisible(true);
                    player.setqMenu(true);
                    player.stop();
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
