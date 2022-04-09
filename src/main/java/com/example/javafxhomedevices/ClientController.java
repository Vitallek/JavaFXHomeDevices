package com.example.javafxhomedevices;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientController implements Initializable{
    @FXML
    public AnchorPane mainPane;
    @FXML
    public Label welcomeLabel;
    @FXML
    public Label enterAmountLabel;
    @FXML
    public Label enterMaxValueLabel;
    @FXML
    public ScrollPane resultPanel;
    @FXML
    private Button goButton;
    @FXML
    private TextField numbersAmount;
    @FXML
    private TextField maxNumberValue;
    @FXML
    private VBox answerZone;

    private Client client;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            client = new Client(new Socket("localhost", 7890));
            System.out.println("created new client\n");
        } catch (IOException e){
            e.printStackTrace();
        }

        goButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int sendNumAmount = Integer.parseInt(numbersAmount.getText());
                int sendMaxNum = Integer.parseInt(maxNumberValue.getText());
                if(!numbersAmount.getText().isEmpty() && !maxNumberValue.getText().isEmpty()){
                    client.getConfigArray().getArrayWithConfig()[0] = sendNumAmount;
                    client.getConfigArray().getArrayWithConfig()[1] = sendMaxNum;

                    client.sendToServer(client.getConfigArray());
                    System.out.println(client.getConfigArray());
                    client.receiveFromServer();
                    ClientController.addLabel(answerZone,client.getRandomArray());
                }
            }
        });
    }

    public static void addLabel(VBox vBox, RandomArray randomArray){
        HBox hBox = new HBox();
        Text text = new Text(randomArray.randomArrayToString());
        TextFlow textFlow = new TextFlow(text);
        hBox.getChildren().add(textFlow);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });
    }
}