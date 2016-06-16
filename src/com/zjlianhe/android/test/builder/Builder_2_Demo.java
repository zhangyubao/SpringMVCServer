package com.zjlianhe.android.test.builder;

import java.sql.Date;

/**
 * 实际应用建造者模式示例代码
 * 
 * 
 * 使用场景 　　
 * 
 * 假设有一个电子杂志系统，定期地向用户的电子邮件信箱发送电子杂志。用户可以通过网页订阅电子杂志，也可以通过网页结束订阅。当客户开始订阅时，
 * 系统发送一个电子邮件表示欢迎，当客户结束订阅时，系统发送一个电子邮件表示欢送。本例子就是这个系统负责发送“欢迎”和“欢送”邮件的模块。
 * 
 * NOTE：虽然在这个例子里面各个产品类均有一个共同的接口，但这仅仅是本例子特有的，并不代表建造模式的特点。建造模式可以应用到具有完全不同接口的产品类上。
 * 大多数情况下是不知道最终构建出来的产品是什么样的
 * ，所以在标准的建造模式里面，一般是不需要对产品定义抽象接口的，因为最终构造的产品千差万别，给这些产品定义公共接口几乎是没有意义的。
 * 
 * @author zhangYB
 *
 */
public class Builder_2_Demo {

	/**************************************** 产品类 ************************************/
	/**
	 * 产品类
	 * 
	 * 一般是不需要对产品定义抽象接口的，因为最终构造的产品千差万别，给这些产品定义公共接口几乎是没有意义的
	 * 
	 * @author zhangYB
	 *
	 */
	public abstract class AutoMessage {
		// 收件人地址
		private String to;
		// 发件人地址
		private String from;
		// 邮件内容
		private String body;
		// 右键标题
		private String subject;
		// 发送日期
		private Date sendDate;

		/**
		 * 发送邮件
		 */
		public void send() {
			System.out.println("发送邮件");
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public Date getSendDate() {
			return sendDate;
		}

		public void setSendDate(Date sendDate) {
			this.sendDate = sendDate;
		}

	}

	/**
	 * 具体产品类
	 * 
	 * @author zhangYB
	 *
	 */
	public class WelcomeMessage extends AutoMessage {
		public WelcomeMessage() {
			System.out.println("发送欢迎邮件");
		}
	}

	/**
	 * 具体产品类
	 * 
	 * @author zhangYB
	 *
	 */
	public class GoodByeMessage extends AutoMessage {
		public GoodByeMessage() {
			System.out.println("发送欢送邮件");
		}
	}

	/**************************************** 建造者类 ************************************/

	public abstract class Builder {

		private AutoMessage msg;

		// 构建标题
		public abstract void buildSubject();

		// 构建内容
		public abstract void buildBody();

		public void buildTo(String to) {
			msg.setTo(to);
		}

		public void buildFrom(String from) {
			msg.setFrom(from);
		}

		public void buildDate() {
			msg.setSendDate(new Date(System.currentTimeMillis()));
		}

		public void senMessage() {
			msg.send();
		}
	}

	/**
	 * 具体的建造者
	 * 
	 * @author zhangYB
	 *
	 */
	public class WelcomeBuilder extends Builder {

		private WelcomeMessage msg;

		public WelcomeBuilder() {
			msg = new WelcomeMessage();
		}

		@Override
		public void buildSubject() {
			msg.setSubject("欢迎邮件");

		}

		@Override
		public void buildBody() {
			msg.setBody("欢迎！");

		}

	}

	/**
	 * 具体的构建者
	 * 
	 * @author zhangYB
	 *
	 */
	public class GoodByeBuilder extends Builder {

		private GoodByeMessage msg;

		public GoodByeBuilder() {
			msg = new GoodByeMessage();
		}

		@Override
		public void buildSubject() {
			msg.setSubject("欢送邮件");

		}

		@Override
		public void buildBody() {
			msg.setBody("欢送！");
		}

	}

	/**************************************** 导演类 ************************************/
	public class Director {
		private Builder builder;

		public Director(Builder builder) {
			this.builder = builder;
		}

		public void build(String from, String to) {
			builder.buildFrom(from);
			builder.buildTo(to);
			builder.buildBody();
			builder.buildSubject();
			builder.buildDate();
			builder.senMessage();
		}

	}

	/**
	 * 客户端
	 */
	public void Client() {
		Builder builder = new WelcomeBuilder();
		Director director = new Director(builder);
		director.build("", "");
	}
}
