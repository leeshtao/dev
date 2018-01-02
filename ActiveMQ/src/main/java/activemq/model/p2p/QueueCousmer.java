package activemq.model.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author ������
 * @date 2017��12��28��
 * 
 *       ��Ϣ�Ľ����ߣ������ߣ�
 * 
 */
public class QueueCousmer {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;// �������ӵ��û���
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;// �û���Ӧ������
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;// ���ӵ�URL--broker

	public static void main(String[] args) throws Exception {
		// ����һ��ConnectionFactory��������MQ������
		ConnectionFactory connectionFactory;
		// ����һ�����Ӷ���
		Connection connection;
		// ʹ��Connection���󴴽�һ��Session����
		Session session;
		// ����һ��Destination����,queue����
		Queue queue;
		// Destination destination;// ��Ϣ�Ľӿڣ�Ŀ�ĵأ�
		MessageConsumer messageConsumer;

		// ʵ��������
		connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
		try {
			// ��������
			connection = connectionFactory.createConnection();
			// ��������
			connection.start();
			// �����Ự
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// destination = session.createQueue("java11");
			queue = session.createQueue("java11");
			// ����������
			// messageConsumer = session.createConsumer(destination);
			// ʹ��Session���󴴽�һ�������߶���
			messageConsumer = session.createConsumer(queue);
			while (true) {
				// ������Ϣ������ʱ��Ϊ100S
				TextMessage message = (TextMessage) messageConsumer.receive(100000);
				if (message != null) {
					System.out.println("�������յ���ϢΪ��" + message);
				} else {
					break;
				}
			}
			// �ر���Դ
			messageConsumer.close();
			session.close();
			connection.close();

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
