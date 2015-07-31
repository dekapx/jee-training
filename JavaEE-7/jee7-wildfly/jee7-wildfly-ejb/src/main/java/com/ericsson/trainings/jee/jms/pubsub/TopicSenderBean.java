package com.ericsson.trainings.jee.jms.pubsub;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stateless Session bean uses JMS 1.1 API to send messages to <b>topic/test</b>.
 * 
 * @author KAPIL KUMAR
 * @version 1.0
 */
@Stateless
public class TopicSenderBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(TopicSenderBean.class);

	@Inject
	private JMSContext context;

	@Resource(lookup = "java:/topic/test")
	private Topic topic;

	public void sendMessage(final String text) {
		try {
			context.createProducer().send(topic, text);
			LOGGER.info("Sending test message [{}] to TestTopic", text);
		} catch (Exception e) {
			LOGGER.error("A problem occurred during the delivery of this message", e);
		}
	}
}
