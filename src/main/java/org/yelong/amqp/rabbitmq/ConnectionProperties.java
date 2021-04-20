/**
 * 
 */
package org.yelong.amqp.rabbitmq;

/**
 * rabbit MQ连接配置
 * 
 * @date 2021年3月22日下午2:55:54
 * @since 3.0.0
 */
public class ConnectionProperties {

	/**
	 * 地址
	 */
	private String host;

	/**
	 * 端口
	 */
	private Integer port;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	// ==================================================constructor==================================================

	public ConnectionProperties() {
	}

	public ConnectionProperties(String host, Integer port, String username, String password) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	// ==================================================get/set==================================================

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
