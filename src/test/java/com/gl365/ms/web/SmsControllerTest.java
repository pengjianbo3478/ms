package com.gl365.ms.web;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.gl365.ms.dto.SmsLianDongDTO;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SmsControllerTest {
	private static final Logger LOG = LoggerFactory.getLogger(SmsControllerTest.class);
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private RestTemplate restTemplate;

	@Test
	@Ignore
	public void send() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/send");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("content", "test");
		params.add("mobiles", "{13760447366,13760447365}");
		builder.params(params);
		builder.accept(MediaType.ALL);
		ResultActions resultActions = this.mockMvc.perform(builder);
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
		resultActions.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@Ignore
	public void test3() {
		String url = "http://114.113.159.224:8099/umpp/mt";
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = MediaType.parseMediaType("application/json; charset=GBK");
		headers.setContentType(mediaType);
		SmsLianDongDTO smsBean = new SmsLianDongDTO();
		smsBean.setSpId("test55");
		smsBean.setAuthCode("ump123");
		List<String> mobiles = new ArrayList<String>();
		mobiles.add("13760447366");
		smsBean.setMobiles(mobiles);
		smsBean.setContent("test111");
		JSONObject requestJson = new JSONObject(smsBean);
		HttpEntity<String> entity = new HttpEntity<String>(requestJson.toString(), headers);
		String result = restTemplate.postForObject(url, entity, String.class);
		LOG.debug("result={}", result);
	}

	@Test
	@Ignore
	public void authenticate() {
		String url = "http://192.168.0.12:18080/rawauth/{id}/{encrypt}";
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
		form.add("id", "rayn");
		form.add("encrypt", "rayn");
		String result = this.restTemplate.postForObject(url, null, String.class, form);
		LOG.debug("result={}", result);
	}
}
