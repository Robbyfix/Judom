package de.gsso.sw_alpha.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Collision extends ImageView{
    private final Pane canvas;

    protected Collision(Pane canvas, String path, double x, double y){
        super(new Image(path));
        this.canvas = canvas;
        this.setY(y);
        this.setX(x);
        this.canvas.getChildren().add(this);
    }


}
