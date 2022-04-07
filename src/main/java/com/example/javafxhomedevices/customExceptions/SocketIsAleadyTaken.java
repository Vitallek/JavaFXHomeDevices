package com.example.javafxhomedevices.customExceptions;

public class SocketIsAleadyTaken extends Exception {
    public SocketIsAleadyTaken(String errorMessage) {
        super(errorMessage);
    }
}