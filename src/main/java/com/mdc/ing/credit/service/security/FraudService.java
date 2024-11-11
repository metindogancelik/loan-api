package com.mdc.ing.credit.service.security;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mdc.ing.credit.model.entity.IngUser;
import com.mdc.ing.credit.model.exception.IngFraudException;

@Service
public class FraudService {
	
	public void fraudCheck(IngUser ingUser) {
		if (ingUser == null || !StringUtils.hasText(ingUser.getUserName())) {
			throw new IngFraudException();
		}
	}
}
