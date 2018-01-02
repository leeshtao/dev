package springactivemq.demo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JMSReceiverMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"config/applicationContext-activemq.xml");
		JMSReceiver proxyJMSConsumer = (JMSReceiver) applicationContext.getBean("messageReceiver");
		proxyJMSConsumer.receive();
		System.out.println("Initial Consumer End! ");
	}
}
