package com.example.javafxhomedevices;

import com.example.javafxhomedevices.Apartment.ApartmentMain;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

//    fxml
    @FXML
    private Label devNameOverview;
    @FXML
    private TextField roomNameEnter;
    @FXML
    private Button addRoomBTN;
    @FXML
    private Label activeDevPowerOverview;
    @FXML
    private Pane paneContainer;
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
    private VBox roomsPane = null;
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
        paneOverview.setStyle("-fx-background-color : #02030A");
        paneOverview.toFront();

        final String apartmentName = "r6siege";

        ApartmentMain Apartment = new ApartmentMain(apartmentName);
        //TODO
        Apartment.startConfig();
        initOverviewPane(Apartment);
        initRoomPane(Apartment);
    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnRooms) {
            roomsPane.setVisible(true);
            paneOverview.setVisible(false);
            paneDevices.setVisible(false);
            paneSockets.setVisible(false);
        }
        if (actionEvent.getSource() == btnSockets) {
            roomsPane.setVisible(false);
            paneOverview.setVisible(false);
            paneDevices.setVisible(false);
            paneSockets.setVisible(true);
        }
        if (actionEvent.getSource() == btnOverview) {
            roomsPane.setVisible(false);
            paneOverview.setVisible(true);
            paneDevices.setVisible(false);
            paneSockets.setVisible(false);
        }
        if(actionEvent.getSource()==btnDevices)
        {
            roomsPane.setVisible(false);
            paneOverview.setVisible(false);
            paneDevices.setVisible(true);
            paneSockets.setVisible(false);
        }
    }

    public void initRoomPane(ApartmentMain Apartment){
        int roomAmountInt = Apartment.getAllRooms().size();
        for (int i = 0; i < roomAmountInt; i++) {
            try {
                final int j = i;
                HBox labelContainer = new HBox();
                labelContainer.setStyle("-fx-background-color: #02030A; -fx-background-radius: 5;");
                labelContainer.setAlignment(Pos.CENTER_LEFT);
                labelContainer.setPrefHeight(53.0);
                labelContainer.setPrefWidth(750.0);

                Label itemRoomName = new Label (
                        Apartment.getAllRooms().get(i).getRoomName()
                );
                itemRoomName.setTextFill(Paint.valueOf("#b7c3d7"));
                itemRoomName.setAlignment(Pos.CENTER);
                itemRoomName.setPrefWidth(200.0);
                labelContainer.getChildren().add(itemRoomName);
                roomsPane.getChildren().add(labelContainer);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        addRoomBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!roomNameEnter.getText().equals("")){
                    Apartment.addRoom(roomNameEnter.getText());
                    roomAmountOverview.setText(String.valueOf(Integer.parseInt(roomAmountOverview.getText()) + 1));

                    HBox labelContainer = new HBox();
                    labelContainer.setStyle("-fx-background-color: #02030A; -fx-background-radius: 5;");
                    labelContainer.setAlignment(Pos.CENTER_LEFT);
                    labelContainer.setPrefHeight(53.0);
                    labelContainer.setPrefWidth(750.0);

                    Label itemRoomName = new Label (
                            Apartment.getAllRooms().get(Apartment.getAllRooms().size()-1).getRoomName()
                    );
                    itemRoomName.setTextFill(Paint.valueOf("#b7c3d7"));
                    itemRoomName.setAlignment(Pos.CENTER);
                    itemRoomName.setPrefWidth(200.0);
                    labelContainer.getChildren().add(itemRoomName);
                    roomsPane.getChildren().add(labelContainer);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("some alert");
                    alert.setHeaderText("Information Alert");
                    String s ="Room name cannot be empty";
                    alert.setContentText(s);
                    alert.show();
                }
            }
        });
    }

    public void initOverviewPane(ApartmentMain Apartment){
        int roomAmountInt = Apartment.getAllRooms().size();
        int socketAmountInt = Apartment.getAllSockets().size();
        int deviceAmountInt = Apartment.getAllDevices().size();
        int enabledDeviceAmountInt = Apartment.enabledDeviceAmount();
        int activeDevPowerInt = Apartment.calculateTotalPower();
        roomAmountOverview.setText(Integer.toString(roomAmountInt));
        socketAmountOverview.setText(Integer.toString(socketAmountInt));
        devAmountOverview.setText(Integer.toString(deviceAmountInt));
        activeDevAmountOverview.setText(Integer.toString(enabledDeviceAmountInt));
        activeDevPowerOverview.setText(Integer.toString(activeDevPowerInt));
        for (int i = 0; i < deviceAmountInt; i++) {
            try {
                final int j = i;
                HBox labelContainer = new HBox();
                labelContainer.setStyle("-fx-background-color: #02030A; -fx-background-radius: 5;");
                labelContainer.setAlignment(Pos.CENTER_LEFT);
                labelContainer.setPrefHeight(53.0);
                labelContainer.setPrefWidth(750.0);

//                ImageView imageContainer = new ImageView();
//                Image imageItem = new Image("file:assets/icons8_GPS_Antenna_64px_1.png", false);
//                imageContainer.setImage(imageItem);
//                imageContainer.setFitHeight(31.0);
//                imageContainer.setFitWidth(25.0);
//                labelContainer.getChildren().add(imageContainer);

                Label itemDevName = new Label (
                        Apartment.getAllDevices().get(i).getDeviceName()
                );
                itemDevName.setTextFill(Paint.valueOf("#b7c3d7"));
                itemDevName.setAlignment(Pos.CENTER);
                itemDevName.setPrefWidth(200.0);
                labelContainer.getChildren().add(itemDevName);

                Label itemDevPower = new Label(
                        Integer.toString(Apartment.getAllDevices().get(i).getDevicePower())
                );
                itemDevPower.setTextFill(Paint.valueOf("#b7c3d7"));
                itemDevPower.setAlignment(Pos.CENTER);
                itemDevPower.setPrefWidth(100.0);
                labelContainer.getChildren().add(itemDevPower);

                Label itemDevRoom = new Label(
                        Apartment.getAllDevices().get(i).getRoom().getRoomName()
                );
                itemDevRoom.setTextFill(Paint.valueOf("#b7c3d7"));
                itemDevRoom.setAlignment(Pos.CENTER);
                itemDevRoom.setPrefWidth(150.0);
                labelContainer.getChildren().add(itemDevRoom);

                Label itemDevSocket = new Label(
                        Apartment.getAllDevices().get(i).getSocket().getSocketName()
                );
                itemDevSocket.setTextFill(Paint.valueOf("#b7c3d7"));
                itemDevSocket.setAlignment(Pos.CENTER);
                itemDevSocket.setPrefWidth(150.0);
                labelContainer.getChildren().add(itemDevSocket);

                Button itemDevStatus = new Button("-");
                itemDevStatus.setId(Integer.toString(i));
                itemDevStatus.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Apartment.getAllDevices().get(Integer.parseInt(itemDevStatus.getId())).switchPower();
                        if (Apartment.getAllDevices().get(Integer.parseInt(itemDevStatus.getId())).getIsOn()){
                            itemDevStatus.setText("active");
                            itemDevStatus.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.GREEN, 2.0, 2.0,0.0,0.0));
                        } else {
                            itemDevStatus.setText("inactive");
                            itemDevStatus.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.RED, 2.0, 2.0,0.0,0.0));
                        }
                        activeDevAmountOverview.setText(String.valueOf(Apartment.enabledDeviceAmount()));
                        activeDevPowerOverview.setText(String.valueOf(Apartment.calculateTotalPower()));
                    }
                });
                itemDevStatus.setPrefHeight(10.0);
                itemDevStatus.setAlignment(Pos.CENTER);
                itemDevStatus.setPrefWidth(80.0);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
