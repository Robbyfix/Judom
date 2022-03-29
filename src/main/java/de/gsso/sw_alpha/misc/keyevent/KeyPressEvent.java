package de.gsso.sw_alpha.misc.keyevent;

import de.gsso.sw_alpha.HelloController;
import de.gsso.sw_alpha.misc.Richtung;
import de.gsso.sw_alpha.objects.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
                case W:                                         //Check, ob Taste SPACE gedrückt ist
                    if (player.isAufBoden()) {                 //Check, ob Unten gleich NULL ist
                        player.setSprung(Richtung.SPRINGEN);    //sprung wird SPRINGEN gesetzt
                        player.setJumpbegin(Instant.now());
                    }
                    break;
                case A:                                         //Check, ob Taste A gedrückt ist
                    player.setLinks(Richtung.LINKS);            //links wird LINKS gesetzt
                    break;
                case D:                                         //Check, ob Taste D gedrückt ist
                    player.setRechts(Richtung.RECHTS);          //rechts wird RECHTS gesetzt
                    break;
                case ESCAPE:
                    player.getQuickMenu().setVisible(true);
                    player.setqMenu(true);
            }
        }
        else{
            if(event.getCode().equals(KeyCode.ESCAPE)){
                player.getQuickMenu().setVisible(false);
                player.setqMenu(false);
            }
        }
    }
}
