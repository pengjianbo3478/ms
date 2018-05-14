package com.gl365.ms.service.impl;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.gl365.ms.dto.SmsResultDTO;
import com.gl365.ms.dto.SmsResultItemDTO;
import com.gl365.ms.enums.Msg;
import com.gl365.ms.exception.SmsException;
import com.gl365.ms.service.SmsService;
@Service
public class SmsServiceImpl<T> implements SmsService<T> {
	private static final Logger LOG = LoggerFactory.getLogger(SmsServiceImpl.class);
	@Value("${sms.liandong.url}")
	private String url;
	@Value("${sms.liandong.spId}")
	private String spId;
	@Value("${sms.liandong.authCode}")
	private String authCode;
	@Value("${sms.mongate.url}")
	private String mongateUrl;
	@Value("${sms.mongate.username}")
	private String mongateUsername;
	@Value("${sms.mongate.password}")
	private String mongatePassword;
//	@Autowired
//	private RestTemplate restTemplate;

	@Override
	public SmsResultDTO send(String content, List<String> mobiles) throws SmsException {
		if (StringUtils.isEmpty(content))
			throw new SmsException(Msg.E001.getCode(), Msg.E001.getDesc());
		if (CollectionUtils.isEmpty(mobiles))
			throw new SmsException(Msg.E002.getCode(), Msg.E002.getDesc());
		
		String mobile = mobiles.toString().replaceAll("[\\[ \\]]", "");
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", "application/x-www-form-urlencoded");

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("userId", mongateUsername);
		map.add("password", mongatePassword);
		map.add("pszMobis", mobile);
		map.add("pszMsg", content);
		map.add("iMobiCount", "1");
		map.add("pszSubPort", "*");
		map.add("MsgId", "1");
		
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

		LOG.debug(">>>sending sms {} {}", mobile, content);
		String response = "IGNORED";
//		String response = new RestTemplate().postForObject(mongateUrl, request, String.class);
//		LOG.debug("<<<response => {}", response);

		SmsResultDTO result = new SmsResultDTO();
		SmsResultItemDTO smsResultItemDTO = new SmsResultItemDTO();
		smsResultItemDTO.setMobile(mobile);
		smsResultItemDTO.setRspcod(response);
		result.setRets(Arrays.asList(smsResultItemDTO));
		LOG.debug("result => {}", ToStringBuilder.reflectionToString(result));
		return result;
	}

//	@Override
//	public SmsResultDTO send(String content, List<String> mobiles) throws SmsException {
//		if (StringUtils.isEmpty(content))
//			throw new SmsException(Msg.E001.getCode(), Msg.E001.getDesc());
//		if (CollectionUtils.isEmpty(mobiles))
//			throw new SmsException(Msg.E002.getCode(), Msg.E002.getDesc());
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//		SmsLianDongDTO smsLianDongDTO = new SmsLianDongDTO();
//		smsLianDongDTO.setSpId(this.spId);
//		smsLianDongDTO.setAuthCode(this.authCode);
//		smsLianDongDTO.setContent(content);
//		smsLianDongDTO.setMobiles(mobiles);
//		LOG.debug("smsLianDongDTO={}", ToStringBuilder.reflectionToString(smsLianDongDTO));
//		Gson gson = new Gson();
//		String reqContent = gson.toJson(smsLianDongDTO);
//		LOG.debug("send body={}", reqContent);
//		HttpEntity<String> entity = new HttpEntity<String>(reqContent, headers);
//		String response = restTemplate.postForObject(this.url, entity, String.class);
//		LOG.debug("response={}", response);
//		if (StringUtils.isEmpty(response))
//			throw new SmsException(Msg.E003.getCode(), Msg.E003.getDesc());
//		TypeToken<SmsResultDTO> typeToken = new TypeToken<SmsResultDTO>() {
//		};
//		Type type = typeToken.getType();
//		SmsResultDTO smsResultDTO = gson.fromJson(response, type);
//		LOG.debug("smsResultDTO={}", ToStringBuilder.reflectionToString(smsResultDTO));
//		return smsResultDTO;
//	}
}
