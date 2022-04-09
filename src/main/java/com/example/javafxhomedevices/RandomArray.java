package com.example.javafxhomedevices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class RandomArray implements Serializable {



    private final ArrayList<Integer> randomArray;

    public RandomArray(){
        this.randomArray = new ArrayList<>();
    }

    public RandomArray(RandomArray receivedArray){
        this.randomArray = receivedArray.getRandomArray();
    }

    public String randomArrayToString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer i: randomArray){
            stringBuilder.append(i).append(" ");
        }
        return stringBuilder.toString();
    }
    public void makeRandomArray(ConfigArray configArray){
        for(int i = 0; i < configArray.getArrayWithConfig()[0]; i++){
            Random random = new Random();
            randomArray.add(random.nextInt(configArray.getArrayWithConfig()[1]));
        }
    }

    public ArrayList<Integer> getRandomArray() {
        return randomArray;
    }
}
