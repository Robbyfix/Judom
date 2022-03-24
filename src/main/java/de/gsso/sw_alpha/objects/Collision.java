package de.gsso.sw_alpha.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Collision extends ImageView {

    protected Collision(Image image, double x, double y){
        super(image);
        this.setX(x);
        this.setY(y);
    }

}
