package de.gsso.sw_alpha.misc;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Functions {

    public static ImageView getKollisionNode(Pane canvas, int i) {
        if (i <= 0) {
            i = canvas.getChildren().size() - 1;
        }
        return (ImageView) canvas.getChildren().get(i);
    }
    
    public static void springen(double x, ImageView... obj) {
        if (obj != null) {
            for (ImageView imageView : obj) {
                //Fig nach oben bewegen
                //imageView.setY(imageView.getY() - schieben - geschw);   //Die Y-Pos wird geändert
                imageView.setY(imageView.getY()-(0.5*1.2*Math.pow(x,2)));
            }
        }

    }

    public static void fallen(double x, boolean umkehr, ImageView... obj) {
        if(obj!=null){
            for (ImageView imageView : obj) {

                if(umkehr){
                    imageView.setY(imageView.getY() - (0.5 * 1.2 * Math.pow(x, 2)));
                }
                else {
                    //Fig nach unten bewegen //1/2*9,81*x²
                    imageView.setY(imageView.getY() + (0.5 * 1.2 * Math.pow(x, 2)));
                }
            }
        }

    }
}
