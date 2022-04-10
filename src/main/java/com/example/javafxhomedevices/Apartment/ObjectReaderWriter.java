package com.example.javafxhomedevices.Apartment;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


public class ObjectReaderWriter {

    public ObjectReaderWriter(){

    }

    public void writeToFile(Apartment apartment, String path, File file){
        try {
            ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream(file));
            oOS.writeObject(apartment);
            oOS.close();
        } catch(IOException ex) {
            System.out.println("error " + ex);
        }
    }

    public Apartment loadApartmentFromFile(String filename){
        try {
            try{
                FileInputStream fileInput = new FileInputStream(filename);
                ObjectInputStream oIS = new ObjectInputStream(fileInput);
                Apartment apartment = new Apartment();
                apartment = (Apartment) oIS.readObject();
                System.out.println("Loaded from " + filename);
                oIS.close();
                return apartment;
            } catch (FileNotFoundException ex){
                System.out.println("file not found " + ex);
                return null;
            }
        } catch (IOException | ClassNotFoundException ex){
            System.out.println("error " + ex );
            return null;
        }

    }
}
