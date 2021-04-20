package test.org.yelong.support.mq.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;
import org.yelong.amqp.rabbitmq.RabbitMQUtils;

import com.rabbitmq.client.Connection;

public class RabbitMQClientTest {

	@Test
	public void basicPublish() throws IOException, TimeoutException {
		Connection connection = RabbitMQs.getConnection();
		RabbitMQUtils.basicPublish(connection, "pengfei_test01", "彭飞1".getBytes());
//		connection.close();
	}

	@Test
	public void basicPublishConsume() throws IOException, TimeoutException {
		Connection connection = RabbitMQs.getConnection();
		RabbitMQUtils.basicPublishConsume(connection, "pengfei_test01", "彭飞2".getBytes(), x -> {
			byte[] body = x.getBody();
			System.out.println(new String(body));
		});
	}

	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = RabbitMQs.getConnection();
		RabbitMQUtils.basicPublishConsume(connection, "pengfei_test01", "彭飞2".getBytes(), x -> {
			byte[] body = x.getBody();
			System.out.println(new String(body));
			x.close();
		});
	}

}
