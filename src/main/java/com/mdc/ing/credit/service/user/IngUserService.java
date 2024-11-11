package com.mdc.ing.credit.service.user;

import org.springframework.stereotype.Service;

import com.mdc.ing.credit.model.constants.LanguageResources;
import com.mdc.ing.credit.model.entity.IngUser;
import com.mdc.ing.credit.service.core.IngUserCoreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngUserService {

	private final IngUserCoreService ingUserCoreService;
	
	public String welcomeUser() {
		IngUser ingUser = ingUserCoreService.getLoggedInUser();
		
		if (ingUser == null) {
			ingUserCoreService.registerLoggedInUser();
		}
		
		return LanguageResources.GREETING_USER_MESSAGE;
	}
	
	public IngUser getLoggedInUser() {
		return ingUserCoreService.getLoggedInUser();
	}
}