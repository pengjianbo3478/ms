package com.gl365.ms.ons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gl365.aliyun.ons.OnsListener;
import com.gl365.ms.dto.SmsResultDTO;
import com.gl365.ms.exception.SmsException;
import com.gl365.ms.model.SmsMessage;
import com.gl365.ms.service.SmsService;
import com.google.gson.Gson;

@Configuration
public class OnsListenerConfiguration {
	private static final Logger LOG = LoggerFactory.getLogger(OnsListenerConfiguration.class);
	@Autowired
	private SmsService<String> smsService;

	@Bean(name = "gl365-sms-consumer")
	public OnsListener onsListener() {
		OnsListener result = message -> {
			String msg = new String(message);
			LOG.debug("received message {}", msg);
			SmsMessage sms = new Gson().fromJson(msg, SmsMessage.class);
			try {
				SmsResultDTO sent = smsService.send(sms.getContent(), sms.getMobiles());
				LOG.debug("sent sms {}", sent.getRets());
			} catch (SmsException e) {
				LOG.error("handle sms message error.", e);
				throw new RuntimeException(e);
			}
		};
		return result;
	}

}
