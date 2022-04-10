package com.example.javafxhomedevices.Apartment;

import com.example.javafxhomedevices.customExceptions.NoFreeSockets;
import com.example.javafxhomedevices.customExceptions.OverPower;
import com.example.javafxhomedevices.customExceptions.SocketIsAleadyTaken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Device implements Serializable {
    private String name;
    private int power;
    private Socket socket;
    private boolean isOn;
    private Room room;

    public Device(){

    }
    public Device(String name, int power, Socket socket, boolean isOn, Room room) {
        this.name = name;
        this.power = power;
        this.socket = socket;
        this.isOn = isOn;
        this.room = room;
    }

    public void setDeviceName(){
        this.name = name;
    }
    public String getDeviceName(){
        return name;
    }

    public void setDevicePower(int power){
        this.power = power;
    }
    public int getDevicePower(){
        return power;
    }

    public void addSocketToDevice(Socket socket){
        this.socket = socket;
    }
    public void removeSocketFromDevice(){
        this.socket = null;
    }
    public Socket getSocket() {
        return socket;
    }

    public void switchPower(){this.isOn = !this.isOn;}
    public void turnDeviceOn() {
        this.isOn = true;
    }
    public void turnDeviceOff() {
        this.isOn = false;
    }
    public boolean getIsOn() {
        return isOn;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Device{" +
                "Name = " + name +
                ", power = " + power +
                ", socket = " + socket.getSocketName() +
                ", is enable = " + isOn +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Device) return true;
        Device device = (Device) o;
        return name.equals(device.name) && power == device.power;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, power);
    }

}
