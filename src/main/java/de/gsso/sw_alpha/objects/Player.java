package de.gsso.sw_alpha.objects;

import de.gsso.sw_alpha.misc.Functions;
import de.gsso.sw_alpha.misc.Richtung;
import de.gsso.sw_alpha.objects.ground.Ground;
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
    private Duration deltaTime = Duration.ZERO; //Zählung der Zeit
    private Instant beginTime = Instant.now(); //Anfangszeit beim Aufrufen
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
    private int kollisionCheck; //Geht alle canvas Objekte als Integer durch
    private int kollisionChecklr; //Geht alle canvas Objekte als Integer durch für Links/Rechts
    private boolean prevPos;
    private Node obj; //Aktuelles Objekt im Canvas
    private Node objlr; //Aktuelles Objekt im Canvas für Links/Rechts
    private double prevYpos;
    private double prevXpos;
    private double xparabel;
    private double startPos; //Start-Position, sollte man sterben/das Level betreten
    private double goalPos;  //Ziel-Position des Raumes, um den Raum zu wechseln
    private ImageView spielerfig = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/Fig.png")));//Bild der spielerfig wird einem ImageView zugeordnet
    private ImageView figkollup = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/figcollver.png"))); //Vertikale Hitbox
    private ImageView figkolldown = new ImageView(new Image(Player.class.getClassLoader().getResourceAsStream("Img/Player/figcollver.png"))); //Vertikale Hitbox
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
        this.mediaPlayer.play();
    }

    public void setLinks(Richtung richtung) {
        this.links = richtung;
    }  //Setzt die Richtung von links

    public void setRechts(Richtung richtung) {
        this.rechts = richtung;
    }//Setzt die Richtung von rechts

    public void setSprung(Richtung sprung) {
        this.sprung = sprung;
    }    //Setzt die Richtung von sprung

    public ImageView getSpielerfig() {return spielerfig;}    //Gibt Spielerfig zurück

    public boolean getAufBoden() {return aufBoden;}

    public void setPrevYpos(double prevYpos) {this.prevYpos = prevYpos;}

    public boolean getPrevPos() {return prevPos;}

    public void setPrevPos(boolean prevPos) {this.prevPos = prevPos;}

    public void setAufBoden(boolean aufBoden) {this.aufBoden = aufBoden;}

    public void setJumpbegin(Instant jumpbegin) {this.jumpbegin = jumpbegin;}

    @Override
    public void handle(long now) {
        if (now > lastCall) {
            fpscount++;

            //Springen
            if (sprung == Richtung.SPRINGEN) {
                if (xparabel >= geschwlimit) {
                    xparabel = geschwlimit;
                }
                Functions.springen(xparabel, spielerfig);
                xparabel++;
                aufBoden = false;
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
                System.out.println(jumpduration.getNano());
                System.out.println(geschwlimit);
                if (kollisionCheck <= 0) {
                    kollisionCheck = canvas.getChildren().size() - 1;
                }
                obj = canvas.getChildren().get(kollisionCheck);
                if (figkollup.getBoundsInParent().intersects(obj.getBoundsInParent())) {
                    if (obj instanceof Ground) {
                        sprung = Richtung.NULL;
                        prevYpos = spielerfig.getY();
                        //System.out.println("Kollision");
                    }
                }
                kollisionCheck--;
            }
                //Fallen
            else if (!aufBoden) {
                if (kollisionCheck <= 0) {
                    kollisionCheck = canvas.getChildren().size() - 1;
                }
                obj = canvas.getChildren().get(kollisionCheck);
                if (figkolldown.getBoundsInParent().intersects(obj.getBoundsInParent())) {
                    if (obj instanceof Ground) {
                        spielerfig.setY(Functions.getKollisionNode(canvas, kollisionCheck).getY() - 154);
                        aufBoden = true;
                        prevPos = false;
                        xparabel = 0;
                        //System.out.println("Kollision");
                    }
                }
                kollisionCheck--;
                if (geschwlimit > 5) {
                    geschwlimit = 5;
                }
                if (xparabel >= geschwlimit) {
                    xparabel = geschwlimit;
                }
                if (kollisionCheck <= 0) { //kollisionCheck eine Art i wie bei for-Schleifen
                    kollisionCheck = canvas.getChildren().size() - 1;
                }
                obj = canvas.getChildren().get(kollisionCheck);
                if (figkolldown.getBoundsInParent().intersects(obj.getBoundsInParent())) { //obj = private Node Datentyp in Player, figkolldown = ImageView
                    if (obj instanceof Ground) { //spezifischer Typ-Check
                        spielerfig.setY(Functions.getKollisionNode(canvas, kollisionCheck).getY() - 154);
                        aufBoden = true;
                        prevPos = false;
                        xparabel = 0;
                        //System.out.println("Kollision");
                    }
                }
                kollisionCheck--;
                Functions.fallen(xparabel, false, spielerfig);
                xparabel++;
                geschwlimit += 0.1;
                System.out.println(geschwlimit);
            }

            prevXpos = spielerfig.getX();

            //Rechts-Bewegung
            if (rechts == Richtung.RECHTS&&links!=Richtung.LINKS) {
                spielerfig.setX(spielerfig.getX() + 8);
                if (spielerfig.getX() > canvas.getWidth()) {
                    spielerfig.setX(0);
                }
                if (!figkolldown.getBoundsInParent().intersects(obj.getBoundsInParent()) && sprung != Richtung.SPRINGEN) {
                    if(obj instanceof Ground) {
                        aufBoden = false;
                        Functions.fallen(0, true, spielerfig);
                    }
                }
            }

            //Links-Bewegung
            if (links == Richtung.LINKS&&rechts!=Richtung.RECHTS) {
                spielerfig.setX(spielerfig.getX() - 8);
                if (spielerfig.getX() <= 0) {
                    spielerfig.setX(canvas.getWidth());
                }
                if (!figkolldown.getBoundsInParent().intersects(obj.getBoundsInParent()) && sprung != Richtung.SPRINGEN) {
                    if(obj instanceof Ground) {
                        aufBoden = false;
                        Functions.fallen(0, true, spielerfig);
                    }
                }
            }

            if (kollisionChecklr <= 0) {
                kollisionChecklr = canvas.getChildren().size() - 1;
            }
            objlr = canvas.getChildren().get(kollisionChecklr);
            if (figkollright.getBoundsInParent().intersects(objlr.getBoundsInParent())) {
                if(objlr instanceof Ground) {
                    System.out.println("Kollision");
                    spielerfig.setX(spielerfig.getX()-8);
                }
            }
            if (figkollleft.getBoundsInParent().intersects(objlr.getBoundsInParent())) {
                if(objlr instanceof Ground) {
                    System.out.println("Kollision");
                    spielerfig.setX(spielerfig.getX()+8);
                }
            }
            kollisionChecklr--;

            figkollup.setY(spielerfig.getY()-20);
            figkollup.setX(spielerfig.getX()+54);

            figkolldown.setY(spielerfig.getY()+154);
            figkolldown.setX(spielerfig.getX()+54);

            figkollleft.setY(spielerfig.getY()+25);
            figkollleft.setX(spielerfig.getX()+34);

            figkollright.setY(spielerfig.getY()+25);
            figkollright.setX(spielerfig.getX()+108);

            //Ausgabe verschiedener Werte im Sekundentakt
            deltaTime = Duration.between(beginTime, Instant.now());
            if (deltaTime.getSeconds() == 1) {
                System.out.println("FPS: " + fpscount);
                fpscount = 0;
                beginTime = Instant.now();
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
            }
            this.mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.play());
            lastCall = now;
        }//handle ende
    }
}