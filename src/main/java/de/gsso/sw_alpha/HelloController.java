package de.gsso.sw_alpha;

import de.gsso.sw_alpha.misc.keyevent.KeyPressEvent;
import de.gsso.sw_alpha.misc.keyevent.KeyReleaseEvent;
import de.gsso.sw_alpha.objects.Ground;
import de.gsso.sw_alpha.objects.Player;
import de.gsso.sw_alpha.objects.Spikes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    private Player spieler;
    private boolean first;
    private Ground[] grounds;
    private Spikes[] spikes;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Pane canvas = new Pane();

    @FXML
    private Pane playerPane;

    @FXML
    private Button demo;

    @FXML
    private Pane QuickMenu;

    @FXML
    private Pane background;

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

    public HelloController(){

    }

    @FXML
    public void handleDemoLevel() {
        if(first==false) {
            ImageView bg = new ImageView(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/Background/GrassLand_Background_Guide.png")));
            background.getChildren().add(bg);
            if (spieler == null) {                            //Check, ob spieler nicht null ist
                spieler = new Player(canvas, playerPane, QuickMenu);               //Neuer Spieler instanziiert
                canvas.getScene().getRoot().setOnKeyPressed(new KeyPressEvent(spieler));
                canvas.getScene().getRoot().setOnKeyReleased(new KeyReleaseEvent(spieler));
            }
            spieler.getSpielerfig().setX(900);
            spieler.getSpielerfig().setY(0);
            spieler.setStartPosX(1600);
            spieler.setStartPosY(0);
            spieler.setAufBoden(false);
            demo.setOpacity(0);
            bg.setX(-2);
            bg.setY(-2);
            canvas.setVisible(true);
            playerPane.setVisible(true);
            spieler.start();
            grounds = new Ground[6];
            grounds[0] = new Ground(canvas, "GrassRockHoz",1600,900);
            grounds[1] = new Ground(canvas, "GrassRockHoz",1200,900);
            grounds[2] = new Ground(canvas, "GrassRockHoz",800,900);
            grounds[3] = new Ground(canvas, "GrassRockHoz",400,900);
            grounds[4] = new Ground(canvas, "GrassRockHoz",0,900);
            grounds[5] = new Ground(canvas, "GrassRockHoz",0,700);
            spikes = new Spikes[2];
            spikes[0] = new Spikes(canvas,1200,800);
            spikes[1] = new Spikes(canvas,900,800);
            first = true;
        }
    }

    public void handleMainMenuAction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleContinueAction(){
        QuickMenu.setVisible(false);
        spieler.setqMenu(false);
    }

    public void handleOpenSettingsAction(){
        MaMe.setVisible(false);
        Cont.setVisible(false);
        Settings.setVisible(false);
        Quit.setVisible(false);
        Ret.setVisible(true);
        sliderVol.setValue(spieler.getMediaPlayer().getVolume());
        sliderVol.setVisible(true);
        Num1.setVisible(true);
        Num2.setVisible(true);
        Num3.setVisible(true);
    }

    public void handleRefreshSettings(){
        if(sliderVol.getValue()<10){
            Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/void.png")));
            Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/void.png")));
            Num3.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/void.png")));
            for(int i=0;i<10;i++){
                if((int) sliderVol.getValue()==i){
                    switch(i){
                        case 0: Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/zero.png")));
                            break;

                        case 1: Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/one.png")));
                            break;

                        case 2: Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/two.png")));
                            break;

                        case 3: Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/three.png")));
                            break;

                        case 4: Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/four.png")));
                            break;

                        case 5: Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/five.png")));
                            break;

                        case 6: Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/six.png")));
                            break;

                        case 7: Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/seven.png")));
                            break;

                        case 8: Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/eight.png")));
                            break;

                        case 9: Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/nine.png")));
                            break;
                    }
                }
            }
            Num1.setY(200);
            Num1.setX(1480);
        }
        if(sliderVol.getValue()>10&&sliderVol.getValue()<100){
            Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/void.png")));
            Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/void.png")));
            Num3.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/void.png")));
            for(int i = 0;i<10;i++){
                for(int j=0;j<10;j++) {
                    if((int) (sliderVol.getValue()/10)==i) {
                        switch (i) {
                            case 1:
                                Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/one.png")));
                                break;

                            case 2:
                                Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/two.png")));
                                break;

                            case 3:
                                Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/three.png")));
                                break;

                            case 4:
                                Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/four.png")));
                                break;

                            case 5:
                                Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/five.png")));
                                break;

                            case 6:
                                Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/six.png")));
                                break;

                            case 7:
                                Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/seven.png")));
                                break;

                            case 8:
                                Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/eight.png")));
                                break;

                            case 9:
                                Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/nine.png")));
                                break;
                        }
                    }
                    if ((int) sliderVol.getValue()-(i*10)==j) {
                        switch (j) {
                            case 0:
                                Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/zero.png")));
                                break;

                            case 1:
                                Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/one.png")));
                                break;

                            case 2:
                                Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/two.png")));
                                break;

                            case 3:
                                Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/three.png")));
                                break;

                            case 4:
                                Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/four.png")));
                                break;

                            case 5:
                                Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/five.png")));
                                break;

                            case 6:
                                Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/six.png")));
                                break;

                            case 7:
                                Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/seven.png")));
                                break;

                            case 8:
                                Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/eight.png")));
                                break;

                            case 9:
                                Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/nine.png")));
                                break;
                        }
                    }
                }
            }
            Num1.setX(1504);
            Num1.setY(200);
            Num2.setX(1456);
            Num2.setY(200);
        }
        if(sliderVol.getValue()==100){
            Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/zero.png")));
            Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/zero.png")));
            Num3.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("UI/one.png")));
            Num1.setX(1540);
            Num1.setY(200);
            Num2.setX(1492);
            Num2.setY(200);
            Num3.setX(1444);
            Num3.setY(200);
        }
        spieler.getMediaPlayer().setVolume(sliderVol.getValue());
    }

    public void handleCloseSettingsAction(){
        MaMe.setVisible(true);
        Cont.setVisible(true);
        Settings.setVisible(true);
        Quit.setVisible(true);
        Ret.setVisible(false);
        sliderVol.setVisible(false);
        Num1.setVisible(false);
        Num2.setVisible(false);
        Num3.setVisible(false);
    }

    public void handleQuitAction(ActionEvent event){
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}