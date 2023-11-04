package org.oss.jceprnic;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sensor {
    private String name;
    private double lowerRange;
    private double higherRange;
    private double value = 0;
    private int factor;
    private String unit;

    public void getRandomValue() {
        this.value = Math.round((Math.random() * (this.higherRange - this.lowerRange)) + this.lowerRange);
    }

    public String getSensorData() {
        return "Device: " + name + " value: " + value + " factor: " + factor + " unit: " + unit;
    }
}