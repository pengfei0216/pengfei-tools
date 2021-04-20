package org.yelong.support.mq.rabbitmq;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import org.yelong.support.mq.rabbitmq.consumer.delivery.DeliveryHandleConsumer;
import org.yelong.support.mq.rabbitmq.consumer.delivery.DeliveryHandler;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQ工具类
 * 
 * @date 2021年3月22日下午2:55:54
 * @since 3.0.0
 */
public final class RabbitMQUtils {

	// ==================================================constructor==================================================

	private RabbitMQUtils() {
	}

	// ==================================================创建连接==================================================

	/**
	 * 创建连接
	 * 
	 * @param connectionProperties 连接配置
	 * @return 连接工厂
	 */
	public static ConnectionFactory createConnectionFactory(ConnectionProperties connectionProperties)
			throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(connectionProperties.getHost());
		factory.setPort(connectionProperties.getPort());
		factory.setUsername(connectionProperties.getUsername());
		factory.setPassword(connectionProperties.getPassword());
		return factory;
	}

	/**
	 * 创建连接
	 * 
	 * @param connectionProperties 连接配置
	 * @return 连接
	 */
	public static Connection createConnection(ConnectionProperties connectionProperties)
			throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(connectionProperties.getHost());
		factory.setPort(connectionProperties.getPort());
		factory.setUsername(connectionProperties.getUsername());
		factory.setPassword(connectionProperties.getPassword());
		return createConnectionFactory(connectionProperties).newConnection();
	}

	// ==================================================向对列发送消息==================================================

	/**
	 * 基本的向对列发送消息
	 * 
	 * @param connection RabbitMQ连接
	 * @param queueName  对列名称
	 * @param body       发送的消息内容
	 */
	public static void basicPublish(Connection connection, String queueName, byte[] body)
			throws IOException, TimeoutException {
		// 创建信道
		Channel channel = connection.createChannel();
		// 创建一个type="direct"、持久化的、非自动删除的交换器
//		channel.exchangeDeclare("exchange_demo", "direct", true, false, null);
		// 创建一个持久化、非排他的、非自动删除的队列
		channel.queueDeclare(queueName, true, false, false, null);
		// 将交换器与队列通过路由键绑定
//		channel.queueBind(queueName, "exchange_demo", "routingkey_demo");
		channel.basicPublish("", queueName, null, body);
		channel.close();
	}

	/**
	 * 基本的向对列发送消息并接收消费端返回的信息
	 * 
	 * @param connection      RabbitMQ连接
	 * @param queueName       对列名称
	 * @param body            发送的消息内容
	 * @param deliveryHandler 交互消息处理器
	 */
	public static void basicPublishConsume(Connection connection, String queueName, byte[] body,
			DeliveryHandler deliveryHandler) throws IOException, TimeoutException {
		Channel channel = connection.createChannel();
		String replyQueueName = channel.queueDeclare().getQueue();
		String collectionId = UUID.randomUUID().toString();
		// 存入回调队列名与collectionId
		BasicProperties bpro = new BasicProperties().builder().correlationId(collectionId).replyTo(replyQueueName)
				.build();
		channel.basicPublish("", queueName, bpro, body);
		// 指定消费队列 关闭ack机制
		channel.basicConsume(replyQueueName, true, new DeliveryHandleConsumer(channel, deliveryHandler));
	}

	// ==================================================消费端==================================================

	/**
	 * 基础的消费
	 * 
	 * @date 2021年4月20日 上午11:05:07
	 * @param connection      连接
	 * @param queueName       队列
	 * @param deliveryHandler 交货消息处理器
	 */
	public static void basicConsume(Connection connection, String queueName, DeliveryHandler deliveryHandler)
			throws IOException {
		// 声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
		Channel channel = connection.createChannel();
		channel.queueDeclare(queueName, true, false, false, null);
		// 创建队列消费者
		// 指定消费队列 打开ack机制
		channel.basicConsume(queueName, false, new DeliveryHandleConsumer(channel, deliveryHandler));
	}

}
