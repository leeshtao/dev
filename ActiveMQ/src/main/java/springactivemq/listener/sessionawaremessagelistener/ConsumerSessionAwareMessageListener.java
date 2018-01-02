package springactivemq.listener.sessionawaremessagelistener;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;

public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<TextMessage> {

	private Destination destination;

	public void onMessage(TextMessage message, Session session) throws JMSException {
		System.out.println("�յ�һ����Ϣ");
		System.out.println("��Ϣ�����ǣ�" + message.getText());
		MessageProducer producer = session.createProducer(destination);
		Message textMessage = session.createTextMessage("I get the message. Thank you.");
		producer.send(textMessage);
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

}