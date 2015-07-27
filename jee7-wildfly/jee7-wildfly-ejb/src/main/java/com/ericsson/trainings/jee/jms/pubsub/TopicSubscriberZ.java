package com.ericsson.trainings.jee.jms.pubsub;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Message-Driven Bean listening <b>topic/test</b> topic and process incoming messages.
 * 
 * @author KAPIL KUMAR
 * @version 1.0
 */
@MessageDriven(name = "TopicSubscriberZ", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/test"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class TopicSubscriberZ implements MessageListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(TopicSubscriberZ.class);

	@PostConstruct
	public void init() {
		LOGGER.info("TopicSubscriberZ initialized...");
	}

	@PreDestroy
	public void destroy() {
		LOGGER.info("TopicSubscriberZ destroyed...");
	}

	public void onMessage(Message message) {
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
