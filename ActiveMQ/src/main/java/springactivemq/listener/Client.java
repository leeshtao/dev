package springactivemq.listener;

import javax.jms.Destination;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"config/applicationContext-listener.xml");

		Producer producer = (Producer) applicationContext.getBean("producer");
		// Destination destination = (Destination)
		// applicationContext.getBean("queueDestination");
		Destination destination = (Destination) applicationContext.getBean("sessionAwareQueue");

		for (int i = 0; i < 10;) {
			producer.send(destination, "I am sender" + (++i));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
