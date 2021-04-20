package org.yelong.amqp.rabbitmq.consumer.delivery;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;

/**
 * 交货处理参数
 * 
 * @date 2021年1月21日下午7:08:39
 * @since 3.0.0
 */
public class DeliveryHandleParams {

	private final Channel channel;

	private final String consumerTag;

	private final Envelope envelope;

	private final BasicProperties properties;

	private final byte[] body;

	// ==================================================constructor==================================================

	public DeliveryHandleParams(Channel channel, String consumerTag, Envelope envelope, BasicProperties properties,
			byte[] body) {
		this.channel = channel;
		this.consumerTag = consumerTag;
		this.envelope = envelope;
		this.properties = properties;
		this.body = body;
	}

	// ==================================================功能方法==================================================

	/**
	 * 答复客户端。如果客户端没有指定需要答复，方法将不会做答复处理
	 * 
	 * @date 2021年4月20日 上午10:58:34
	 * @param body 答复的信息
	 */
	public void replyTo(byte[] body) throws IOException {
		String replName = properties.getReplyTo();
		if (null != replName) {
			BasicProperties replBP = new BasicProperties().builder().correlationId(properties.getCorrelationId())
					.build();
			channel.basicPublish("", replName, replBP, body);
		}
	}

	/**
	 * 基础消。消费端处理完后需要调用 此方法，否则消息处理后队列中的消息没有消除，下次程序再次运行可能会再次处理已经处理的消息
	 * 
	 * @date 2021年4月20日 上午10:57:36
	 */
	public void basicAck() throws IOException {
		channel.basicAck(envelope.getDeliveryTag(), false);
	}

	/**
	 * 关闭连接
	 * 
	 * @date 2021年4月20日 上午11:00:03
	 */
	public void close() throws IOException {
		channel.getConnection().close();
	}

	// ==================================================get==================================================

	public Channel getChannel() {
		return channel;
	}

	public String getConsumerTag() {
		return consumerTag;
	}

	public Envelope getEnvelope() {
		return envelope;
	}

	public BasicProperties getProperties() {
		return properties;
	}

	public byte[] getBody() {
		return body;
	}

}
