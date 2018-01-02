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

	// ʹ��JMSTemplate������Ϣ
	public void send(Destination destination, final String msg) {
		jmsTemplate.send(destination, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				System.out.println("������Ϣ,��Ϣ������:" + msg);
				TextMessage textMessage = session.createTextMessage(msg);
				textMessage.setJMSReplyTo(responseDestination);
				return textMessage;
			}
		});
	}
}
