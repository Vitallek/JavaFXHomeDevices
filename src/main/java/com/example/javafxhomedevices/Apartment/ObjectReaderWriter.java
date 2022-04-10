package com.example.javafxhomedevices.Apartment;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


public class ObjectReaderWriter {

    public ObjectReaderWriter(){

    }

    public void writeToFile(Apartment apartment, File file){
        try {
            ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream(file));
            oOS.writeObject(apartment);
            oOS.close();
        } catch(IOException ex) {
            System.out.println("error " + ex);
        }
    }

    public Apartment loadApartmentFromFile(File file){
        try {
            try{
                FileInputStream fileInput = new FileInputStream(file);
                ObjectInputStream oIS = new ObjectInputStream(fileInput);
                Apartment apartment = (Apartment) oIS.readObject();
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
