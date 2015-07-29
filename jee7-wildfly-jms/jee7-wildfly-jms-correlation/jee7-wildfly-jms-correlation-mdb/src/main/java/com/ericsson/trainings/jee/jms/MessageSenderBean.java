package com.ericsson.trainings.jee.jms;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@Local(MessageSenderLocal.class)
public class MessageSenderBean implements MessageSenderLocal {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageSenderBean.class);

	@Inject
	private JMSContext jmsContext;

	@Resource(lookup = "java:/queue/request")
	private Queue requestQueue;

	@Resource(lookup = "java:/queue/response")
	private Queue responseQueue;

	public void sendMessage(final String text) {
		try {
			final TextMessage textMessage = jmsContext.createTextMessage(text);
			jmsContext.createProducer().setJMSReplyTo(responseQueue).send(requestQueue, textMessage);
			LOGGER.info("-- Sending test message [{}] to TestQueue", text);

			final JMSConsumer consumer = jmsContext.createConsumer(responseQueue);
			LOGGER.info("-- Create consumer for response queue");
			final String response = consumer.receiveBody(String.class, 5000);
			LOGGER.info("-- Received response : {}", response);
		} catch (Exception e) {
			LOGGER.error("A problem occurred during the delivery of this message", e);
		}
	}
}
