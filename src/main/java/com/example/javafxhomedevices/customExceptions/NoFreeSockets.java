package com.example.javafxhomedevices.customExceptions;

public class NoFreeSockets extends Exception {
    public NoFreeSockets(String errorMessage) {
        super(errorMessage);
    }
}