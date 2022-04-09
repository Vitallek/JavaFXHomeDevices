package com.example.javafxhomedevices;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Client{

    private Socket socket;
    private ObjectInputStream inp;
    private ObjectOutputStream outp;

    private ConfigArray configArray;

    private RandomArray randomArray;

    public Client(Socket socket) {
        try{
            this.socket = socket;
            this.outp = new ObjectOutputStream(socket.getOutputStream());
            this.inp = new ObjectInputStream(socket.getInputStream());
            this.configArray = new ConfigArray();
        } catch (IOException e){
            e.printStackTrace();
            closeConnection(socket,outp,inp);
        }
    }

    public void sendToServer(ConfigArray configArray){
        try{
            if(configArray != null){
                outp.writeObject(configArray);
            }
        } catch (IOException e){
            e.printStackTrace();
            closeConnection(socket,outp,inp);
        }
    }

    public void receiveFromServer(){
        try{
            randomArray = new RandomArray((RandomArray)inp.readObject());
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            closeConnection(socket,outp,inp);
        }
    }

    public void closeConnection(Socket socket, ObjectOutputStream outp, ObjectInputStream inp){
        try{
            if(inp != null) inp.close();
            if(outp != null) outp.close();
            if(socket != null) socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public RandomArray getRandomArray() {
        return randomArray;
    }
    public ConfigArray getConfigArray() {
        return configArray;
    }
}
