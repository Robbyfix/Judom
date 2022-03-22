package de.gsso.sw_alpha.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Spikes extends ImageView {
    private final Pane canvas;

    public Spikes(Pane canvas, double x, double y) {
        super(new Image("Img/Demo/spikes.png"));
        this.canvas = canvas;
        this.setX(x);
        this.setY(y);
    }
}
