package de.gsso.sw_alpha.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Decoration extends ImageView {
    private final Pane canvas;

    public Decoration(Pane canvas, String name, double x, double y){
        super(new Image("Img/Decoration/"+name+".png"));
        this.canvas = canvas;
        this.setY(y);
        this.setX(x);
        this.canvas.getChildren().add(this);
    }
}
