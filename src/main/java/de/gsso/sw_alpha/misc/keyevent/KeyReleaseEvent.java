package de.gsso.sw_alpha.misc.keyevent;

import de.gsso.sw_alpha.misc.Richtung;
import de.gsso.sw_alpha.objects.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
public class KeyReleaseEvent implements EventHandler<KeyEvent> {


    private Player player;


    public KeyReleaseEvent(Player player) {
        this.player = player;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case W:                                         //Check, ob Taste SPACE nicht mehr gedrückt ist
                player.setSprung(Richtung.NULL);            //sprung wird NULL gesetzt
                //player.setGeschwlimit(2);
                break;
            case A:                                         //Check, ob Taste A nicht mehr gedrückt ist
                player.setLinks(Richtung.NULL);             //links wird NULL gesetzt
                break;
            case D:                                         //Check, ob Taste D nicht mehr gedrückt ist
                player.setRechts(Richtung.NULL);            //rechts wird NULL gesetzt
                break;
        }
    }
}