package com.example.test;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsConfig {

	@Bean
	public ConnectionFactory connectionFactory() {
	    return new ActiveMQConnectionFactory("tcp://192.168.9.11:61616");
	}

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory cf) {
	    return new JmsTemplate(cf);
	}
}