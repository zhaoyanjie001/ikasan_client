package com.example.test;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MainTest {

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory =
                new ActiveMQConnectionFactory("tcp://192.168.9.11:61616");

        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // ===== 发送端 =====
        MessageProducer producer =
                session.createProducer(session.createQueue("INBOUND.QUEUE"));

        String key = "testKey";
        String value = "HELLO ACTIVE MQ FROM JAVA";

        TextMessage msg = session.createTextMessage(key + "|" + value);

        producer.send(msg);

        System.out.println("Sent MQ => " + msg.getText());

        // ===== 接收 OUTBOUND =====
        MessageConsumer consumer =
                session.createConsumer(session.createQueue("OUTBOUND.QUEUE"));

        System.out.println("Waiting OUTBOUND...");

        Message response = consumer.receive(10000);

        if (response == null) {
            System.out.println("❌ OUTBOUND EMPTY (check consumer)");
            return;
        }

        System.out.println("REPLY => " +
                ((TextMessage) response).getText());

        System.out.println("Done");
    }
}