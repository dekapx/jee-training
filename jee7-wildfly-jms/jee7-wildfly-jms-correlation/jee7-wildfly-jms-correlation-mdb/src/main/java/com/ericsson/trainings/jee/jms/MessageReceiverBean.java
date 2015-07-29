package com.ericsson.trainings.jee.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageDriven(name = "QueueListenerBean", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/request"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class MessageReceiverBean implements MessageListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiverBean.class);

	@Inject
	private JMSContext jmsContext;

	public void onMessage(final Message message) {
		try {
			final Destination destination = message.getJMSReplyTo();
			if (destination == null) {
				return;
			}

			final TextMessage textMessage = (TextMessage) message;
			final String jmsCorrelationId = message.getJMSCorrelationID();
			final String payload = textMessage.getText();
			LOGGER.info("-- Received Message from queue: " + payload);

			final String text = "Processed: " + payload + "for CorrelationId: " + jmsCorrelationId;
			final TextMessage responseMessage = jmsContext.createTextMessage(text);
			responseMessage.setJMSCorrelationID(jmsCorrelationId);

			final JMSProducer jmsProducer = jmsContext.createProducer();
			jmsProducer.send(destination, responseMessage);
		} catch (JMSException e) {
			LOGGER.error("Exception while receiving message from the queue...", e);
			throw new RuntimeException(e);
		}
	}
}
