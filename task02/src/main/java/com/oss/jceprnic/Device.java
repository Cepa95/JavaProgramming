package com.oss.jceprnic;


import lombok.Getter;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Device {
    private MqttClient mqttClient;
    private List<Sensor> sensors;
    private String topic;
    private String address;

    public Device(String topic, String address) {
        this.topic = topic;
        this.address = address;
        sensors = new ArrayList<>();

        try {
            mqttClient = new MqttClient(address, topic);
        } catch (MqttException e) {
            System.out.println("Error while creating MqttClient: " + e.getMessage());
        }
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public void runDevice() throws MqttException {
        MqttConnectOptions connection = new MqttConnectOptions();

        try {
            mqttClient.connect(connection);
        } catch (MqttException err) {
            System.out.println("MqttException during connection");
            throw err;
        }

        for (Sensor sensor : sensors) {
            String message = sensor.getSensorData();
            MqttMessage messageBytes = new MqttMessage(message.getBytes());
            mqttClient.publish(topic, messageBytes);
            System.out.println("Message published");
        }
        mqttClient.disconnect();
        mqttClient.close();
    }


}
