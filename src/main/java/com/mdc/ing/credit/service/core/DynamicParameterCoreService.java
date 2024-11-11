package com.mdc.ing.credit.service.core;

import org.springframework.stereotype.Service;

import com.mdc.ing.credit.model.entity.DynamicParameter;
import com.mdc.ing.credit.repository.DynamicParameterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DynamicParameterCoreService {

	private final DynamicParameterRepository dynamicParameterRepository;
	
	public DynamicParameter findDynamicParameter(String key) {
		return dynamicParameterRepository.findById(key).orElseGet(null);
	}
}
