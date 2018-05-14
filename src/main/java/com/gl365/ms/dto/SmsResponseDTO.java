package com.gl365.ms.dto;
import java.io.Serializable;
public class SmsResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String desc;
	private Serializable result;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Serializable result) {
		this.result = result;
	}
}
