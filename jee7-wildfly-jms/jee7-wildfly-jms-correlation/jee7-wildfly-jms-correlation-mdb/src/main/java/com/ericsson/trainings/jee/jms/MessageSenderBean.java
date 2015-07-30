package com.ericsson.trainings.jee.jms;

import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@Local(MessageSenderLocal.class)
public class MessageSenderBean implements MessageSenderLocal {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageSenderBean.class);

	private static final String SENDER_ID = "TestSender";

	@Inject
	private JMSContext jmsContext;

	@Resource(lookup = "java:/queue/request")
	private Queue requestQueue;

	@Resource(lookup = "java:/queue/response")
	private Queue responseQueue;

	public void sendMessage(final String text) {
		try {
			final String jmsCorrelationId = generateJmsCorrelationId();
			final TextMessage message = jmsContext.createTextMessage();
			message.setText(text);
			message.setJMSCorrelationID(jmsCorrelationId);

			final JMSProducer jmsProducer = jmsContext.createProducer();
			jmsProducer.setJMSReplyTo(responseQueue);
			jmsProducer.send(requestQueue, message);
			LOGGER.info("-- Sending test message [{}] with JmsCorrelationId: [{}]", text, jmsCorrelationId);

			final JMSConsumer consumer = jmsContext.createConsumer(responseQueue);
			final TextMessage reply = (TextMessage) consumer.receive();
			final String response = reply.getText();
			LOGGER.info("-- Received response : [{}]", response);
		} catch (Exception e) {
			LOGGER.error("A problem occurred during the delivery of this message", e);
		}
	}

	private String generateJmsCorrelationId() {
		return SENDER_ID + "_" + UUID.randomUUID().toString();
	}
}
