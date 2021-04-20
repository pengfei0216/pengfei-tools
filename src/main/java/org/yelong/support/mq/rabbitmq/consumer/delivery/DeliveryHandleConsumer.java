package org.yelong.support.mq.rabbitmq.consumer.delivery;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 交货处理消费者
 * 
 * @date 2021年4月20日 上午10:01:31
 * @since 3.0.0
 */
public class DeliveryHandleConsumer extends DefaultConsumer {

	protected final DeliveryHandler deliveryHandler;

	public DeliveryHandleConsumer(Channel channel, DeliveryHandler deliveryHandler) {
		super(channel);
		this.deliveryHandler = deliveryHandler;
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
			throws IOException {
		deliveryHandler.handleDelivery(new DeliveryHandleParams(getChannel(), consumerTag, envelope, properties, body));
	}

}
