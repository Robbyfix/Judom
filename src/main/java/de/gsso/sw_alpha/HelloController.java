package de.gsso.sw_alpha;

import de.gsso.sw_alpha.misc.keyevent.KeyPressEvent;
import de.gsso.sw_alpha.misc.keyevent.KeyReleaseEvent;
import de.gsso.sw_alpha.objects.Decoration;
import de.gsso.sw_alpha.objects.Ground;
import de.gsso.sw_alpha.objects.Player;
//import de.gsso.sw_alpha.resources.Img.Ground;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    private Player spieler;
    private Ground[] grounds;
    private Decoration[] decorations;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private boolean first;

    @FXML
    private Pane canvas = new Pane();

    @FXML
    private Pane playerPane;

    @FXML
    private Pane QuickMenu;

    @FXML
    private Pane decorationPane;

    @FXML
    private Button lvl;

    @FXML
    private Button lvl1;

    @FXML
    private Button lvl2;

    @FXML
    private Button lvl3;

    @FXML
    private Button lvl4;

    @FXML
    private Button lvl5;

    @FXML
    private Button lvl6;

    @FXML
    private Button MaMe;

    @FXML
    private Button Cont;

    @FXML
    private Button Settings;

    @FXML
    private Button Quit;

    @FXML
    private Button Ret;

    @FXML
    private Slider sliderVol;

    @FXML
    private ImageView Num1;

    @FXML
    private ImageView Num2;

    @FXML
    private ImageView Num3;

    public void handleFirstLevel() {
        if(!first) {
            MenuController.mediaPlayer.stop();
            if (spieler == null) {
                spieler = new Player(canvas, playerPane, QuickMenu, decorationPane);
                canvas.getScene().getRoot().setOnKeyPressed(new KeyPressEvent(spieler));
                canvas.getScene().getRoot().setOnKeyReleased(new KeyReleaseEvent(spieler));
            }
            spieler.setAufBoden(false);
            spieler.getSpielerfig().setY(0);
            spieler.setStartPosY(0);
            lvl1.setOpacity(0);
            lvl2.setOpacity(0);
            lvl3.setOpacity(0);
            lvl4.setOpacity(0);
            lvl5.setOpacity(0);
            lvl6.setOpacity(0);
            canvas.setVisible(true);
            playerPane.setVisible(true);
            spieler.start();

            grounds = new Ground[8];

            grounds[0] = new Ground(canvas, "5x8", 800, 800);
            grounds[1] = new Ground(canvas, "5x12", 300, 550);
            grounds[2] = new Ground(canvas, "3x10", -300, 600);
            grounds[3] = new Ground(canvas, "3x10", -700, 770);
            grounds[4] = new Ground(canvas, "8x9", -1400, 550);
            grounds[5] = new Ground(canvas, "5x12", -2000, 350);
            grounds[6] = new Ground(canvas, "8x12", -3000, 250);
            grounds[7] = new Ground(canvas, "5x12", -3850, 550);

            decorations = new Decoration[43];
            decorations[0] = new Decoration(decorationPane, "TrunkLeft", 1000, 755);
            decorations[1] = new Decoration(decorationPane, "TrunkCenter", 1048, 755);
            decorations[2] = new Decoration(decorationPane, "TrunkRight", 1096, 755);
            decorations[3] = new Decoration(decorationPane, "Bush", 850, 755);

            decorations[4] = new Decoration(decorationPane, "Tree", 300, 310);
            decorations[5] = new Decoration(decorationPane, "FlowerWhite", 490, 505);
            decorations[6] = new Decoration(decorationPane, "Stone2", -190, 552);
            decorations[7] = new Decoration(decorationPane, "Stone1", -255, 552);

            decorations[8] = new Decoration(decorationPane, "Lonetrunk", -600, 722);
            decorations[9] = new Decoration(decorationPane, "FlowerRed", -660, 722);

            decorations[10] = new Decoration(decorationPane, "ChoppedTree", -1350, 502);
            decorations[11] = new Decoration(decorationPane, "TrunkLeft", -1250, 502);
            decorations[12] = new Decoration(decorationPane, "TrunkCenter", -1202, 502);
            decorations[13] = new Decoration(decorationPane, "TrunkCenter2", -1154, 502);
            decorations[14] = new Decoration(decorationPane, "TrunkRight", -1106, 502);
            decorations[15] = new Decoration(decorationPane, "Bush", -935, 502);

            decorations[16] = new Decoration(decorationPane, "Stone3", -1950, 302);
            decorations[17] = new Decoration(decorationPane, "Stone2", -1800, 302);
            decorations[18] = new Decoration(decorationPane, "Grass2", -1840, 303);

            decorations[19] = new Decoration(decorationPane, "Tree", -3000, 11);
            decorations[20] = new Decoration(decorationPane, "Grass3", -2950, 203);
            decorations[21] = new Decoration(decorationPane, "Grass2", -2900, 203);
            decorations[22] = new Decoration(decorationPane, "Grass3", -2850, 203);
            decorations[23] = new Decoration(decorationPane, "Grass2", -2800, 203);
            decorations[24] = new Decoration(decorationPane, "Grass3", -2750, 203);
            decorations[25] = new Decoration(decorationPane, "Grass3", -2700, 203);
            decorations[30] = new Decoration(decorationPane, "Tree", -2680, 11); //dass der Baum hinter dem Grass ist
            decorations[26] = new Decoration(decorationPane, "Grass2", -2650, 203);
            decorations[27] = new Decoration(decorationPane, "Grass3", -2600, 203);
            decorations[28] = new Decoration(decorationPane, "Grass3", -2550, 203);
            decorations[29] = new Decoration(decorationPane, "Stone1", -2850, 203);
            decorations[31] = new Decoration(decorationPane, "Bush", -2680, 204);

            decorations[32] = new Decoration(decorationPane, "Grass3", -3800, 502);
            decorations[33] = new Decoration(decorationPane, "Grass3", -3750, 502);
            decorations[34] = new Decoration(decorationPane, "Grass3", -3700, 502);
            decorations[35] = new Decoration(decorationPane, "Grass3", -3650, 502);

            decorations[36] = new Decoration(decorationPane, "Grass3", -3600, 502);
            decorations[37] = new Decoration(decorationPane, "Bush", -3560, 502);
            decorations[38] = new Decoration(decorationPane, "FlowerRed", -3790, 502);
            decorations[39] = new Decoration(decorationPane, "FlowerWhite", -3725, 502);
            decorations[40] = new Decoration(decorationPane, "FlowerYellow", -3635, 502);
            decorations[41] = new Decoration(decorationPane, "Grass3", -3820, 502);
            decorations[42] = new Decoration(decorationPane, "GoalFlag", -3720, 400);


            first = true;
        }
    }

    public void handleSecondLevel() {
        if (!first) {
            MenuController.mediaPlayer.stop();
            if (spieler == null) {
                spieler = new Player(canvas, playerPane, QuickMenu, decorationPane);
                canvas.getScene().getRoot().setOnKeyPressed(new KeyPressEvent(spieler));
                canvas.getScene().getRoot().setOnKeyReleased(new KeyReleaseEvent(spieler));
            }
            spieler.setAufBoden(false);
            spieler.getSpielerfig().setY(0);
            spieler.setStartPosY(0);
            lvl1.setOpacity(0);
            lvl2.setOpacity(0);
            lvl3.setOpacity(0);
            lvl4.setOpacity(0);
            lvl5.setOpacity(0);
            lvl6.setOpacity(0);
            canvas.setVisible(true);
            playerPane.setVisible(true);
            spieler.start();

            grounds = new Ground[8];
            grounds[0] = new Ground(canvas, "8x12", 550, 300);
            grounds[1] = new Ground(canvas, "5x12", -100, 850);
            grounds[2] = new Ground(canvas, "3x10", -700, 700);
            grounds[3] = new Ground(canvas, "13x16", -1950, 550);
            grounds[4] = new Ground(canvas, "5x12", -2770, 470);
            grounds[5] = new Ground(canvas, "3x13", -3300, 600);
            grounds[6] = new Ground(canvas, "5x12", -3850, 550);

            decorations = new Decoration[43];
            decorations[0] = new Decoration(decorationPane, "Bush", 600, 255);
            decorations[1] = new Decoration(decorationPane, "FlowerWhite", 720, 252);
            decorations[2] = new Decoration(decorationPane, "Tree", 900, 61);
            decorations[3] = new Decoration(decorationPane, "LoneTrunk", 840, 255);

            decorations[4] = new Decoration(decorationPane, "ChoppedTree", -70, 803);
            decorations[5] = new Decoration(decorationPane, "Stone2", 130,805);

            decorations[6] = new Decoration(decorationPane, "Grass2",-700,652);
            decorations[7] = new Decoration(decorationPane, "Grass2",-650,652);
            decorations[8] = new Decoration(decorationPane, "Grass3",-600,652);
            decorations[9] = new Decoration(decorationPane, "Grass2",-550,652);
            decorations[10] = new Decoration(decorationPane, "Stone1",-640,652);


            decorations[28] = new Decoration(decorationPane, "Tree",-1920,307);
            decorations[29] = new Decoration(decorationPane, "Tree",-1720,307);
            decorations[30] = new Decoration(decorationPane, "Tree",-1520,307);
            decorations[31] = new Decoration(decorationPane, "Tree",-1320,307);
            decorations[11] = new Decoration(decorationPane, "Grass2",-1950,502);
            decorations[12] = new Decoration(decorationPane, "Grass2",-1900,502);
            decorations[13] = new Decoration(decorationPane, "Grass2",-1850,502);
            decorations[14] = new Decoration(decorationPane, "Grass3",-1800,502);
            decorations[15] = new Decoration(decorationPane, "Grass2",-1750,502);
            decorations[16] = new Decoration(decorationPane, "Grass2",-1700,502);
            decorations[17] = new Decoration(decorationPane, "Grass2",-1650,502);
            decorations[18] = new Decoration(decorationPane, "Grass3",-1600,502);
            decorations[19] = new Decoration(decorationPane, "Grass2",-1550,502);
            decorations[20] = new Decoration(decorationPane, "Grass2",-1500,502);
            decorations[21] = new Decoration(decorationPane, "Grass2",-1450,502);
            decorations[22] = new Decoration(decorationPane, "Grass2",-1400,502);
            decorations[23] = new Decoration(decorationPane, "Grass2",-1350,502);
            decorations[24] = new Decoration(decorationPane, "Grass3",-1300,502);
            decorations[25] = new Decoration(decorationPane, "Grass2",-1250,502);
            decorations[26] = new Decoration(decorationPane, "Grass2",-1200,502);
            decorations[27] = new Decoration(decorationPane, "Grass2",-1150,502);

            decorations[32] = new Decoration(decorationPane, "ChoppedTree", -2550, 422);
            decorations[33] = new Decoration(decorationPane, "TrunkLeft", -2740, 422);
            decorations[34] = new Decoration(decorationPane, "TrunkCenter", -2692, 422);
            decorations[35] = new Decoration(decorationPane, "TrunkCenter2", -2644, 422);
            decorations[36] = new Decoration(decorationPane, "TrunkRight", -2596, 422);

            decorations[37] = new Decoration(decorationPane, "LoneTrunk", -3200, 555);
            decorations[38] = new Decoration(decorationPane, "Stone2", -3260, 555);

            decorations[39] = new Decoration(decorationPane, "Tree", -3880, 311);
            decorations[40] = new Decoration(decorationPane, "GoalFlag", -3620, 406);
            decorations[41] = new Decoration(decorationPane, "FlowerRed", -3700, 502);


            first = true;
        }
    }
    public void handleThirdLevel () {
        if(!first) {
            MenuController.mediaPlayer.stop();
            if (spieler == null) {
                spieler = new Player(canvas, playerPane, QuickMenu, decorationPane);
                canvas.getScene().getRoot().setOnKeyPressed(new KeyPressEvent(spieler));
                canvas.getScene().getRoot().setOnKeyReleased(new KeyReleaseEvent(spieler));
            }
            spieler.setAufBoden(false);
            spieler.getSpielerfig().setY(0);
            spieler.setStartPosY(0);
            lvl1.setOpacity(0);
            lvl2.setOpacity(0);
            lvl3.setOpacity(0);
            lvl4.setOpacity(0);
            lvl5.setOpacity(0);
            lvl6.setOpacity(0);
            canvas.setVisible(true);
            playerPane.setVisible(true);
            spieler.start();

            grounds = new Ground[7];
            grounds[0] = new Ground(canvas,"5x12",800,300);
            grounds[1] = new Ground(canvas,"8x12",-200,400);
            grounds[2] = new Ground(canvas,"5x12",-800,300);
            grounds[3] = new Ground(canvas, "8x12", -1600, 200);
            grounds[4] = new Ground(canvas,"5x12",-2150,800);
            grounds[5] = new Ground(canvas,"5x12",-2800,550);
            grounds[6] = new Ground(canvas, "8x12", -3570, 350);

            decorations = new Decoration[22];
            decorations[0] = new Decoration(decorationPane, "Stone2", 870, 252);
            decorations[1] = new Decoration(decorationPane, "ChoppedTree", 980, 252);

            decorations[2] = new Decoration(decorationPane, "Stone3", -130, 353);
            decorations[3] = new Decoration(decorationPane, "FlowerWhite", 50, 353);
            decorations[4] = new Decoration(decorationPane, "Bush", 180, 353);

            decorations[5] = new Decoration(decorationPane, "Stone2", -730, 253);
            decorations[6] = new Decoration(decorationPane, "Bush", -570, 253);

            decorations[7] = new Decoration(decorationPane, "ChoppedTree", -1520, 153);
            decorations[8] = new Decoration(decorationPane, "TrunkLeft", -1400, 153);
            decorations[9] = new Decoration(decorationPane, "TrunkCenter", -1352, 153);
            decorations[10] = new Decoration(decorationPane, "TrunkCenter2", -1304, 153);
            decorations[11] = new Decoration(decorationPane, "TrunkRight", -1256, 153);

            decorations[12] = new Decoration(decorationPane, "LoneTrunk", -2100, 753);
            decorations[13] = new Decoration(decorationPane, "Stone2", -1925, 753);

            decorations[14] = new Decoration(decorationPane, "Bush", -2770, 503);
            decorations[15] = new Decoration(decorationPane, "ChoppedTree", -2570, 503);

            decorations[16] = new Decoration(decorationPane, "Tree", -3570, 111);
            decorations[21] = new Decoration(decorationPane, "GoalFlag", -3390, 207);
            decorations[17] = new Decoration(decorationPane, "TrunkLeft", -3290, 303);
            decorations[18] = new Decoration(decorationPane, "TrunkCenter", -3242, 303);
            decorations[19] = new Decoration(decorationPane, "TrunkRight", -3206, 303);
            decorations[20] = new Decoration(decorationPane, "ChoppedTree", -3150, 303);

            first = true;
        }
    }

    public void handleFourthLevel(){
        if(!first) {
            MenuController.mediaPlayer.stop();
            if (spieler == null) {
                spieler = new Player(canvas, playerPane, QuickMenu, decorationPane);
                canvas.getScene().getRoot().setOnKeyPressed(new KeyPressEvent(spieler));
                canvas.getScene().getRoot().setOnKeyReleased(new KeyReleaseEvent(spieler));
            }
            spieler.setAufBoden(false);
            spieler.getSpielerfig().setY(0);
            spieler.setStartPosY(0);
            lvl1.setOpacity(0);
            lvl2.setOpacity(0);
            lvl3.setOpacity(0);
            lvl4.setOpacity(0);
            lvl5.setOpacity(0);
            lvl6.setOpacity(0);
            canvas.setVisible(true);
            playerPane.setVisible(true);
            spieler.start();

            grounds = new Ground[7];
            grounds[0] = new Ground(canvas, "13x16", 250,300);
            grounds[1] = new Ground(canvas, "5x12", -650,850);
            grounds[2] = new Ground(canvas, "3x10", -1200,575);
            grounds[3] = new Ground(canvas, "3x13", -1575, 300);
            grounds[4] = new Ground(canvas, "8x9", -2850, 910);
            grounds[5] = new Ground(canvas, "5x8", -3450, 700);
            grounds[6] = new Ground(canvas, "3x13", -3920, 417);

            decorations = new Decoration[25];
            decorations[0] = new Decoration(decorationPane, "ChoppedTree", 300, 252);
            decorations[1] = new Decoration(decorationPane, "TrunkLeft", 448, 252);
            decorations[2] = new Decoration(decorationPane, "TrunkCenter", 496, 253);
            decorations[3] = new Decoration(decorationPane, "TrunkCenter2", 544, 253);
            decorations[4] = new Decoration(decorationPane, "TrunkRight", 592,253);
            decorations[5] = new Decoration(decorationPane, "Bush", 684,253);
            decorations[6] = new Decoration(decorationPane, "FlowerRed", 775,253);
            decorations[7] = new Decoration(decorationPane, "Tree", 850,61);

            decorations[8] = new Decoration(decorationPane, "FlowerWhite", -600,802);
            decorations[9] = new Decoration(decorationPane, "Bush", -475,802);

            decorations[10] = new Decoration(decorationPane, "Grass2", -1200,527);
            decorations[11] = new Decoration(decorationPane, "Grass2", -1150,527);
            decorations[12] = new Decoration(decorationPane, "Grass3", -1100,527);
            decorations[13] = new Decoration(decorationPane, "Grass2", -1050,527);

            decorations[14] = new Decoration(decorationPane, "Stone2", -1500,252);

            decorations[15] = new Decoration(decorationPane, "ChoppedTree", -2815,862);
            decorations[16] = new Decoration(decorationPane, "LoneTrunk", -2700,862);
            decorations[17] = new Decoration(decorationPane, "Bush", -2590,862);
            decorations[18] = new Decoration(decorationPane, "FlowerWhite", -2480,862);
            decorations[19] = new Decoration(decorationPane, "Stone1", -2395,862);

            decorations[21] = new Decoration(decorationPane, "LoneTrunk", -3400,653);
            decorations[22] = new Decoration(decorationPane, "Stone3", -3285,653);

            decorations[23] = new Decoration(decorationPane, "GoalFlag", -3900,274);
            decorations[24] = new Decoration(decorationPane, "Stone2", -3810,369);

            first = true;
        }
    }

    public void handleFifthLevel(){
        if(!first) {
            MenuController.mediaPlayer.stop();
            if (spieler == null) {
                spieler = new Player(canvas, playerPane, QuickMenu, decorationPane);
                canvas.getScene().getRoot().setOnKeyPressed(new KeyPressEvent(spieler));
                canvas.getScene().getRoot().setOnKeyReleased(new KeyReleaseEvent(spieler));
            }
            spieler.setAufBoden(false);
            spieler.getSpielerfig().setY(0);
            spieler.setStartPosY(0);
            lvl1.setOpacity(0);
            lvl2.setOpacity(0);
            lvl3.setOpacity(0);
            lvl4.setOpacity(0);
            lvl5.setOpacity(0);
            lvl6.setOpacity(0);
            canvas.setVisible(true);
            playerPane.setVisible(true);
            spieler.start();

            grounds = new Ground[8];
            grounds[0] = new Ground(canvas, "5x12", 730,450);
            grounds[1] = new Ground(canvas, "3x13", 300,600);
            grounds[2] = new Ground(canvas, "8x12", -600,500);
            grounds[3] = new Ground(canvas, "3x13", -1075, 650);
            grounds[4] = new Ground(canvas, "3x13", -1550, 850);
            grounds[5] = new Ground(canvas, "5x8", -2250, 750);
            grounds[6] = new Ground(canvas, "3x10", -2700, 625);
            grounds[7] = new Ground(canvas, "5x8", -3400, 690);

            decorations = new Decoration[20];
            decorations[0] = new Decoration(decorationPane, "ChoppedTree", 750, 402);
            decorations[1] = new Decoration(decorationPane, "Stone1", 857, 402);
            decorations[2] = new Decoration(decorationPane, "Stone2", 930, 402);

            decorations[3] = new Decoration(decorationPane, "TrunkLeft", 320, 552);
            decorations[4] = new Decoration(decorationPane, "TrunkRight", 368, 552);

            decorations[5] = new Decoration(decorationPane, "ChoppedTree", -580, 453);
            decorations[6] = new Decoration(decorationPane, "Bush", -455, 453);
            decorations[7] = new Decoration(decorationPane, "Stone3", -320, 453);
            decorations[8] = new Decoration(decorationPane, "FlowerYellow", -180, 453);

            decorations[9] = new Decoration(decorationPane, "Stone2", -1000, 603);

            decorations[10] = new Decoration(decorationPane, "LoneTrunk", -1500, 803);
            decorations[11] = new Decoration(decorationPane, "Stone1", -1425, 803);

            decorations[12] = new Decoration(decorationPane, "LoneTrunk", -2200, 703);
            decorations[13] = new Decoration(decorationPane, "FlowerRed", -2110, 703);
            decorations[14] = new Decoration(decorationPane, "ChoppedTree", -2015, 703);

            decorations[15] = new Decoration(decorationPane, "Bush", -2675, 577);
            decorations[16] = new Decoration(decorationPane, "Grass2", -2585, 577);

            decorations[17] = new Decoration(decorationPane, "GoalFlag", -3375, 547);
            decorations[18] = new Decoration(decorationPane, "FlowerWhite", -3285, 642);
            decorations[19] = new Decoration(decorationPane, "Stone3", -3210, 642);

            first = true;
        }
    }

    public void handleSixthLevel(){
        if(!first) {
            MenuController.mediaPlayer.stop();
            if (spieler == null) {
                spieler = new Player(canvas, playerPane, QuickMenu, decorationPane);
                canvas.getScene().getRoot().setOnKeyPressed(new KeyPressEvent(spieler));
                canvas.getScene().getRoot().setOnKeyReleased(new KeyReleaseEvent(spieler));
            }
            spieler.setAufBoden(false);
            spieler.getSpielerfig().setY(0);
            spieler.setStartPosY(0);
            lvl1.setOpacity(0);
            lvl2.setOpacity(0);
            lvl3.setOpacity(0);
            lvl4.setOpacity(0);
            lvl5.setOpacity(0);
            lvl6.setOpacity(0);
            canvas.setVisible(true);
            playerPane.setVisible(true);
            spieler.start();

            grounds = new Ground[9];
            grounds[0] = new Ground(canvas, "3x13", 850,700);
            grounds[1] = new Ground(canvas, "3x13", 350,550);
            grounds[2] = new Ground(canvas, "3x13", -300,375);
            grounds[3] = new Ground(canvas, "5x12", -1075, 280);
            grounds[4] = new Ground(canvas, "3x13", -1650, 485);
            grounds[5] = new Ground(canvas, "3x13", -2450, 640);
            grounds[6] = new Ground(canvas, "5x12", -3180, 425);
            grounds[7] = new Ground(canvas, "3x13", -3800, 200);
            grounds[8] = new Ground(canvas, "8x9", -5240, 950);

            decorations = new Decoration[25];
            decorations[0] = new Decoration(decorationPane, "LoneTrunk", 900, 652);
            decorations[1] = new Decoration(decorationPane, "Stone1", 985, 652);

            decorations[2] = new Decoration(decorationPane, "Stone2", 400, 502);

            decorations[3] = new Decoration(decorationPane, "ChoppedTree", -250, 328);

            decorations[4] = new Decoration(decorationPane, "TrunkCenter", -1000, 232);
            decorations[5] = new Decoration(decorationPane, "TrunkCenter2", -952, 232);
            decorations[6] = new Decoration(decorationPane, "Stone1", -857, 232);

            decorations[7] = new Decoration(decorationPane, "TrunkLeft", -1585, 437);
            decorations[8] = new Decoration(decorationPane, "FlowerWhite", -1500, 437);

            decorations[9] = new Decoration(decorationPane, "Bush", -2380, 592);



            decorations[10] = new Decoration(decorationPane, "Grass2", -3160, 377);
            decorations[11] = new Decoration(decorationPane, "Grass3", -3110, 377);
            decorations[12] = new Decoration(decorationPane, "Grass2", -3060, 377);
            decorations[13] = new Decoration(decorationPane, "Grass2", -3010, 377);
            decorations[14] = new Decoration(decorationPane, "Grass3", -2960, 377);
            decorations[15] = new Decoration(decorationPane, "Grass2", -2910, 377);
            decorations[16] = new Decoration(decorationPane, "FlowerYellow", -3095, 377);
            decorations[17] = new Decoration(decorationPane, "FlowerRed", -2970, 377);

            decorations[18] = new Decoration(decorationPane, "Stone1", -3760, 152);
            decorations[19] = new Decoration(decorationPane, "ChoppedTree", -3700, 152);

            decorations[20] = new Decoration(decorationPane, "Bush", -5200, 902);
            decorations[21] = new Decoration(decorationPane, "GoalFlag", -5110, 807);
            decorations[22] = new Decoration(decorationPane, "FlowerWhite", -5055, 902);
            decorations[23] = new Decoration(decorationPane, "Tree", -5000, 711);
            decorations[24] = new Decoration(decorationPane, "FlowerRed", -4815, 902);

            first = true;
        }
    }

    public void handleMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        spieler.getMediaPlayer().stop();
        MenuController.mediaPlayer.play();
    }

    public void handleChangeLevel(ActionEvent event) throws IOException{
        spieler.getMediaPlayer().stop();
        root = FXMLLoader.load(HelloApplication.class.getResource("Game.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        MenuController.mediaPlayer.play();
    }

    public void handleContinue(){
        QuickMenu.setVisible(false);
        spieler.setqMenu(false);
        spieler.start();
    }

    public void handleOpenSettings(){
        MaMe.setVisible(false);
        Cont.setVisible(false);
        Settings.setVisible(false);
        Quit.setVisible(false);
        Ret.setVisible(true);
        lvl.setVisible(false);

        sliderVol.setValue(spieler.getMediaPlayer().getVolume());
        sliderVol.setVisible(true);

        Num1.setVisible(true);

        Num2.setVisible(true);

        Num3.setVisible(true);

        if(sliderVol.getValue()<10) {
            einstellig();
        }
        else if(sliderVol.getValue()>10&&sliderVol.getValue()<100){
            zweistellig();
        }
        else{
            dreistellig();
        }
    }

    public void handleRefreshSettings(){
        if(sliderVol.getValue()<10){
            einstellig();
        }
        if(sliderVol.getValue()>10&&sliderVol.getValue()<100){
            zweistellig();
        }
        if(sliderVol.getValue()==100){
            dreistellig();
        }
        spieler.getMediaPlayer().setVolume(sliderVol.getValue());
        MenuController.mediaPlayer.setVolume(sliderVol.getValue());
    }

    public void handleCloseSettings(){
        MaMe.setVisible(true);
        Cont.setVisible(true);
        Settings.setVisible(true);
        Quit.setVisible(true);
        Ret.setVisible(false);
        sliderVol.setVisible(false);
        lvl.setVisible(true);

        Num1.setVisible(false);

        Num2.setVisible(false);

        Num3.setVisible(false);
    }

    public void einstellig(){
        Num1.setVisible(true);

        Num2.setVisible(false);

        Num3.setVisible(false);

        setNums(false);

        Num1.setY(500);
        Num1.setX(1280);
    }

    public void zweistellig(){
        Num1.setVisible(true);

        Num2.setVisible(true);

        Num3.setVisible(false);

        setNums(true);

        Num1.setX(1304);
        Num1.setY(500);

        Num2.setX(1256);
        Num2.setY(500);
    }
    public void dreistellig(){
        Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/zero.png")));
        Num1.setVisible(true);

        Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/zero.png")));
        Num2.setVisible(true);

        Num3.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/one.png")));
        Num3.setVisible(true);

        Num1.setX(1340);
        Num1.setY(500);

        Num2.setX(1292);
        Num2.setY(500);

        Num3.setX(1244);
        Num3.setY(500);
    }

    public void setNums(boolean twoNums){
        if(twoNums) {
            switch ((int) (sliderVol.getValue() / 10)) {
                case 1 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/one.png")));
                case 2 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/two.png")));
                case 3 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/three.png")));
                case 4 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/four.png")));
                case 5 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/five.png")));
                case 6 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/six.png")));
                case 7 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/seven.png")));
                case 8 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/eight.png")));
                case 9 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/nine.png")));
            }
            switch ((int) sliderVol.getValue() - (((int)sliderVol.getValue()/10) * 10)) {
                case 0 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/zero.png")));
                case 1 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/one.png")));
                case 2 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/two.png")));
                case 3 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/three.png")));
                case 4 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/four.png")));
                case 5 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/five.png")));
                case 6 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/six.png")));
                case 7 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/seven.png")));
                case 8 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/eight.png")));
                case 9 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/nine.png")));
            }
        }
        else{
            switch ((int) sliderVol.getValue()) {
                case 0 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/zero.png")));
                case 1 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/one.png")));
                case 2 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/two.png")));
                case 3 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/three.png")));
                case 4 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/four.png")));
                case 5 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/five.png")));
                case 6 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/six.png")));
                case 7 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/seven.png")));
                case 8 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/eight.png")));
                case 9 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/nine.png")));
            }
        }
    }

    public void handleQuit(ActionEvent event){
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}