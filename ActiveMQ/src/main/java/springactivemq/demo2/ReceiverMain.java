package springactivemq.demo2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReceiverMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext-jms.xml");
		Receiver receiver = (Receiver) context.getBean("receiver");
		System.out.println(receiver.receiveMessage());
	}
}
