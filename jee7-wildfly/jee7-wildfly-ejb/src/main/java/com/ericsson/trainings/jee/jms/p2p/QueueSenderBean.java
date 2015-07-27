package com.ericsson.trainings.jee.jms.p2p;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stateless Session bean uses JMS 2.0 API to send messages to <b>queue/test</b>.
 * 
 * @author KAPIL KUMAR
 * @version 1.0
 */
@Stateless
public class QueueSenderBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(QueueSenderBean.class);

	@Inject
	private JMSContext context;

	@Resource(lookup = "java:/queue/test")
	private Queue queue;

	public void sendMessage(final String text) {

		try {
			context.createProducer().send(queue, text);
			LOGGER.info("Sending test message [{}] to TestQueue", text);
		} catch (Exception e) {
			LOGGER.error("A problem occurred during the delivery of this message", e);
		}
	}
}