package com.gl365.ms.service;
import java.util.List;
import com.gl365.ms.dto.SmsResultDTO;
import com.gl365.ms.exception.SmsException;
@FunctionalInterface
public interface SmsService<T> {
	SmsResultDTO send(String content, List<String> mobiles) throws SmsException;
}
