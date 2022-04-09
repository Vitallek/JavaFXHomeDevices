package com.example.javafxhomedevices;

import com.example.javafxhomedevices.Apartment.Apartment;
import com.example.javafxhomedevices.Apartment.ApartmentMain;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

//    fxml
    @FXML
    private Label devNameOverview;
    @FXML
    private TextField roomNameEnter;
    @FXML
    private TextField roomForSocketEnter;
    @FXML
    private TextField socketNameEnter;
    @FXML
    private Button addRoomBTN;
    @FXML
    private Button addSocketBTN;
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
    private VBox roomsItemPane = null;
    @FXML
    private VBox socketItemPane = null;
    @FXML
    private VBox deviceItemPane = null;
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

    final private String apartmentName = "r6siege";
    final private ApartmentMain Apartment = new ApartmentMain(apartmentName);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneOverview.setVisible(true);
        paneRooms.setVisible(false);
        paneDevices.setVisible(false);
        paneSockets.setVisible(false);

        //TODO
        Apartment.startConfig();
        initOverviewPane(Apartment);
        initRoomPane(Apartment);
        initSocketPane(Apartment);
        initDevicePane(Apartment);
    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnRooms) {
            roomsItemPane.getChildren().clear();
            initRoomPane(Apartment);
            paneRooms.setVisible(true);
            paneOverview.setVisible(false);
            paneDevices.setVisible(false);
            paneSockets.setVisible(false);
        }
        if (actionEvent.getSource() == btnSockets) {
            socketItemPane.getChildren().clear();
            initSocketPane(Apartment);
            paneRooms.setVisible(false);
            paneOverview.setVisible(false);
            paneDevices.setVisible(false);
            paneSockets.setVisible(true);
        }
        if (actionEvent.getSource() == btnOverview) {
            pnItems.getChildren().clear();
            initOverviewPane(Apartment);
            paneRooms.setVisible(false);
            paneOverview.setVisible(true);
            paneDevices.setVisible(false);
            paneSockets.setVisible(false);
        }
        if(actionEvent.getSource()==btnDevices) {
            deviceItemPane.getChildren().clear();
            initDevicePane(Apartment);
            paneRooms.setVisible(false);
            paneOverview.setVisible(false);
            paneDevices.setVisible(true);
            paneSockets.setVisible(false);
        }
    }


    public void initDevicePane(ApartmentMain Apartment){
        int deviceAmountInt = Apartment.getAllDevices().size();
        for (int i = 0; i < deviceAmountInt; i++) {
            try {
                HBox labelContainer = new HBox();
                labelContainer.setStyle("-fx-background-color: #02030A; -fx-background-radius: 5;");
                labelContainer.setAlignment(Pos.CENTER_LEFT);
                labelContainer.setPrefHeight(53.0);
                labelContainer.setPrefWidth(450.0);

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
                itemDevPower.setPrefWidth(180.0);
                labelContainer.getChildren().add(itemDevPower);

                Label itemDevRoom = new Label(
                        Apartment.getAllDevices().get(i).getRoom().getRoomName()
                );
                itemDevRoom.setTextFill(Paint.valueOf("#b7c3d7"));
                itemDevRoom.setAlignment(Pos.CENTER);
                itemDevRoom.setPrefWidth(200.0);
                labelContainer.getChildren().add(itemDevRoom);

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
                itemDevStatus.setPrefWidth(180.0);

                if (Apartment.getAllDevices().get(i).getIsOn()){
                    itemDevStatus.setText("active");
                    itemDevStatus.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.GREEN, 2.0, 2.0,0.0,0.0));

                } else {
                    itemDevStatus.setText("inactive");
                    itemDevStatus.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.RED, 2.0, 2.0,0.0,0.0));
                }
                itemDevStatus.setTextFill(Paint.valueOf("#b7c3d7"));
                labelContainer.getChildren().add(itemDevStatus);

                deviceItemPane.getChildren().add(labelContainer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void initSocketPane(ApartmentMain Apartment){
        int socketAmountInt = Apartment.getAllSockets().size();
        for (int i = 0; i < socketAmountInt; i++) {
            try {
                HBox labelContainer = new HBox();
                labelContainer.setStyle("-fx-background-color: #02030A; -fx-background-radius: 5;");
                labelContainer.setAlignment(Pos.CENTER_LEFT);
                labelContainer.setPrefHeight(53.0);
                labelContainer.setPrefWidth(420.0);

                Label itemSocketName = new Label (
                        Apartment.getAllSockets().get(i).getSocketName()
                );
                itemSocketName.setTextFill(Paint.valueOf("#b7c3d7"));
                itemSocketName.setAlignment(Pos.CENTER);
                itemSocketName.setPrefWidth(180.0);
                labelContainer.getChildren().add(itemSocketName);

                Label itemSocketRoomName = new Label (
                        Apartment.getAllSockets().get(i).getRoom().getRoomName()
                );
                itemSocketRoomName.setTextFill(Paint.valueOf("#b7c3d7"));
                itemSocketRoomName.setAlignment(Pos.CENTER);
                itemSocketRoomName.setPrefWidth(180.0);
                labelContainer.getChildren().add(itemSocketRoomName);

                Label itemSocketDevice = new Label (
                        Apartment.getAllSockets().get(i).getDevice().getDeviceName()
                );
                itemSocketDevice.setTextFill(Paint.valueOf("#b7c3d7"));
                itemSocketDevice.setAlignment(Pos.CENTER);
                itemSocketDevice.setPrefWidth(180.0);
                labelContainer.getChildren().add(itemSocketDevice);

                socketItemPane.getChildren().add(labelContainer);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        addSocketBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(Apartment.getAllRooms().size() == 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("some alert");
                    alert.setHeaderText("Information Alert");
                    String s ="No rooms exists. Add room first.";
                    alert.setContentText(s);
                    alert.show();
                    return;
                }
                if(roomForSocketEnter.getText().equals("") || socketNameEnter.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("some alert");
                    alert.setHeaderText("Information Alert");
                    String s ="Room or socket name cannot be empty";
                    alert.setContentText(s);
                    alert.show();
                    return;
                }
                if(Apartment.findRoom(roomForSocketEnter.getText()) == null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("some alert");
                    alert.setHeaderText("Information Alert");
                    String s ="No room found with provided name. Try another one";
                    alert.setContentText(s);
                    alert.show();
                    return;
                }

                Apartment.addSocket(socketNameEnter.getText(),roomForSocketEnter.getText());
                socketAmountOverview.setText(String.valueOf(Integer.parseInt(socketAmountOverview.getText()) + 1));

                HBox labelContainer = new HBox();
                labelContainer.setStyle("-fx-background-color: #02030A; -fx-background-radius: 5;");
                labelContainer.setAlignment(Pos.CENTER_LEFT);
                labelContainer.setPrefHeight(53.0);
                labelContainer.setPrefWidth(420.0);

                Label itemSocketName = new Label (
                        Apartment.getAllSockets().get(Apartment.getAllSockets().size() - 1).getSocketName()
                );
                itemSocketName.setTextFill(Paint.valueOf("#b7c3d7"));
                itemSocketName.setAlignment(Pos.CENTER);
                itemSocketName.setPrefWidth(180.0);
                labelContainer.getChildren().add(itemSocketName);

                Label itemSocketRoomName = new Label (
                        Apartment.getAllSockets().get(Apartment.getAllSockets().size() - 1).getRoom().getRoomName()
                );
                itemSocketRoomName.setTextFill(Paint.valueOf("#b7c3d7"));
                itemSocketRoomName.setAlignment(Pos.CENTER);
                itemSocketRoomName.setPrefWidth(180.0);
                labelContainer.getChildren().add(itemSocketRoomName);

                Label itemSocketDevice = new Label ("-");
                if(Apartment.getAllSockets().get(Apartment.getAllSockets().size() - 1).getDevice() != null){
                    itemSocketDevice.setText(Apartment.getAllSockets().get(Apartment.getAllSockets().size() - 1).getDevice().getDeviceName());
                }
                itemSocketDevice.setTextFill(Paint.valueOf("#b7c3d7"));
                itemSocketDevice.setAlignment(Pos.CENTER);
                itemSocketDevice.setPrefWidth(180.0);
                labelContainer.getChildren().add(itemSocketDevice);

                socketItemPane.getChildren().add(labelContainer);
            }
        });
    }

    public void initRoomPane(ApartmentMain Apartment){
        int roomAmountInt = Apartment.getAllRooms().size();
        for (int i = 0; i < roomAmountInt; i++) {
            try {
                HBox labelContainer = new HBox();
                labelContainer.setStyle("-fx-background-color: #02030A; -fx-background-radius: 5;");
                labelContainer.setAlignment(Pos.CENTER_LEFT);
                labelContainer.setPrefHeight(53.0);
                labelContainer.setPrefWidth(325.0);

                Label itemRoomName = new Label (
                        Apartment.getAllRooms().get(i).getRoomName()
                );
                itemRoomName.setTextFill(Paint.valueOf("#b7c3d7"));
                itemRoomName.setAlignment(Pos.CENTER);
                itemRoomName.setPrefWidth(200.0);
                labelContainer.getChildren().add(itemRoomName);
                roomsItemPane.getChildren().add(labelContainer);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        addRoomBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(roomNameEnter.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("some alert");
                    alert.setHeaderText("Information Alert");
                    String s ="Room name cannot be empty";
                    alert.setContentText(s);
                    alert.show();
                    return;
                }
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
                roomsItemPane.getChildren().add(labelContainer);
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
