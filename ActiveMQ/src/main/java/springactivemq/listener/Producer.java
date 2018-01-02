package springactivemq.listener;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class Producer {
	@Qualifier("responseQueue")
	private Destination responseDestination;

	private JmsTemplate jmsTemplate;

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	// 使用JMSTemplate发送消息
	public void send(Destination destination, final String msg) {
		jmsTemplate.send(destination, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				System.out.println("发送消息,消息内容是:" + msg);
				TextMessage textMessage = session.createTextMessage(msg);
				textMessage.setJMSReplyTo(responseDestination);
				return textMessage;
			}
		});
	}
}
