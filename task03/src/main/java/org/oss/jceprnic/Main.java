package org.oss.jceprnic;

public class Main {
    public static void main(String[] args) {
        String jsonFilePath = "src/main/resources/deserialization.json";
        Device device = Deserialization.deserialize(jsonFilePath);
        device.runDevice();
    }
}

