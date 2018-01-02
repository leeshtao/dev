package springactivemq.listener.messagelistener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener {

	public void onMessage(Message message) {
		TextMessage textMsg = (TextMessage) message;
		try {
			System.out.println("���յ�����Ϣ����Ϣ�����ǣ�" + textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
