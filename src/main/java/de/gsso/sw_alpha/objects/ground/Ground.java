package de.gsso.sw_alpha.objects.ground;

import de.gsso.sw_alpha.objects.Collision;
import javafx.scene.layout.Pane;

public class Ground extends Collision {

    protected Ground(Pane canvas, String path, double x, double y) {
        super(canvas, path, x, y);
    }
}
