package de.gsso.sw_alpha.objects;

import javafx.scene.layout.Pane;

public class Room {
    private Pane canvas;
    private Ground[] grounds = new Ground[5];
    private int x;

    public Room(Pane canvas){
        this.canvas = canvas;
    }

    public void createRoom(int ID){
        switch(ID){
            case 0:
                grounds[0] = new Ground(canvas, "GrassRockHoz",1600,900);
                grounds[1] = new Ground(canvas, "GrassRockHoz",1200,900);
                grounds[2] = new Ground(canvas, "GrassRockHoz",800,900);
                grounds[3] = new Ground(canvas, "GrassRockHoz",400,900);
                grounds[4] = new Ground(canvas, "GrassRockHoz",0,900);
                canvas.getChildren().addAll(grounds[0],grounds[1],grounds[2],grounds[3],grounds[4]);
        }
    }
}
