package springactivemq.demo1;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;

public class JMSReceiver {

	public JMSReceiver() {

	}

	private JmsTemplate jmsTemplate;

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void receive() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"config/applicationContext-activemq.xml");
		Destination destination = (Destination) applicationContext.getBean("queueDestination");
		while (true) {
			try {
				TextMessage txtmsg = (TextMessage) jmsTemplate.receive(destination);
				if (null != txtmsg) {
					System.out.println("DB Proxy: " + txtmsg);
					System.out.println("DB Proxy Message: " + txtmsg.getText());
				} else {
					break;
				}
			} catch (JmsException e) {
				e.printStackTrace();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
