package com.example.javafxhomedevices;

import com.example.javafxhomedevices.Apartment.ApartmentMain;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

//    fxml
    @FXML
    private VBox pnItems = null;
    @FXML
    private Label roomAmount;
    @FXML
    private Label socketAmount;
    @FXML
    private Label devAmount;
    @FXML
    private Label activeDevAmount;
    @FXML
    private Label ApartmentName;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnRooms;
    @FXML
    private Button btnSockets;
    @FXML
    private Button btnDevices;
    @FXML
    private Button turnOn;
    @FXML
    private Pane paneDevices;
    @FXML
    private Pane paneSockets;
    @FXML
    private Pane paneRooms;
    @FXML
    private Pane paneOverview;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final String apartmentName = "r6siege";

        ApartmentMain Apartment = new ApartmentMain(apartmentName);

        Node[] nodes = new Node[10];
//        for (int i = 0; i < nodes.length; i++) {
//            try {
//
//                final int j = i;
//                nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));
//
//                //give the items some effect
//
//                nodes[i].setOnMouseEntered(event -> {
//                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
//                });
//                nodes[i].setOnMouseExited(event -> {
//                    nodes[j].setStyle("-fx-background-color : #02030A");
//                });
//                pnItems.getChildren().add(nodes[i]);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnRooms) {
            paneRooms.setStyle("-fx-background-color : #1620A1");
            paneRooms.toFront();
        }
        if (actionEvent.getSource() == btnSockets) {
            paneSockets.setStyle("-fx-background-color : #53639F");
            paneSockets.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            paneOverview.setStyle("-fx-background-color : #02030A");
            paneOverview.toFront();
        }
        if(actionEvent.getSource()==btnDevices)
        {
            paneDevices.setStyle("-fx-background-color : #464F67");
            paneDevices.toFront();
        }
    }
}
