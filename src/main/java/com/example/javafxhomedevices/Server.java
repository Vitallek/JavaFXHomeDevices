package com.example.javafxhomedevices;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectInputStream inp;
    private ObjectOutputStream outp;
    private ConfigArray configArray;
    private RandomArray randomArray;

    public static void main(String[] args) {
        try{
            System.out.println("starting server...\n");
            Server server  = new Server(new ServerSocket(7890));

            System.out.println("server is listening on port 7890...");
            while (true) {
                server.receiveFromClient();
                System.out.println("received from " + server.socket.getRemoteSocketAddress().toString());
                System.out.println(server.configArray);
                System.out.println("performing task...");
                server.replyToClient();
                System.out.println(server.randomArray.randomArrayToString());
                System.out.println("replied to " + server.socket.getRemoteSocketAddress().toString());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Server(ServerSocket serverSocket) {
        try{
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();

            this.outp = new ObjectOutputStream(socket.getOutputStream());
            this.inp = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void replyToClient(){
        if(socket.isConnected()){
            try{
                if(configArray != null){
                    randomArray = new RandomArray();
                    randomArray.makeRandomArray(configArray);
                    outp.writeObject(randomArray);
                }
            } catch (IOException e){
                e.printStackTrace();
                closeConnection(serverSocket,socket,outp,inp);
            }
        }
    }

    public void receiveFromClient(){
        if(socket.isConnected()){
            try{
                configArray = new ConfigArray((ConfigArray)inp.readObject());
                System.out.println(configArray);
            } catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
                closeConnection(serverSocket,socket,outp,inp);
            }
        }
    }

    public void closeConnection(ServerSocket serverSocket, Socket socket, ObjectOutputStream outp, ObjectInputStream inp){
        try{
            if(inp != null) inp.close();
            if(outp != null) outp.close();
            if(serverSocket != null) serverSocket.close();
            if(socket != null) socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }
}




