package com.ericsson.trainings.jee6.jms.p2p;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

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

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/queue/test")
	private Queue queue;

	public void sendMessage(final String text) {
		Connection connection = null;

		try {
			connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(queue);
			TextMessage textMessage = session.createTextMessage();
			textMessage.setText(text);
			messageProducer.send(textMessage);
			LOGGER.info("Sending test message [{}] to TestQueue", textMessage.getText());
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
