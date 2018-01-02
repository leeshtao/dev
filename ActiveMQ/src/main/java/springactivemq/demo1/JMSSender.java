package springactivemq.demo1;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class JMSSender {

	public static void main(String[] args) {
		// ��һ������ʼ��һ��spring����
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"config/applicationContext-activemq.xml");
		// �ڶ������������л��JMSTemplate����
		JmsTemplate jmsTemplate = applicationContext.getBean("jmsTemplate", JmsTemplate.class);
		// ���������������л��һ��Destination����
		Destination destination = (Destination) applicationContext.getBean("queueDestination");
		// ���Ĳ���ʹ��JMSTemplate��������Ϣ����Ҫ֪��Destination
		jmsTemplate.send(destination, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage("Send Message:Hello ActiveMQ Text Message!");
				return textMessage;
			}
		});
		System.out.println("Send the JMS Message Successfully!");
	}
}
