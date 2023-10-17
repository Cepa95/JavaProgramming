package com.oss.jceprnic;

import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {
    public static void main(String[] args) throws MqttException {
        Sensor sensor1=new Sensor("Water temperature =>",-3266.8,3266.8,10,"C");
        Sensor sensor2=new Sensor("Water pressure =>",0,65.36,1000,"Bar");
        Sensor sensor3=new Sensor("Water usage =>",0,65336,0,"liter");
        Sensor sensor4=new Sensor("Water usage(big) =>",0,6533.6,10,"m^3");
        Device device=new Device("josip","tcp://localhost:1883");
        device.addSensor(sensor1);
        device.addSensor(sensor2);
        device.addSensor(sensor3);
        device.addSensor(sensor4);
        device.runDevice();
        System.out.println(sensor1.getSensorData());
        System.out.println(sensor2.getSensorData());
        System.out.println(sensor3.getSensorData());
        System.out.println(sensor4.getSensorData());
        System.out.println(sensor4.getHigherRange());
        System.out.println(device.getSensors());
    }

}