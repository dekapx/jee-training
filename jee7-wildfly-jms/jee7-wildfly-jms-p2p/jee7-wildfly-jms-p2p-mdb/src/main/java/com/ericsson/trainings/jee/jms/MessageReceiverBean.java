package com.ericsson.trainings.jee.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageDriven(name = "QueueListenerBean", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class MessageReceiverBean implements MessageListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiverBean.class);

	public void onMessage(final Message message) {
		try {
			if (message instanceof TextMessage) {
				final TextMessage msg = (TextMessage) message;
				LOGGER.info("Received Message from queue: " + msg.getText());
			}
		} catch (JMSException e) {
			LOGGER.error("Exception while receiving message from the queue...", e);
			throw new RuntimeException(e);
		}
	}
}
