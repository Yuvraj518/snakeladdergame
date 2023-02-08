package com.example.jerry;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GamePage {
    AnchorPane root;
    GamePage() throws IOException{
        root= FXMLLoader.load(getClass().getResource("GamePage.fxml"));
    }
}
