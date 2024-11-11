package com.mdc.ing.credit.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan/admin")
@PreAuthorize("hasRole('ADMIN')") 
public class LoanAdminController {

	@GetMapping("/closeLoan")
	public String closeLoan() {
		return "Services can be called here user independent.";
	}
}
