package com.gl365.ms.enums;

public enum Msg {
	SUCCESS("00", "交易成功"), FAILED("99", "交易失败"), E001("001", "短信内容为空"), E002("002", "手机号码为空"),E003("003", "调用短信接口失败");

	private final String code;
	private final String desc;

	private Msg(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public String getCode() {
		return code;
	}
}
