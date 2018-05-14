package com.gl365.ms.model;

import java.util.List;

public class SmsMessage {
	private String content;
	private List<String> mobiles;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getMobiles() {
		return mobiles;
	}
	public void setMobiles(List<String> mobiles) {
		this.mobiles = mobiles;
	}

}
