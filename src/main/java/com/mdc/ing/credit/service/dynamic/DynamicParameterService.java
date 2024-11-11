package com.mdc.ing.credit.service.dynamic;

import org.springframework.stereotype.Service;

import com.mdc.ing.credit.model.entity.DynamicParameter;
import com.mdc.ing.credit.service.core.DynamicParameterCoreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DynamicParameterService {

	private final DynamicParameterCoreService dynamicParameterCoreService;
	
	public String getDynamicParameter(String key) {
		DynamicParameter dynamicParameter = dynamicParameterCoreService.findDynamicParameter(key);
		return dynamicParameter == null ? null : dynamicParameter.getParameterValue();
	}
}
