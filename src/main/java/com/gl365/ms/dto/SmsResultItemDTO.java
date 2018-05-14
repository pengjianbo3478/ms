package com.gl365.ms.dto;
import java.io.Serializable;
public class SmsResultItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String mobile;
	private String msgId;
	private String rspcod;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getRspcod() {
		return rspcod;
	}

	public void setRspcod(String rspcod) {
		this.rspcod = rspcod;
	}
}
