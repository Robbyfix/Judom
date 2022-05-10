package de.gsso.sw_alpha;

import de.gsso.sw_alpha.objects.Player;
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
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button start;

    @FXML
    private Button quit;

    @FXML
    private Button settings;

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

    public void handleStartGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("Game.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleOpenSettings(){
        start.setVisible(false);
        quit.setVisible(false);
        settings.setVisible(false);
        Ret.setVisible(true);
        sliderVol.setValue(Player.mediaPlayer.getVolume());
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
        Player.mediaPlayer.setVolume(sliderVol.getValue());
    }

    public void handleCloseSettings(){
        start.setVisible(true);
        quit.setVisible(true);
        settings.setVisible(true);
        Ret.setVisible(false);
        sliderVol.setVisible(false);
        Num1.setVisible(false);
        Num2.setVisible(false);
        Num3.setVisible(false);
    }

    public void einstellig(){
        Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/void.png")));
        Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/void.png")));
        Num3.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/void.png")));
        setNums(false);
        Num1.setY(200);
        Num1.setX(1480);
    }

    public void zweistellig(){
        Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/void.png")));
        Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/void.png")));
        Num3.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/void.png")));
        setNums(true);
        Num1.setX(1504);
        Num1.setY(200);
        Num2.setX(1456);
        Num2.setY(200);
    }
    public void dreistellig(){
        Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/zero.png")));
        Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/zero.png")));
        Num3.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/one.png")));
        Num1.setX(1540);
        Num1.setY(200);
        Num2.setX(1492);
        Num2.setY(200);
        Num3.setX(1444);
        Num3.setY(200);
    }

    public void setNums(boolean twoNums){
        for(int i = 0;i<10;i++){
            if(twoNums) {
                for (int j = 0; j < 10; j++) {
                    if ((int) (sliderVol.getValue() / 10) == i) {
                        switch (i) {
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
                    }
                    if ((int) sliderVol.getValue() - (i * 10) == j) {
                        switch (j) {
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
            }
            else{
                if((int) sliderVol.getValue()==i){
                    switch (i) {
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
        }
    }

    public void handleQuitGame(ActionEvent event) throws IOException{
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
