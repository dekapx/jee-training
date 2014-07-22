package com.ericsson.trainings.jee6.jms.pubsub;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Message-Driven Bean listening <b>topic/test</b> queue and process incoming messages.
 * 
 * @author KAPIL KUMAR
 * @version 1.0
 */
@MessageDriven(name = "TopicSubscriberZ", activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/test"), @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class TopicSubscriberZ implements MessageListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(TopicSubscriberZ.class);

	public void onMessage(Message rcvMessage) {
		try {
			if (rcvMessage instanceof TextMessage) {
				TextMessage msg = (TextMessage) rcvMessage;
				LOGGER.info("Received message [{}] by [{}] from Topic [topic/test]", msg.getText(), this.getClass().getSimpleName());
			}
		} catch (JMSException e) {
			LOGGER.error("Exception while receiving message from the Topic...", e);
			throw new RuntimeException(e);
		}
	}
}
