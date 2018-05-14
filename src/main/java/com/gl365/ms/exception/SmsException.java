package com.gl365.ms.exception;

public class SmsException extends Exception {

	private static final long serialVersionUID = 1L;
	private String code;
	private String desc;
	private String info;

	public SmsException() {
		super();
	}

	public SmsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SmsException(String message, Throwable cause) {
		super(message, cause);
	}

	public SmsException(String code, String desc, Throwable cause) {
		super("code=" + code + "," + "desc=" + desc, cause);
		this.code = code;
		this.desc = desc;
	}

	public SmsException(String code, String desc, String info, Throwable cause) {
		super("code=" + code + "," + "desc=" + desc, cause);
		this.code = code;
		this.desc = desc;
		this.info = info;
	}

	public SmsException(String message) {
		super(message);
	}

	public SmsException(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public SmsException(Throwable cause) {
		super(cause);
	}

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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
