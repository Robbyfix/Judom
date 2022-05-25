package de.gsso.sw_alpha.objects;

import de.gsso.sw_alpha.HelloController;
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
    public static Media sound = new Media(new File("src/main/resources/BGM/demo.mp3").toURI().toString());
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
    private ImageView DNum1 = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/UI/void.png")));
    private ImageView DNum2  = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/UI/void.png")));
    private ImageView DNum3  = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/UI/void.png")));
    private ImageView DNum4  = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/UI/void.png")));
    private boolean animStehenR = true;
    private boolean animStehenL;
    private boolean animLaufenR;
    private boolean animLaufenL;
    private boolean animSprungR;
    private boolean animSprungL;
    private boolean animFallenR;
    private boolean animFallenL;
    private boolean aufBoden;
    private boolean qMenu;
    private int count;
    private int kollisionCheck; //Geht alle canvas Objekte als Integer durch
    private int deaths;
    private int hitboxMov;
    private long lastCall = System.nanoTime();
    private double prevYpos;
    private double xparabel;
    private double startPosY;
    private double startPosX;
    private double goalPosX;
    private double geschwlimit = 5; //Max. Bewegungsgeschw.

    public Player(Pane canvas, Pane playerPane, Pane quickMenu) {
        this.canvas = canvas;
        this.playerPane = playerPane;
        this.quickMenu = quickMenu;
        this.canvas.getChildren().addAll(figkollup, figkolldown, figkollleft, figkollright);
        this.playerPane.getChildren().addAll(spielerfig, DNum1, DNum2, DNum3, DNum4);
        spielerfig.setX(1557);
        spielerfig.setY(515);
        this.kollisionCheck = this.canvas.getChildren().size() - 1;
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(new javafx.util.Duration(30)));
    }

    @Override
    public void handle(long now) {
        if (now > lastCall) {

            //Springen
            if (sprung == Richtung.SPRINGEN) {
                if(checkCollision(figkollup)){
                    sprung = Richtung.NULL;
                }
                figkolldown.setY(spielerfig.getY() + 168);
                springen();
            }

            //Fallen
            else if (!aufBoden) {
                if(checkCollision(figkolldown)){
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
                fallen("down", spielerfig);
                fallen("down",figkolldown,7);
                if(count %3==0){
                    figkolldown.setY(spielerfig.getY() + 168);
                }
            }

            if(aufBoden){
                xparabel = 3;
                if(animStehenL||animFallenL||animLaufenL||animSprungL) {
                    figkolldown.setX(spielerfig.getX() +hitboxMov);
                }
                else{
                    figkolldown.setX(spielerfig.getX() + 64 +hitboxMov);
                }
                fallen("down", figkolldown);
                if(checkCollision(figkolldown)){
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
            if (checkCollision(figkollright)) {
                //spielerfig.setX(((Ground)obj).getX()-165);
                //spielerfig.setX(spielerfig.getX()-6);
                canvas.setLayoutX(canvas.getLayoutX()+48);
                hitboxMov -= 48;
            }

            //Linke-Kollision
            if (checkCollision(figkollleft)) {
                //spielerfig.setX(spielerfig.getX()+(((Ground)obj).getImage().getWidth()/2));
                //spielerfig.setX(spielerfig.getX()+6);
                canvas.setLayoutX(canvas.getLayoutX()-48);
                hitboxMov += 48;
            }

            if(spielerfig.getY()>1234){
                spielerfig.setY(startPosY);
                canvas.setLayoutX(0);
                hitboxMov = 0;
                animStehenR = true;
                animStehenL = false;

                animSprungR = false;
                animSprungL = false;

                animLaufenR = false;
                animLaufenL = false;

                animFallenR = false;
                animFallenL = false;
                deaths++;
                spielerfig.setImage(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/FigStandingRight.gif")));
            }

            if((animStehenL||animFallenL||animLaufenL||animSprungL)&&!animLaufenR){
                setHitbox(true);
            }
            else {
                setHitbox(false);
                animFallenL = false;
                animLaufenL = false;
                animStehenL = false;
                animSprungL = false;
            }

            if(deaths<10){
                einstellig();
            }
            else if(deaths>=10&&deaths<100){
                zweistellig();
            }
            else if(deaths==100){
                dreistellig();
            }
            else{
                dreistellig();
                DNum4.setImage(new Image(Player.class.getClassLoader().getResourceAsStream("Img/UI/plus.png")));
                DNum4.setVisible(true);
                DNum4.setX(356);
                DNum4.setY(41);
            }
            if(count==60) {
                DebugAusgabe();
            }

            kollisionCheck--;

            count++;

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

        else if (jumpduration.getNano() > 200000000 && jumpduration.getNano() < 300000000) {
            geschwlimit = 4;
        }

        else if (jumpduration.getNano() > 300000000 && jumpduration.getNano() < 400000000) {
            geschwlimit = 3.5;
        }

        else if (jumpduration.getNano() > 400000000 && jumpduration.getNano() < 500000000) {
            geschwlimit = 3;
        }

        else if (jumpduration.getNano() > 500000000 && jumpduration.getNano() < 600000000) {
            geschwlimit = 2.5;
        }

        else if (jumpduration.getNano() > 600000000 && jumpduration.getNano() < 650000000) {
            geschwlimit = 2;
        }

        else if (jumpduration.getNano() > 650000000 && jumpduration.getNano() < 700000000) {
            geschwlimit = 1.5;
        }

        else if (jumpduration.getNano() > 750000000) {
            jumpduration = Duration.ZERO;
            jumpbegin = null;
            sprung = Richtung.NULL;
            prevYpos = spielerfig.getY();
        }
        aufBoden = false;
    }

    public void fallen(String direction, ImageView fig) {
        if (geschwlimit > 4.5) {
            geschwlimit = 4.5;
        }
        if (xparabel >= geschwlimit) {
            xparabel = geschwlimit;
        }
        switch (direction) {
            case "up" -> fig.setY(fig.getY() - (0.5 * 1.2 * Math.pow(xparabel, 2)));
            case "down" -> fig.setY(fig.getY() + (0.5 * 1.2 * Math.pow(xparabel, 2)));
        }
        xparabel++;
        geschwlimit += 0.1;
    }

    public void fallen(String direction, ImageView fig, double x) {
        switch (direction) {
            case "up" -> fig.setY(fig.getY() - (0.5 * 1.2 * Math.pow(x, 2)));
            case "down" -> fig.setY(fig.getY() + (0.5 * 1.2 * Math.pow(x, 2)));
        }
    }

    public void laufen(String direction){
        switch (direction) {
            case "links" -> {
                hitboxMov -= 6;
                canvas.setLayoutX((canvas.getLayoutX() + 6));
            }
            case "rechts" -> {
                hitboxMov += 6;
                canvas.setLayoutX((canvas.getLayoutX() - 6));
            }
        }
    }


    public boolean checkCollision(ImageView fig){
        if (kollisionCheck <= 0) {
            kollisionCheck = canvas.getChildren().size() - 1;
        }
        obj = canvas.getChildren().get(kollisionCheck);
        if (fig.getBoundsInParent().intersects(obj.getBoundsInParent())) {
            if (obj instanceof Ground) {
                return true;
            }
        }
        return false;
    }

    public void setHitbox(boolean Links){
        if(!Links) {
            figkollup.setY(spielerfig.getY() - 20);
            figkollup.setX(spielerfig.getX() + 54 +hitboxMov);

            if (!aufBoden) {
                figkolldown.setX(spielerfig.getX() + 64 +hitboxMov);
            }

            figkollleft.setY(spielerfig.getY() + 37);
            figkollleft.setX(spielerfig.getX() + 34 +hitboxMov);

            figkollright.setY(spielerfig.getY() + 37);
            figkollright.setX(spielerfig.getX() + 108 +hitboxMov);
        }
        else{
            figkollup.setY(spielerfig.getY() - 20);
            figkollup.setX(spielerfig.getX() +hitboxMov);

            if (!aufBoden) {
                figkolldown.setX(spielerfig.getX() +hitboxMov);
            }

            figkollleft.setY(spielerfig.getY() + 37);
            figkollleft.setX(spielerfig.getX()-40+hitboxMov);

            figkollright.setY(spielerfig.getY() + 37);
            figkollright.setX(spielerfig.getX() +108+hitboxMov);
        }
    }

    public void DebugAusgabe(){
        System.out.println("FPS: " + count);
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
        System.out.println(canvas.getLayoutX());
        //System.out.println(mediaPlayer.getVolume());
        count = 0;
    }

    public void einstellig(){
        DNum1.setVisible(true);
        DNum2.setVisible(false);
        DNum3.setVisible(false);
        DNum4.setVisible(false);

        setNums(false);

        DNum1.setY(41);
        DNum1.setX(200);
    }

    public void zweistellig(){
        DNum1.setVisible(true);
        DNum2.setVisible(true);
        DNum3.setVisible(false);
        DNum4.setVisible(false);

        setNums(true);

        DNum1.setX(200);
        DNum1.setY(41);

        DNum2.setX(255);
        DNum2.setY(41);
    }
    public void dreistellig(){
        DNum1.setImage(new Image(Player.class.getClassLoader().getResourceAsStream("Img/UI/one.png")));
        DNum1.setVisible(true);

        DNum2.setImage(new Image(Player.class.getClassLoader().getResourceAsStream("Img/UI/zero.png")));
        DNum2.setVisible(true);

        DNum3.setImage(new Image(Player.class.getClassLoader().getResourceAsStream("Img/UI/zero.png")));
        DNum3.setVisible(true);

        DNum4.setVisible(false);

        DNum1.setX(200);
        DNum1.setY(41);

        DNum2.setX(255);
        DNum2.setY(41);

        DNum3.setX(308);
        DNum3.setY(41);
    }

    public void setNums(boolean twoNums){
        for(int i = 0;i<10;i++){
            if(twoNums) {
                for (int j = 0; j < 10; j++) {
                    switch ((deaths / 10)) {
                        case 1 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/one.png")));
                        case 2 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/two.png")));
                        case 3 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/three.png")));
                        case 4 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/four.png")));
                        case 5 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/five.png")));
                        case 6 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/six.png")));
                        case 7 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/seven.png")));
                        case 8 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/eight.png")));
                        case 9 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/nine.png")));
                    }
                    switch (deaths - (i * 10)) {
                        case 0 -> DNum2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/zero.png")));
                        case 1 -> DNum2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/one.png")));
                        case 2 -> DNum2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/two.png")));
                        case 3 -> DNum2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/three.png")));
                        case 4 -> DNum2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/four.png")));
                        case 5 -> DNum2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/five.png")));
                        case 6 -> DNum2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/six.png")));
                        case 7 -> DNum2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/seven.png")));
                        case 8 -> DNum2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/eight.png")));
                        case 9 -> DNum2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/nine.png")));
                    }
                }
            }
            else{
                switch (deaths) {
                    case 0 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/zero.png")));
                    case 1 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/one.png")));
                    case 2 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/two.png")));
                    case 3 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/three.png")));
                    case 4 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/four.png")));
                    case 5 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/five.png")));
                    case 6 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/six.png")));
                    case 7 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/seven.png")));
                    case 8 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/eight.png")));
                    case 9 -> DNum1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/nine.png")));
                }
            }
        }
    }

    public void setLinks(Richtung richtung) {this.links = richtung;}

    public void setRechts(Richtung richtung) {this.rechts = richtung;}

    public void setSprung(Richtung sprung) {this.sprung = sprung;}

    public boolean isAufBoden() {return aufBoden;}

    public boolean isAnimLaufenL() {return animLaufenL;}

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

    public Richtung getSprung() {return sprung;}

    public void setAufBoden(boolean aufBoden) {this.aufBoden = aufBoden;}

    public void setJumpbegin(Instant jumpbegin) {this.jumpbegin = jumpbegin;}

    public void setGoalPosX(double goalPosX) {this.goalPosX = goalPosX;}

    public void setStartPosY(double startPosY) {this.startPosY = startPosY;}

    public void setStartPosX(double startPosX) {this.startPosX = startPosX;}

    public boolean isQuickMenu() {return qMenu;}

    public Pane getQuickMenu() {return quickMenu;}

    public MediaPlayer getMediaPlayer() {return mediaPlayer;}

    public void setqMenu(boolean qMenu) {this.qMenu = qMenu;}

}