package com.gl365.ms.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SmsResultDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<SmsResultItemDTO> rets = new ArrayList<SmsResultItemDTO>();

	public List<SmsResultItemDTO> getRets() {
		return rets;
	}

	public void setRets(List<SmsResultItemDTO> rets) {
		this.rets = rets;
	}

}
