package org.oss.jceprnic;

import lombok.*;
import org.eclipse.paho.client.mqttv3.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Device {
    private MqttClient mqttClient;
    private String topic;
    private String address;
    private List<Sensor> sensors;
    private String jsonPath;

    public Device(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public void runDevice() {
        try {
            mqttClient = new MqttClient(this.address, this.topic);
        } catch (MqttException e) {
            System.err.println("MQTT connection error: " + e.getMessage());
        }
        try {
            MqttConnectOptions connection = new MqttConnectOptions();
            mqttClient.connect(connection);
            int iterator = 1;
            for (Sensor sensor : sensors) {
                sensor.getRandomValue();
                String message = sensor.getSensorData();
                MqttMessage messageBytes = new MqttMessage(message.getBytes());
                mqttClient.publish(this.topic, messageBytes);
                System.out.println(iterator + ". Message published");
                iterator++;
            }
            mqttClient.disconnect();
            mqttClient.close();
        } catch (MqttException e) {
            System.err.println("MQTT error: " + e.getMessage());
        }
    }
}

