package test.org.yelong.support.mq.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQs {

	public static Connection getConnection() throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("pengfei");
		connectionFactory.setPassword("pengfei");
		Connection connection = connectionFactory.newConnection();
		return connection;
	}

}
