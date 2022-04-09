package com.example.javafxhomedevices;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientGUI extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clientGUI.fxml"));
        stage.setTitle("App");
        stage.setScene(new Scene(fxmlLoader.load(), 300,500));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}