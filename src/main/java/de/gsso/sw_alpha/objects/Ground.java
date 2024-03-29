package de.gsso.sw_alpha.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Ground extends ImageView {
    private final Pane canvas;

    public Ground(Pane canvas, String name, double x, double y) {
        super(new Image("Img/Ground/"+name+".png"));
        this.setX(x);
        this.setY(y);
        this.canvas = canvas;
        this.canvas.getChildren().add(this);
    }
}
