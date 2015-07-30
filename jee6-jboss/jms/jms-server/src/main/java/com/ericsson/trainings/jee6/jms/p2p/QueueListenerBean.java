package com.ericsson.trainings.jee6.jms.p2p;

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
 * Message-Driven Bean listening <b>queue/test</b> queue and process incoming messages.
 * 
 * @author KAPIL KUMAR
 * @version 1.0
 */
@MessageDriven(name = "QueueListenerBean", activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test"), @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class QueueListenerBean implements MessageListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(QueueListenerBean.class);

	@PostConstruct
	public void init() {
		LOGGER.info("QueueListenerBean initialized...");
	}

	@PreDestroy
	public void destroy() {
		LOGGER.info("QueueListenerBean destroyed...");
	}

	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				TextMessage msg = (TextMessage) message;
				LOGGER.info("Received Message from queue: " + msg.getText());
			}
		} catch (JMSException e) {
			LOGGER.error("Exception while receiving message from the queue...", e);
			throw new RuntimeException(e);
		}
	}
}
