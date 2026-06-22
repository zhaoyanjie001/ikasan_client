package com.example.test;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

@Component
public class InboundConsumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "INBOUND.QUEUE")
    public void receive(String message) {

        System.out.println("RECEIVED => " + message);

        // ===== 业务处理 =====
        String result = "XXXXX_" + message;

        // ===== 写 OUTBOUND =====
        jmsTemplate.convertAndSend("OUTBOUND.QUEUE", result);

        System.out.println("SENT OUTBOUND => " + result);
    }
}