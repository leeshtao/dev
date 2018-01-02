package activemq.model.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * ��Ե�ģʽ
 * 
 * @author ������
 * @date 2017��12��28��
 * 
 *       ������Ϣ--������---��Ϣ�ṩ��
 * 
 */
public class QueueProduct {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;// �������ӵ��û���
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;// �û���Ӧ������
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;// ���ӵ�URL--broker

	public static void main(String[] args) {

		// ���ӹ���
		ConnectionFactory connectionFactory;
		// ����
		Connection connection;
		// �Ự
		Session session;
		// ��Ϣ�Ľӿڣ�Ŀ�ĵأ�
		// Destination destination;
		Queue queue;
		// ��Ϣ������
		MessageProducer messageProducer;

		// 1������һ�����ӹ���������Ҫָ�������ip���˿�, ��ʼ��������Ϣ
		connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
		try {
			// 2��ʹ�ù������󴴽�һ��Connection����
			connection = connectionFactory.createConnection();
			// 3���������ӣ�����Connection�����start����
			connection.start();// ��������
			// 4������һ��Session����
			/**
			 * ����1�� true��false---�Ƿ�������
			 * 
			 * ����2�� AUTO_ACKNOWLEDGE����Ϣ�������ߺ��������������κθ������������ɻ�ȡ��Ϣ���ݻ�����Ϣ���Զ�װ��ģʽ��
			 * CLIENT_ACKNOWLEDGE��������Ϣ����Ҫ��Ϣ�Ľ����߿ͻ��˷���ȷ�ϲ������ſ��Ի�ȡ��Ϣ
			 * DUPS_OK_ACKNOWLEDGE������Ҫ����������
			 * SESSION_TRANSACTED��ֻ��Ҫ����1Ϊfalse��ʱ�򣬲ſ���ʹ��
			 * 
			 * �������1Ϊtrue�������������Ҫ��������false����Ҫ��������
			 * �������1λtrue�������2����ΪAUTO_ACKNOWLEDGE��CLIENT_ACKNOWLEDGE��
			 * DUPS_OK_ACKNOWLEDGE �������1λfalse�������2����Ϊ����
			 */
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			// 5��ʹ��Session���󴴽�һ��Destination����
			// ,�˶������ΪDestination��Queue֮һ,������ʽqueue��topic������Ӧ��ʹ��queue
			// destination = session.createQueue("java11");
			// ����queue����Э�飬������Ϣ���ͣ�����������Ϣ���ƣ����⣩
			queue = session.createQueue("java11");
			// 6��ʹ��Session���󴴽�һ��Producer����,����������
			// messageProducer = session.createProducer(destination);
			messageProducer = session.createProducer(queue);
			// 7������һ��Message���󣬿���ʹ��TextMessage
			sendMessage(session, messageProducer);
			// �����дcommit����ϢҲ���Է��ͳɹ���������û�а�ȫ��صģ����ݲ��洢
			// 9���ύ��Ϣ
			session.commit();
			// 10���ر���Դ
			messageProducer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������Ϣ����
	 * 
	 * @param session
	 * @param messageProducer
	 * @throws JMSException
	 */
	private static void sendMessage(Session session, MessageProducer messageProducer) throws JMSException {
		for (int i = 0; i < 5; i++) {
			TextMessage message = session.createTextMessage("�����߷�����Ϣ����Ϊ����" + i + "����Ϣ");
			System.out.println("�����߷�����Ϣ����Ϊ����" + i + "����Ϣ");
			// ������Ϣ
			messageProducer.send(message);
		}
	}
}
