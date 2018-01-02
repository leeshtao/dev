package springactivemq.listener.messagelisteneradapter;

import javax.jms.Destination;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springactivemq.listener.Producer;

public class Client {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"config/applicationContext-listener.xml");

		Producer producer = (Producer) applicationContext.getBean("producer");
		Destination destination = (Destination) applicationContext.getBean("adapterQueue");

		producer.send(destination, "≤‚ ‘MessageListenerAdapter");
	}
}
