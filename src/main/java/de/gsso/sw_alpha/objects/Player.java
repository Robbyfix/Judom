package de.gsso.sw_alpha.objects;

import de.gsso.sw_alpha.misc.Richtung;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

public class Player extends AnimationTimer {
    private Media sound = new Media(new File("src/main/resources/BGM/test.mp3").toURI().toString());
    private MediaPlayer mediaPlayer = new MediaPlayer(sound);
    private int fpscount; //Zählt die Framerate
    private double geschwlimit = 5; //Max. Bewegungsgeschw.
    private Instant jumpbegin = Instant.now(); //Anfangszeit des Sprungs
    private Duration jumpduration = Duration.ZERO; //Zählung der Zeit des Sprungs
    private static final long INTERVAL = 10;
    private long lastCall = System.nanoTime();
    private Pane canvas; //Pane auf dem alles dargestellt wird
    private Pane playerPane;
    private Richtung links = Richtung.NULL; //Richtungs-enum für die "Links"-Bewegung
    private Richtung rechts = Richtung.NULL; //Richtungs-enum für die "Rechts"-Bewegung
    private Richtung sprung = Richtung.NULL; //Richtungs-enum für den Sprung
    private boolean aufBoden; //Gibt an, ob der Boden berührt wird oder nicht
    private boolean stehen;
    private int kollisionCheck; //Geht alle canvas Objekte als Integer durch
    private boolean prevPos;
    private Node obj; //Aktuelles Objekt im Canvas
    private int i;
    private double prevYpos;
    private double prevXpos;
    private double xparabel;
    private double startPosX; //Start-Position, sollte man sterben/das Level betreten
    private double startPosY;
    private double goalPosX;  //Ziel-Position des Raumes, um den Raum zu wechseln
    private double goalPosY;
    private ImageView spielerfig = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/Fig.png")));//Bild der spielerfig wird einem ImageView zugeordnet
    private ImageView figkollup = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/figcollver.png"))); //Vertikale Hitbox-Oben
    private ImageView figkolldown = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/figcollver.png"))); //Vertikale Hitbox-Unten
    private ImageView figkollleft = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/figcollhoz.png"))); //Horizontale Hitbox-Links
    private ImageView figkollright = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/figcollhoz.png"))); //Horizontale Hitbox-Rechts

    public Player(Pane canvas, Pane playerPane) {
        this.canvas = canvas; //Pane canvas wird zum übergebenen canvas gesetzt
        this.playerPane = playerPane;
        canvas.getChildren().addAll(figkollup, figkolldown, figkollleft, figkollright);  //Fügt dem pane die spielerfig hinzu
        playerPane.getChildren().add(spielerfig);
        spielerfig.setX(1557);
        spielerfig.setY(515);
        this.kollisionCheck = this.canvas.getChildren().size() - 1;
        //mediaPlayer.play();
        //mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(new javafx.util.Duration(30)));
    }

    @Override
    public void handle(long now) {
        if (now > lastCall) {

            //Springen
            if (sprung == Richtung.SPRINGEN) {
                if(checkCollision(figkollup,"ground")){
                    sprung = Richtung.NULL;
                    //System.out.println("Kollision");
                }
                springen();
                //System.out.println(geschwlimit);
            }
            //Fallen
            else if (!aufBoden) {
                if(checkCollision(figkolldown, "ground")){
                    spielerfig.setY(((ImageView)obj).getY() - 154);
                    aufBoden = true;
                    stehen = true;
                    xparabel = 0;
                    //System.out.println("Kollision");
                }
                fallen("down");
                //System.out.println(geschwlimit);
            }

            //Rechts-Bewegung
            if (rechts == Richtung.RECHTS&&links!=Richtung.LINKS) {
                laufen("rechts");
                if (!checkCollision(figkolldown,"ground")) {
                    fallen("up",0);
                    if(fpscount==15||fpscount==30||fpscount==45||fpscount==60) {
                        aufBoden = false;
                    }
                }
            }

            //Links-Bewegung
            if (links == Richtung.LINKS&&rechts!=Richtung.RECHTS) {
                laufen("links");
                if (!checkCollision(figkolldown,"ground")) {
                    fallen("up",0);
                    if(fpscount==15||fpscount==30||fpscount==45||fpscount==60) {
                        aufBoden = false;
                    }
                }
            }

            if (checkCollision(figkollright,"ground")) {
                System.out.println("Kollision");
                //spielerfig.setX(((ImageView)obj).getX());
                spielerfig.setX(((Ground)obj).getX());
            }

            if (checkCollision(figkollleft,"ground")) {
                System.out.println("Kollision");
                spielerfig.setX(((Ground)obj).getX()+((Ground)obj).getImage().getWidth());
            }

            setHitbox();

            setFigImgState();

            if(fpscount==60) {
                DebugAusgabe();
            }

            kollisionCheck--;

            fpscount++;

            lastCall = now;
        }
    }

