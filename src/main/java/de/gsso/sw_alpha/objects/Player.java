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
    public static Media sound = new Media(new File("src/main/resources/BGM/test.mp3").toURI().toString());
    public static MediaPlayer mediaPlayer = new MediaPlayer(sound);
    private Instant jumpbegin = Instant.now();
    private Duration jumpduration = Duration.ZERO;
    private Pane canvas;
    private Pane playerPane;
    private Pane quickMenu;
    private Richtung links = Richtung.NULL;
    private Richtung rechts = Richtung.NULL;
    private Richtung sprung = Richtung.NULL;
    private Node obj; //Aktuelles Objekt im Canvas
    private ImageView spielerfig = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/FigStandingRight.gif")));
    private final ImageView figkollup = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/figcollver.png")));
    private final ImageView figkolldown = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/figcollver.png")));
    private final ImageView figkollleft = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/figcollhoz.png")));
    private final ImageView figkollright = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/figcollhoz.png")));
    private boolean animStehenR = true;
    private boolean animStehenL;
    private boolean animLaufenR;
    private boolean animLaufenL;
    private boolean animSprungR;
    private boolean animSprungL;
    private boolean animFallenR;
    private boolean animFallenL;
    private boolean aufBoden;
    private boolean prevPos;
    private boolean qMenu;
    private int fpscount;
    private int kollisionCheck; //Geht alle canvas Objekte als Integer durch
    private long lastCall = System.nanoTime();
    private double prevYpos;
    private double prevXpos;
    private double xparabel;
    private double startPosX;
    private double startPosY;
    private double goalPosX;
    private double goalPosY;
    private double geschwlimit = 5; //Max. Bewegungsgeschw.

    public Player(Pane canvas, Pane playerPane, Pane quickMenu) {
        this.canvas = canvas;
        this.playerPane = playerPane;
        this.quickMenu = quickMenu;
        canvas.getChildren().addAll(figkollup, figkolldown, figkollleft, figkollright);
        playerPane.getChildren().add(spielerfig);
        spielerfig.setX(1557);
        spielerfig.setY(515);
        this.kollisionCheck = this.canvas.getChildren().size() - 1;
        //mediaPlayer.setVolume(50);
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
                }
                figkolldown.setY(spielerfig.getY() + 168);
                springen();
            }

            //Fallen
            else if (!aufBoden) {
                if(checkCollision(figkolldown, "ground")){
                    spielerfig.setY(((ImageView)obj).getY() - 164);
                    aufBoden = true;
                    if(animFallenL){
                        spielerfig.setImage(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/FigStandingLeft.gif")));
                        animFallenL = false;
                        animStehenL = true;
                    }
                    else if(animFallenR){
                        spielerfig.setImage(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/FigStandingRight.gif")));
                        animFallenR = false;
                        animStehenR = true;
                    }
                    xparabel = 0;
                    prevYpos = figkolldown.getY();
                    figkolldown.setY(spielerfig.getY() + 168);
                }
                if(checkCollision(figkolldown, "spikes")){
                    spielerfig.setX(startPosX);
                    spielerfig.setY(startPosY);
                    figkolldown.setY(spielerfig.getY() + 168);
                }
                fallen("down", spielerfig);
                fallen("down",figkolldown,7);
                if(fpscount%3==0){
                    figkolldown.setY(spielerfig.getY() + 168);
                }
            }

            if(aufBoden){
                xparabel = 3;
                if(animStehenL||animFallenL||animLaufenL||animSprungL) {
                    figkolldown.setX(spielerfig.getX());
                }
                else{
                    figkolldown.setX(spielerfig.getX() + 64);
                }
                prevXpos = spielerfig.getY();
                fallen("down", figkolldown);
                if(checkCollision(figkolldown,"ground")){
                    figkolldown.setY(spielerfig.getY() + 110);
                    prevYpos = figkolldown.getY();
                }
                else if(figkolldown.getY()>=prevYpos+55){
                    fallen("up",spielerfig);
                    aufBoden = false;
                }
            }

            //Rechts-Bewegung
            if (rechts == Richtung.RECHTS&&links!=Richtung.LINKS) {
                laufen("rechts");
            }

            //Links-Bewegung
            if (links == Richtung.LINKS&&rechts!=Richtung.RECHTS) {
                laufen("links");
            }

            //Rechte-Kollision
            if (checkCollision(figkollright,"ground")) {
                spielerfig.setX(((Collision)obj).getX()-165);
            }

            //Linke-Kollision
            if (checkCollision(figkollleft,"ground")) {
                spielerfig.setX(((Collision)obj).getX()+((Collision)obj).getImage().getWidth());
            }

            if(spielerfig.getY()>1234){
                spielerfig.setX(startPosX);
                spielerfig.setY(startPosY);
            }

            if(animStehenL||animFallenL||animLaufenL||animSprungL){
                setHitbox(true);
            }
            else {
                setHitbox(false);
            }

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
        aufBoden = false;
    }

    public void fallen(String direction, ImageView fig) {
        if (geschwlimit > 5) {
            geschwlimit = 5;
        }
        if (xparabel >= geschwlimit) {
            xparabel = geschwlimit;
        }
        switch (direction) {
            case "up":
            fig.setY(fig.getY() - (0.5 * 1.2 * Math.pow(xparabel, 2)));
            break;

            case "down":
            fig.setY(fig.getY() + (0.5 * 1.2 * Math.pow(xparabel, 2)));
            break;
        }
        xparabel++;
        geschwlimit += 0.1;
    }

    public void fallen(String direction, ImageView fig, double x) {
        switch (direction) {
            case "up":
                fig.setY(fig.getY() - (0.5 * 1.2 * Math.pow(x, 2)));
                break;

            case "down":
                fig.setY(fig.getY() + (0.5 * 1.2 * Math.pow(x, 2)));
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
                case "spikes":
                    if(obj instanceof Spikes){
                        return true;
                    }
            }
        }

        return false;
    }

    public void setHitbox(boolean Links){
        if(!Links) {
            figkollup.setY(spielerfig.getY() - 20);
            figkollup.setX(spielerfig.getX() + 54);

            if (!aufBoden) {
                figkolldown.setX(spielerfig.getX() + 64);
            }

            figkollleft.setY(spielerfig.getY() + 37);
            figkollleft.setX(spielerfig.getX() + 34);

            figkollright.setY(spielerfig.getY() + 37);
            figkollright.setX(spielerfig.getX() + 108);
        }
        else{
            figkollup.setY(spielerfig.getY() - 20);
            figkollup.setX(spielerfig.getX());

            if (!aufBoden) {
                figkolldown.setX(spielerfig.getX());
            }

            figkollleft.setY(spielerfig.getY() + 37);
            figkollleft.setX(spielerfig.getX());

            figkollright.setY(spielerfig.getY() + 37);
            figkollright.setX(spielerfig.getX());
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
        System.out.println(mediaPlayer.getVolume());
        fpscount = 0;
    }

    public void setLinks(Richtung richtung) {this.links = richtung;}

    public void setRechts(Richtung richtung) {this.rechts = richtung;}

    public void setSprung(Richtung sprung) {this.sprung = sprung;}

    public boolean isAufBoden() {return aufBoden;}

    public boolean isAnimFallenL() {return animFallenL;}

    public boolean isAnimFallenR() {return animFallenR;}

    public boolean isAnimLaufenL() {return animLaufenL;}

    public boolean isAnimLaufenR() {return animLaufenR;}

    public boolean isAnimSprungL() {return animSprungL;}

    public boolean isAnimSprungR() {return animSprungR;}

    public boolean isAnimStehenL() {return animStehenL;}

    public boolean isAnimStehenR() {return animStehenR;}

    public void setAnimFallenL(boolean animFallenL) {this.animFallenL = animFallenL;}

    public void setAnimFallenR(boolean animFallenR) {this.animFallenR = animFallenR;}

    public void setAnimLaufenL(boolean animLaufenL) {this.animLaufenL = animLaufenL;}

    public void setAnimLaufenR(boolean animLaufenR) {this.animLaufenR = animLaufenR;}

    public void setAnimSprungL(boolean animSprungL) {this.animSprungL = animSprungL;}

    public void setAnimSprungR(boolean animSprungR) {this.animSprungR = animSprungR;}

    public void setAnimStehenL(boolean animStehenL) {this.animStehenL = animStehenL;}

    public void setAnimStehenR(boolean animStehenR) {this.animStehenR = animStehenR;}

    public ImageView getSpielerfig() {return spielerfig;}

    public Richtung getLinks() {return links;}

    public Richtung getRechts() {return rechts;}

    public Richtung getSprung() {return sprung;}

    public void setPrevYpos(double prevYpos) {this.prevYpos = prevYpos;}

    public void setPrevPos(boolean prevPos) {this.prevPos = prevPos;}

    public void setAufBoden(boolean aufBoden) {this.aufBoden = aufBoden;}

    public void setJumpbegin(Instant jumpbegin) {this.jumpbegin = jumpbegin;}

    public void setGoalPosX(double goalPosX) {this.goalPosX = goalPosX;}

    public void setGoalPosY(double goalPosY) {this.goalPosY = goalPosY;}

    public void setStartPosX(double startPosX) {this.startPosX = startPosX;}

    public void setStartPosY(double startPosY) {this.startPosY = startPosY;}

    public boolean isQuickMenu() {return qMenu;}

    public Pane getQuickMenu() {return quickMenu;}

    public MediaPlayer getMediaPlayer() {return mediaPlayer;}

    public void setqMenu(boolean qMenu) {this.qMenu = qMenu;}
}