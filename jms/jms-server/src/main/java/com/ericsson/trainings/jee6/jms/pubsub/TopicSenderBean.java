package com.ericsson.trainings.jee6.jms.pubsub;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
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

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/topic/test")
	private Topic topic;

	public void sendMessage(final String text) {
		Connection connection = null;

		try {
			connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(topic);
			TextMessage textMessage = session.createTextMessage();
			textMessage.setText(text);
			messageProducer.send(textMessage);
			LOGGER.info("Sending test message [{}] to TestTopic", textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
			LOGGER.error("A problem occurred during the delivery of this message", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					LOGGER.error("Exception while closing JMS connection...", e);
				}
			}
		}
	}
}
