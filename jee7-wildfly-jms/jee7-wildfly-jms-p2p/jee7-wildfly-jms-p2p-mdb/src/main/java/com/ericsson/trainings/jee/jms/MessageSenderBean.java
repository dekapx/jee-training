package com.ericsson.trainings.jee.jms;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
	private JMSContext context;

	@Resource(lookup = "java:/queue/test")
	private Queue queue;

	public void sendMessage(final String text) {
		try {
			final TextMessage textMessage = context.createTextMessage(text);
			context.createProducer().send(queue, textMessage);
			LOGGER.info("Sending test message [{}] to TestQueue", text);
		} catch (Exception e) {
			LOGGER.error("A problem occurred during the delivery of this message", e);
		}
	}
}
