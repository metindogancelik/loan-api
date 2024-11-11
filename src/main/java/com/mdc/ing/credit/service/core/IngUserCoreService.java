package com.mdc.ing.credit.service.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mdc.ing.credit.model.entity.IngUser;
import com.mdc.ing.credit.repository.IngUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngUserCoreService {

	private final IngUserRepository ingUserRepository;
	
	private String getLoggedInUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
        	return authentication.getName();
        }
        
        return null;
    }
	
	public IngUser getLoggedInUser() {
		return ingUserRepository.findByUserName(getLoggedInUserName()).orElse(null);
	}
	
	public IngUser registerLoggedInUser() {
		IngUser user = IngUser
				.builder()
				.userName(getLoggedInUserName())
				.build();
		
		return ingUserRepository.save(user);
	}
}
