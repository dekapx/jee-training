package com.ericsson.trainings.jee.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageDriven(name = "MessageSubscriberBean", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/test"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class MessageSubscriberBean implements MessageListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageSubscriberBean.class);

	public void onMessage(final Message message) {
		try {
			if (message instanceof TextMessage) {
				TextMessage msg = (TextMessage) message;
				LOGGER.info("Received message [{}] by [{}] from Topic [topic/test]", msg.getText(), this.getClass().getSimpleName());
			}
		} catch (JMSException e) {
			LOGGER.error("Exception while receiving message from the Topic...", e);
			throw new RuntimeException(e);
		}
	}
}
