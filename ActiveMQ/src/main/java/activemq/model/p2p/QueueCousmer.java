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
 * @author 李世涛
 * @date 2017年12月28日
 * 
 *       消息的接受者（消费者）
 * 
 */
public class QueueCousmer {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;// 建立连接的用户名
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;// 用户对应的密码
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;// 连接的URL--broker

	public static void main(String[] args) throws Exception {
		// 创建一个ConnectionFactory对象连接MQ服务器
		ConnectionFactory connectionFactory;
		// 创建一个连接对象
		Connection connection;
		// 使用Connection对象创建一个Session对象
		Session session;
		// 创建一个Destination对象,queue对象
		Queue queue;
		// Destination destination;// 消息的接口（目的地）
		MessageConsumer messageConsumer;

		// 实例化工厂
		connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
		try {
			// 建立连接
			connection = connectionFactory.createConnection();
			// 开启连接
			connection.start();
			// 创建会话
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// destination = session.createQueue("java11");
			queue = session.createQueue("java11");
			// 创建接受者
			// messageConsumer = session.createConsumer(destination);
			// 使用Session对象创建一个消费者对象
			messageConsumer = session.createConsumer(queue);
			while (true) {
				// 接收消息，接收时间为100S
				TextMessage message = (TextMessage) messageConsumer.receive(100000);
				if (message != null) {
					System.out.println("消费者收到消息为：" + message);
				} else {
					break;
				}
			}
			// 关闭资源
			messageConsumer.close();
			session.close();
			connection.close();

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
