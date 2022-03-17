package de.gsso.sw_alpha.objects;

import javafx.scene.layout.Pane;

public class Ground extends Collision {

    public Ground(Pane canvas, String name, double x, double y) {
        super(canvas, "Img/Ground/"+name+".png", x, y);
    }
}
