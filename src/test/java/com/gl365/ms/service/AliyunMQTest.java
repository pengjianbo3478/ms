package com.gl365.ms.service;

import java.util.Properties;
import org.joda.time.LocalDateTime;
import org.junit.Ignore;
import org.junit.Test;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
@Ignore
public class AliyunMQTest {
//	@Value("${gl365.aliyun.ons.accessKeyId}")
//	private String accessKeyId;
//	@Value("${gl365.aliyun.ons.accessKeySecret}")
//	private String accessKeySecret;
//	@Value("${gl365.aliyun.ons.onsAddr}")
//	private String onsAddr;
//	@Value("${gl365.aliyun.ons.producerId}")
//	private String producerId;
//	@Value("${gl365.aliyun.ons.topic}")
//	private String topic;
//	@Value("${gl365.aliyun.ons.key}")
//	private String key;
//	@Value("${gl365.aliyun.ons.tag}")
//	private String tag;

	private String accessKeyId = "LTAIYPgAUtdQZhl0";
	private String onsAddr = "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet";
	private String accessKeySecret = "lzZ1kUS4hXGIAhRt32PDvWs2xpYR86";
	private String producerId = "PID-gl365-dev-sms";
	private String topic = "gl365-dev-sms";
	private String key = "GL365_DEV_SMS";
	private String tag = "JUNIT";

	@Test
	public void test() {
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.ProducerId, producerId);
		properties.put(PropertyKeyConst.AccessKey, accessKeyId);
		properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
		// 公有云生产环境：http://onsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
		// 公有云公测环境：http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet
		// 杭州金融云环境：http://jbponsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
		// 杭州深圳云环境：http://mq4finance-sz.addr.aliyun.com:8080/rocketmq/nsaddr4client-internal
		properties.put(PropertyKeyConst.ONSAddr, onsAddr);// 此处以公有云生产环境为例
		Producer producer = ONSFactory.createProducer(properties);

		// 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
		producer.start();
		String now = LocalDateTime.now().toString("yyyy-MM-dd HH:mm:ss");
		now = String.format("{\"content\":\"%s\",\"mobiles\": [\"13798436966\"]}", now);
		System.out.println(now);
		Message msg = new Message(
				// Message Topic
				topic,
				// Message Tag,
				// 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤
				tag,
				// Message Body
				// 任何二进制形式的数据，ONS不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方式
				now.getBytes());

		// 设置代表消息的业务关键属性，请尽可能全局唯一。
		// 以方便您在无法正常收到消息情况下，可通过ONS Console查询消息并补发。
		// 注意：不设置也不会影响消息正常收发
		msg.setKey(key);

		// 发送消息，只要不抛异常就是成功
		SendResult sendResult = producer.send(msg);
		System.out.println(sendResult);

		// 在应用退出前，销毁Producer对象
		// 注意：如果不销毁也没有问题
		producer.shutdown();
	}

}
