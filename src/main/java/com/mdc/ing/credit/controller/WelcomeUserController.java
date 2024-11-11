package com.mdc.ing.credit.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdc.ing.credit.service.user.IngUserService;

import lombok.RequiredArgsConstructor;

@RestController
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class WelcomeUserController {
	
	private final IngUserService ingUserService;

	@GetMapping("/")
	public String welcome() {
		return ingUserService.welcomeUser();
	}
}
