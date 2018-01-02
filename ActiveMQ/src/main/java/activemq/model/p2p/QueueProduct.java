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
 * 点对点模式
 * 
 * @author 李世涛
 * @date 2017年12月28日
 * 
 *       发送消息--生产者---消息提供者
 * 
 */
public class QueueProduct {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;// 建立连接的用户名
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;// 用户对应的密码
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;// 连接的URL--broker

	public static void main(String[] args) {

		// 连接工厂
		ConnectionFactory connectionFactory;
		// 连接
		Connection connection;
		// 会话
		Session session;
		// 消息的接口（目的地）
		// Destination destination;
		Queue queue;
		// 消息生产者
		MessageProducer messageProducer;

		// 1、创建一个连接工厂对象，需要指定服务的ip及端口, 初始化工厂信息
		connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
		try {
			// 2、使用工厂对象创建一个Connection对象
			connection = connectionFactory.createConnection();
			// 3、开启连接，调用Connection对象的start方法
			connection.start();// 开启连接
			// 4、创建一个Session对象。
			/**
			 * 参数1： true和false---是否开启事务
			 * 
			 * 参数2： AUTO_ACKNOWLEDGE：消息的生产者和消费者无需做任何副本操作，即可获取消息内容或发送消息（自动装配模式）
			 * CLIENT_ACKNOWLEDGE：发送消息后，需要消息的接受者客户端发送确认操作，才可以获取消息
			 * DUPS_OK_ACKNOWLEDGE：是需要副本操作的
			 * SESSION_TRANSACTED：只需要参数1为false的时候，才可以使用
			 * 
			 * 如果参数1为true，则代表连接需要开启事务，false不需要开启事务
			 * 如果参数1位true、则参数2可以为AUTO_ACKNOWLEDGE、CLIENT_ACKNOWLEDGE、
			 * DUPS_OK_ACKNOWLEDGE 如果参数1位false，则参数2可以为四种
			 */
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			// 5、使用Session对象创建一个Destination对象
			// ,此对象可以为Destination和Queue之一,两种形式queue、topic，现在应该使用queue
			// destination = session.createQueue("java11");
			// 采用queue主题协议，进行消息发送，参数代表消息名称（主题）
			queue = session.createQueue("java11");
			// 6、使用Session对象创建一个Producer对象,创建生产者
			// messageProducer = session.createProducer(destination);
			messageProducer = session.createProducer(queue);
			// 7、创建一个Message对象，可以使用TextMessage
			sendMessage(session, messageProducer);
			// 如果不写commit，消息也可以发送成功，但是是没有安全监控的，数据不存储
			// 9、提交消息
			session.commit();
			// 10、关闭资源
			messageProducer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送消息方法
	 * 
	 * @param session
	 * @param messageProducer
	 * @throws JMSException
	 */
	private static void sendMessage(Session session, MessageProducer messageProducer) throws JMSException {
		for (int i = 0; i < 5; i++) {
			TextMessage message = session.createTextMessage("生产者发送消息内容为：第" + i + "条消息");
			System.out.println("生产者发送消息内容为：第" + i + "条消息");
			// 发送消息
			messageProducer.send(message);
		}
	}
}