    public void springen() {
        if (xparabel >= geschwlimit) {
            xparabel = geschwlimit;
        }
        spielerfig.setY(spielerfig.getY()-(0.5*1.2*Math.pow(xparabel,2)));
        xparabel++;

        jumpduration = Duration.between(jumpbegin, Instant.now());

        if (jumpduration.getNano() < 200000000) {
            geschwlimit = 4.5;
        }

        if (jumpduration.getNano() > 200000000 && jumpduration.getNano() < 300000000) {
            geschwlimit = 4;
        }

        if (jumpduration.getNano() > 300000000 && jumpduration.getNano() < 400000000) {
            geschwlimit = 3.5;
        }

        if (jumpduration.getNano() > 400000000 && jumpduration.getNano() < 500000000) {
            geschwlimit = 3;
        }

        if (jumpduration.getNano() > 500000000 && jumpduration.getNano() < 600000000) {
            geschwlimit = 2.5;
        }

        if (jumpduration.getNano() > 600000000 && jumpduration.getNano() < 650000000) {
            geschwlimit = 2.5;
        }

        if (jumpduration.getNano() > 650000000 && jumpduration.getNano() < 700000000) {
            geschwlimit = 2;
        }

        if (jumpduration.getNano() > 750000000) {
            jumpduration = Duration.ZERO;
            jumpbegin = null;
            sprung = Richtung.NULL;
            prevYpos = spielerfig.getY();
        }
        //System.out.println(jumpduration.getNano());
        aufBoden = false;
    }

    public void fallen(String direction) {
        if (geschwlimit > 5) {
            geschwlimit = 5;
        }
        if (xparabel >= geschwlimit) {
            xparabel = geschwlimit;
        }
        switch (direction) {
            case "up":
            spielerfig.setY(spielerfig.getY() - (0.5 * 1.2 * Math.pow(xparabel, 2)));
            break;

            case "down":
            spielerfig.setY(spielerfig.getY() + (0.5 * 1.2 * Math.pow(xparabel, 2)));
            break;
        }
        xparabel++;
        geschwlimit += 0.1;
    }

    public void fallen(String direction, double x) {
        switch (direction) {
            case "up":
                spielerfig.setY(spielerfig.getY() - (0.5 * 1.2 * Math.pow(x, 2)));
                break;

            case "down":
                spielerfig.setY(spielerfig.getY() + (0.5 * 1.2 * Math.pow(x, 2)));
                break;
        }
    }

    public void laufen(String direction){
        switch(direction) {
            case "links":
            spielerfig.setX(spielerfig.getX() - 6);
            if (spielerfig.getX() <= 0) {
                spielerfig.setX(canvas.getWidth());
            }
            break;
            case "rechts":
                spielerfig.setX(spielerfig.getX() + 6);
                if (spielerfig.getX() > canvas.getWidth()) {
                    spielerfig.setX(0);
                }
        }
    }


    public boolean checkCollision(ImageView fig, String type){
        if (kollisionCheck <= 0) {
            kollisionCheck = canvas.getChildren().size() - 1;
        }
        obj = canvas.getChildren().get(kollisionCheck);
        if (fig.getBoundsInParent().intersects(obj.getBoundsInParent())) {
            switch(type) {
                case "ground":
                    if (obj instanceof Ground) {
                        return true;
                }
            }
        }

        return false;
    }

    public void setHitbox(){
        figkollup.setY(spielerfig.getY()-20);
        figkollup.setX(spielerfig.getX()+54);

        figkolldown.setY(spielerfig.getY()+154);
        figkolldown.setX(spielerfig.getX()+54);

        figkollleft.setY(spielerfig.getY()+37);
        figkollleft.setX(spielerfig.getX()+34);

        figkollright.setY(spielerfig.getY()+37);
        figkollright.setX(spielerfig.getX()+108);
    }

    public void setFigImgState(){
        if(rechts!=Richtung.RECHTS&&links!=Richtung.LINKS&&sprung!=Richtung.SPRINGEN&&aufBoden&&stehen){
            spielerfig.setImage(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/Fig_standing.gif")));
            stehen = false;
        }
    }

    public void DebugAusgabe(){
        System.out.println("FPS: " + fpscount);
        if (links == Richtung.LINKS) {
            System.out.println("Links");
        }
        if (rechts == Richtung.RECHTS) {
            System.out.println("Rechts");
        }
        if (!aufBoden) {
            System.out.println("Fallen");
        }
        if (sprung == Richtung.SPRINGEN) {
            System.out.println("Sprung");
        }
        fpscount = 0;
    }

    public void setLinks(Richtung richtung) {this.links = richtung;}

    public void setRechts(Richtung richtung) {this.rechts = richtung;}

    public void setSprung(Richtung sprung) {this.sprung = sprung;}

    public ImageView getSpielerfig() {return spielerfig;}    //Gibt Spielerfig zurück

    public boolean getAufBoden() {return aufBoden;}

    public void setPrevYpos(double prevYpos) {this.prevYpos = prevYpos;}

    public boolean getPrevPos() {return prevPos;}

    public void setPrevPos(boolean prevPos) {this.prevPos = prevPos;}

    public void setAufBoden(boolean aufBoden) {this.aufBoden = aufBoden;}

    public void setJumpbegin(Instant jumpbegin) {this.jumpbegin = jumpbegin;}

    public void setGoalPosX(double goalPosX) {this.goalPosX = goalPosX;}

    public void setGoalPosY(double goalPosY) {this.goalPosY = goalPosY;}

    public void setStartPosX(double startPosX) {this.startPosX = startPosX;}

    public void setStartPosY(double startPosY) {this.startPosY = startPosY;}
}