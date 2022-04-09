package com.example.javafxhomedevices;

import java.io.Serializable;

public class ConfigArray implements Serializable {


    private final int[] arrayWithConfig;

    public ConfigArray(){
        this.arrayWithConfig = new int[2];
    }
    public ConfigArray(ConfigArray receivedArray){
        this.arrayWithConfig = receivedArray.getArrayWithConfig();
    }

    public int[] getArrayWithConfig() {
        return arrayWithConfig;
    }

    public void fillConfigArray(int numAmount, int maxNum){
        this.arrayWithConfig[0] = numAmount;
        this.arrayWithConfig[1] = maxNum;
    }

}
