package com.ericsson.trainings.jee.jms;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@Local(MessagePublisherLocal.class)
public class MessagePublisherBean implements MessagePublisherLocal {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessagePublisherBean.class);

	@Inject
	private JMSContext context;

	@Resource(lookup = "java:/topic/test")
	private Topic topic;

	@Override
	public void publishMessage(final String text) {
		try {
			context.createProducer().send(topic, text);
			LOGGER.info("Sending test message [{}] to TestTopic", text);
		} catch (Exception e) {
			LOGGER.error("A problem occurred during the delivery of this message", e);
		}
	}

}
