package com.gl365.ms.web;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gl365.ms.dto.SmsResponseDTO;
import com.gl365.ms.dto.SmsResultDTO;
import com.gl365.ms.enums.Msg;
import com.gl365.ms.exception.SmsException;
import com.gl365.ms.service.SmsService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@RestController
public class SmsController {
	private static final Logger LOG = LoggerFactory.getLogger(SmsController.class);
	@Autowired
	private SmsService<?> smsService;

	@PostMapping("/send")
	@HystrixCommand(fallbackMethod = "fallback")
	public SmsResponseDTO send(@RequestParam String content, @RequestParam List<String> mobiles) throws SmsException {
		LOG.debug("content={},mobiles={}", content, mobiles);
		SmsResultDTO smsResultDTO = this.smsService.send(content, mobiles);
		SmsResponseDTO smsResponesDTO = new SmsResponseDTO();
		smsResponesDTO.setCode(Msg.SUCCESS.getCode());
		smsResponesDTO.setDesc(Msg.SUCCESS.getDesc());
		smsResponesDTO.setResult(smsResultDTO);
		return smsResponesDTO;
	}

	public SmsResponseDTO fallback(@RequestParam String content, @RequestParam List<String> mobiles) throws SmsException {
		return null;
	}
}
