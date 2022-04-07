package com.example.javafxhomedevices.Apartment;

import java.util.Comparator;

public class DevicePowerSorter implements Comparator<Device>
{
    @Override
    public int compare(Device d1, Device d2) {
        return d2.getDevicePower() - d1.getDevicePower();
    }
}