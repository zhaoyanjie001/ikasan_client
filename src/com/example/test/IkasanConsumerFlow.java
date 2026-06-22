package com.example.test;

import org.springframework.jms.annotation.JmsListener;

public class IkasanConsumerFlow {

    @JmsListener(destination = "IKASAN.INPUT")
    public void onMessage(String message) {

        System.out.println("Ikasan received: " + message);

        // transform
        String[] parts = message.split("\\|");

        String key = parts[0];
        String value = parts[1];

        // business logic
        System.out.println("KEY=" + key + ", VALUE=" + value);
    }
}
