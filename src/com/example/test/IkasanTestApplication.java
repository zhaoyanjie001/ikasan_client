package com.example.test;


import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
@SpringBootApplication(scanBasePackages = "com.example.test")
public class IkasanTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(IkasanTestApplication.class, args);
    }
}