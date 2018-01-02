package springactivemq.demo2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SenderMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext-jms.xml");
		Sender sender = (Sender) context.getBean("sender");
		sender.sendInfo();
	}
}
