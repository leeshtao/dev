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
		// 第一步：初始化一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"config/applicationContext-activemq.xml");
		// 第二步：从容器中获得JMSTemplate对象。
		JmsTemplate jmsTemplate = applicationContext.getBean("jmsTemplate", JmsTemplate.class);
		// 第三步：从容器中获得一个Destination对象
		Destination destination = (Destination) applicationContext.getBean("queueDestination");
		// 第四步：使用JMSTemplate对象发送消息，需要知道Destination
		jmsTemplate.send(destination, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage("Send Message:Hello ActiveMQ Text Message!");
				return textMessage;
			}
		});
		System.out.println("Send the JMS Message Successfully!");
	}
}
