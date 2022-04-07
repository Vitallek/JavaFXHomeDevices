package com.example.javafxhomedevices.Apartment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/*
 * Class Apartment
 * @param string name contains name on apartment
 * @param ArrayList rooms contains array of rooms in apartment
 * */
public class Apartment implements Serializable {

    private String name;
    private ArrayList<Room> rooms;

    public Apartment(){

    }
    public Apartment(String name){
        this.name = name;
        rooms = new ArrayList<>();
    }
    public Apartment(String name, ArrayList<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public void setApartmentName(){
        this.name = name;
    }
    public String getApartmentName(){
        return name;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
    public ArrayList<Room> getRooms() {
        return rooms;
    }
    public void addRoomToAppartment(Room room){
        rooms.add(room);
    }

    public Room findRoom(String roomName){
        for (Room room : rooms){
            if (room.getRoomName().equalsIgnoreCase(roomName)) return room;
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Apartment) return true;
        Apartment apartment = (Apartment) o;
        return name.equals(apartment.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
