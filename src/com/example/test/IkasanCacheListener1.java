package com.example.test;

import org.apache.geode.cache.*;
import org.apache.geode.cache.util.CacheListenerAdapter;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class IkasanCacheListener1 extends CacheListenerAdapter<String, Object> {

    private Connection connection;
    private Session session;
    private MessageProducer producer;

    public void init() throws Exception {

        ConnectionFactory factory =
                new ActiveMQConnectionFactory("tcp://192.168.9.11:61616");

        connection = factory.createConnection();
        connection.start();

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("INBOUND.QUEUE");

        producer = session.createProducer(queue);
    }

    @Override
    public void afterCreate(EntryEvent<String, Object> event) {
        send(event.getKey(), event.getNewValue());
    }

    @Override
    public void afterUpdate(EntryEvent<String, Object> event) {
        send(event.getKey(), event.getNewValue());
    }

    private void send(String key, Object value) {
        try {

            TextMessage msg = session.createTextMessage(key + "|" + value);

            producer.send(msg);

            System.out.println("Sent MQ => " + msg.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}