package springactivemq.demo2;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import org.springframework.jms.core.JmsTemplate;

public class Receiver {

	private JmsTemplate jmsTemplate;

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Receiver() {

	}

	public String receiveMessage() {
		String receiveMessage = "";
		MapMessage message = (MapMessage) jmsTemplate.receive();
		try {
			receiveMessage = message.getString("lastName");
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return receiveMessage;
	}
}
