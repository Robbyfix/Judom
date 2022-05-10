package de.gsso.sw_alpha.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Decoration extends ImageView {
    private final Pane decorationPane;

    public Decoration(Pane decorationPane, String name, double x, double y){
        super(new Image("Img/Decoration/"+name+".png"));
        this.decorationPane = decorationPane;
        this.setY(y);
        this.setX(x);
        this.decorationPane.getChildren().add(this);
    }
}
