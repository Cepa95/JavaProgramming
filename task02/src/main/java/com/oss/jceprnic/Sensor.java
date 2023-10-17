package com.oss.jceprnic;

import lombok.*;

import java.util.Random;

@Getter
@Setter
public class Sensor {
    private String name;
    private double lowerRange;
    private double higherRange;
    private double value;
    private int factor;
    private String unit;

    public Sensor(String name, double lowerRange, double higherRange, int factor, String unit){

        this.name = name;
        this.lowerRange = lowerRange;
        this.higherRange = higherRange;
        this.value= Math.round((Math.random() * (this.higherRange - this.lowerRange)) + this.lowerRange);
        this.factor=factor;
        this.unit=unit;
    }
    public String getSensorData(){
        return "Device: " + name + " value: "+ value+ " factor: "+ factor + " unit: " + unit;
    }
}