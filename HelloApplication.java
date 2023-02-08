package com.example.jerry;

import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Group root;
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        root= new Group();
        GamePage page=new GamePage();
        root.getChildren().add(page.root);
        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("SnakeLadder Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}