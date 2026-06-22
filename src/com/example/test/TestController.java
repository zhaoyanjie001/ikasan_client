package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private JmsTemplate jmsTemplate;

	@PostMapping("/send")
	public String send(@RequestBody String msg) {
		jmsTemplate.setDefaultDestinationName("INBOUND.QUEUE");
		jmsTemplate.convertAndSend( msg);

		return "sent: " + msg;
	}
}