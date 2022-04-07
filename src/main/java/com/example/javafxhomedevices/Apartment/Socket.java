package com.example.javafxhomedevices.Apartment;

import java.io.Serializable;

public class Socket implements Serializable {

    private Device device;
    private String name;
    private Room room;

    public Socket() {

    }
    public Socket(String name, Room room){
        this.device = null;
        this.name = name;
        this.room = room;
    }

    public void setSocketName(String name){
        this.name = name;
    }
    public String getSocketName(){
        return name;
    }

    public void addDeviceToSocket(Device device) {
        this.device = device;
    }
    public void removeDeviceFromSocket(Device device){
        this.device = null;
    }
    public Device getDevice() {
        return device;
    }

    public void setRoom(Room room){
        this.room = room;
    }
    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        if(device != null){
            return "Socket{" +
                    "Name = " + name +
                    ", device = " + device.getDeviceName() +
                    ", room = " + room +
                    '}';
        } else {
            return "Socket{" +
                    "Name = " + name +
                    ", No_Device " +
                    ", room = " + room +
                    '}';
        }
    }
}
