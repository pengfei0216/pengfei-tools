package org.yelong.amqp.rabbitmq.consumer.delivery;

import java.io.IOException;

/**
 * 交货信息处理器
 * 
 * @date 2021年4月20日 上午10:04:25
 * @since 3.0.0
 */
@FunctionalInterface
public interface DeliveryHandler {

	/**
	 * 处理交货信息。处理完之后需要关闭连接
	 * 
	 * @date 2021年4月20日 上午10:05:11
	 * @param deliveryHandleParams 交互信息处理参数
	 * @throws IOException 流异常
	 */
	void handleDelivery(DeliveryHandleParams deliveryHandleParams) throws IOException;

}
