package com.example.javafxhomedevices.Apartment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
public class Room implements Serializable {

    private String name;
    private ArrayList<Socket> socketList;
    private ArrayList<Device> deviceList;

    public Room(){

    }
    public Room(String name){
        this.name = name;
        socketList = new ArrayList<>();
        deviceList = new ArrayList<>();
    }
    public Room(String name, ArrayList<Socket> socketList, ArrayList<Device> deviceList) {
        this.name = name;
        this.socketList = socketList;
        this.deviceList = deviceList;
    }

    public void setRoomName(){
        this.name = name;
    }
    public String getRoomName(){
        return name;
    }

    public void addSocketToRoom(Socket socket) {
        socketList.add(socket);
    }
    public void removeSocketFromRoom(Socket socket){
        socketList.remove(socket);
    }
    public ArrayList<Socket> getSocketList() {
        return socketList;
    }

    public Socket findSocketInRoom(String socketName){
        for (Socket socket : socketList){
            if (socket.getSocketName().equalsIgnoreCase(socketName)) return socket;
        }
        return null;
    }

    public void addDeviceToRoom(Device device) {
        deviceList.add(device);
    }
    public void removeDeviceFromRoom(Device device){
        deviceList.remove(device);
    }
    public ArrayList<Device> getDeviceList() {
        return deviceList;
    }

    public Device findDeviceInRoom(String deviceName){
        for (Device device : deviceList){
            if (device.getDeviceName().equalsIgnoreCase(deviceName)) return device;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Room " + name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Room) return true;
        Room room = (Room) o;
        return name.equals(room.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
