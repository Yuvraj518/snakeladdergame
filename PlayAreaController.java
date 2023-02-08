package com.example.jerry;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import static javafx.scene.input.KeyCode.X;

public class PlayAreaController {
    @FXML
    Text number;
    @FXML
    ImageView player1,player2;

    @FXML
    Text changeturn;
    int turn=1;

    HashMap<Pair<Double,Double>, Pair<Double,Double>> snakeLCoordinate;
    @FXML
    public void roll(MouseEvent event) throws IOException {
        getSnakeLCoordinates();
        Random random = new Random();
        int rolling= random.nextInt(6)+1;
        number.setText(""+rolling);
        //System.out.println(rolling);
        if(turn==1){
            Pair<Double,Double> moveCoordinates =movement(player1.getTranslateX(),player1.getTranslateY(),rolling);
            player1.setTranslateX(moveCoordinates.getKey());
            player1.setTranslateY(moveCoordinates.getValue());
            if(snakeLCoordinate.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue()))){
                player1.setTranslateX(snakeLCoordinate.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                player1.setTranslateY(snakeLCoordinate.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            checkWin(player1.getTranslateX(),player1.getTranslateY());
        }
        else{
            Pair<Double,Double> moveCoordinates =movement(player2.getTranslateX(),player2.getTranslateY(),rolling);
            player2.setTranslateX(moveCoordinates.getKey());
            player2.setTranslateY(moveCoordinates.getValue());
            if(snakeLCoordinate.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue()))){
                player2.setTranslateX(snakeLCoordinate.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                player2.setTranslateY(snakeLCoordinate.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            checkWin(player2.getTranslateX(),player2.getTranslateY());
        }
        if(turn==1){
            turn=2;
            changeturn.setText("Player 2's turn");
        }
        else{
            turn=1;
            changeturn.setText("Player 1's turn");
        }

    }
    Pair<Double,Double> movement(double X, double Y, int rolling){
        double moveX = X;
        double moveY = Y;
        if (Y % 100==0) {
            moveX +=rolling*50;
        }
        else{
            moveX-=rolling*50;
        }
        //moveX +=rolling*50;
        if(moveX>450){
            moveX=moveX-500;
            moveX=500-moveX-50;
            moveY-=50;
        }
        if(moveX<0){
            if(moveY==-450){return new Pair<>(X,Y);}
            moveX=-moveX-50;
            moveY-=50;
        }
        return new Pair<>(moveX,moveY);
    }

    void checkWin(Double X, Double Y) throws IOException {
        if(X==0 && Y==-450){
            Alert winAlert= new Alert(Alert.AlertType.INFORMATION);
            winAlert.setHeaderText("Hurray !!");
            winAlert.setContentText("Player"+turn+" has Won");
            winAlert.showAndWait();
            AnchorPane start= FXMLLoader.load(getClass().getResource("GamePage.fxml"));
            HelloApplication.root.getChildren().setAll(start);
        }
    }

    void getSnakeLCoordinates(){
        snakeLCoordinate= new HashMap<>();
        snakeLCoordinate.put(new Pair<>(0.0,0.0),new Pair<>(150.0,-150.0));
        snakeLCoordinate.put(new Pair<>(150.0,0.0),new Pair<>(300.0,-50.0));
        snakeLCoordinate.put(new Pair<>(400.0,0.0),new Pair<>(450.0,-150.0));
        snakeLCoordinate.put(new Pair<>(350.0,-100.0),new Pair<>(150.0,-400.0));
        snakeLCoordinate.put(new Pair<>(0.0,-100.0),new Pair<>(50.0,-200.0));
        snakeLCoordinate.put(new Pair<>(450.0,-250.0),new Pair<>(300.0,-300.0));
        snakeLCoordinate.put(new Pair<>(450.0,-350.0),new Pair<>(450.0,-450.0));
        snakeLCoordinate.put(new Pair<>(0.0,-350.0),new Pair<>(0.0,-450.0));

        snakeLCoordinate.put(new Pair<>(150.0,-50.0),new Pair<>(300.0,0.0));
        snakeLCoordinate.put(new Pair<>(300.0,-250.0),new Pair<>(300.0,-150.0));
        snakeLCoordinate.put(new Pair<>(50.0,-300.0),new Pair<>(50.0,-50.0));
        snakeLCoordinate.put(new Pair<>(150.0,-300.0),new Pair<>(0.0,-250.0));
        snakeLCoordinate.put(new Pair<>(300.0,-400.0),new Pair<>(150.0,-100.0));
        snakeLCoordinate.put(new Pair<>(100.0,-450.0),new Pair<>(50.0,-350.0));
        snakeLCoordinate.put(new Pair<>(250.0,-450.0),new Pair<>(250.0,-350.0));
        snakeLCoordinate.put(new Pair<>(350.0,-450.0),new Pair<>(350.0,-350.0));

    }
}
