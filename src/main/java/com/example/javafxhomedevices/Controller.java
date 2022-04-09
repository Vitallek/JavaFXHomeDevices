package com.example.javafxhomedevices;

import com.example.javafxhomedevices.Apartment.ApartmentMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

//    fxml
    @FXML
    private Label devNameOverview;
    @FXML
    private Label devPowerOverview;
    @FXML
    private Label devRoomOverview;
    @FXML
    private Label devSocketOverview;
    @FXML
    private Button devStatusOverview;
    @FXML
    private VBox pnItems = null;
    @FXML
    private Label roomAmountOverview;
    @FXML
    private Label socketAmountOverview;
    @FXML
    private Label devAmountOverview;
    @FXML
    private Label activeDevAmountOverview;
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
        //TODO
        Apartment.startConfig();

        int roomAmountInt = Apartment.getAllRooms().size();
        int socketAmountInt = Apartment.getAllSockets().size();
        int deviceAmountInt = Apartment.getAllDevices().size();
        int enabledDeviceAmountInt = Apartment.enabledDeviceAmount();
        roomAmountOverview.setText(Integer.toString(roomAmountInt));
        socketAmountOverview.setText(Integer.toString(socketAmountInt));
        devAmountOverview.setText(Integer.toString(deviceAmountInt));
        activeDevAmountOverview.setText(Integer.toString(enabledDeviceAmountInt));
        Node[] nodes = new Node[deviceAmountInt];
        for (int i = 0; i < deviceAmountInt; i++) {
            try {
                final int j = i;
                HBox labelContainer = new HBox();
                labelContainer.setStyle("-fx-background-color: #02030A; -fx-background-radius: 5; -fx-background-insets: 0;");
                labelContainer.setAlignment(Pos.CENTER_LEFT);
                labelContainer.setPrefHeight(53.0);
                labelContainer.setPrefWidth(695.0);
                labelContainer.setSpacing(80.0);
                labelContainer.setOpaqueInsets(new Insets(0,0,0,10));

//                ImageView imageContainer = new ImageView();
//                Image imageItem = new Image("file:assets/icons8_GPS_Antenna_64px_1.png");
//                imageContainer.setImage(imageItem);
//                labelContainer.getChildren().add(imageContainer);

                Label itemDevName = new Label (
                    Apartment.getAllDevices().get(i).getDeviceName()
                );
                itemDevName.setTextFill(Paint.valueOf("#b7c3d7"));
                labelContainer.getChildren().add(itemDevName);

                Label itemDevPower = new Label(
                    Integer.toString(Apartment.getAllDevices().get(i).getDevicePower())
                );
                itemDevPower.setTextFill(Paint.valueOf("#b7c3d7"));
                labelContainer.getChildren().add(itemDevPower);

                Label itemDevRoom = new Label(
                    Apartment.getAllDevices().get(i).getRoom().getRoomName()
                );
                itemDevRoom.setTextFill(Paint.valueOf("#b7c3d7"));
                labelContainer.getChildren().add(itemDevRoom);

                Label itemDevSocket = new Label(
                    Apartment.getAllDevices().get(i).getSocket().getSocketName()
                );
                itemDevSocket.setTextFill(Paint.valueOf("#b7c3d7"));
                labelContainer.getChildren().add(itemDevSocket);

                Button itemDevStatus = new Button("-");
                if (Apartment.getAllDevices().get(i).getIsOn()){
                    itemDevStatus.setText("active");
                    itemDevStatus.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.GREEN, 2.0, 2.0,0.0,0.0));

                } else {
                    itemDevStatus.setText("inactive");
                    itemDevStatus.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.RED, 2.0, 2.0,0.0,0.0));

                }
                itemDevStatus.setTextFill(Paint.valueOf("#b7c3d7"));
                labelContainer.getChildren().add(itemDevStatus);

                pnItems.getChildren().add(labelContainer);
                nodes[i] = labelContainer;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
