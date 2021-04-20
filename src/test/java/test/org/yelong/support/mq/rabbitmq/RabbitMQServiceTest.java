package test.org.yelong.support.mq.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.yelong.support.mq.rabbitmq.RabbitMQUtils;

public class RabbitMQServiceTest {

	public static void main(String[] args) throws IOException, TimeoutException {
		RabbitMQUtils.basicConsume(RabbitMQs.getConnection(), "pengfei_test01", x -> {
			byte[] body = x.getBody();
			String bodyStr = new String(body);
			System.out.println(bodyStr);
			x.replyTo(("消费成功了：" + bodyStr).getBytes());
			x.basicAck();
		});
	}

}
