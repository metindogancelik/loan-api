package com.mdc.ing.credit.service.customer;

import org.springframework.stereotype.Service;

import com.mdc.ing.credit.model.entity.Customer;
import com.mdc.ing.credit.model.entity.IngUser;
import com.mdc.ing.credit.model.input.CreateCustomerInput;
import com.mdc.ing.credit.service.core.CustomerCoreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	
	private final CustomerCoreService customerCoreService;
	
	public Customer findCustomerOfUser(IngUser ingUser) {
		return customerCoreService.findCustomerByIngUser(ingUser);
	}
	
	public Customer createCustomer(CreateCustomerInput createCustomerInput) {
		
		Customer customer = Customer
				.builder()
				.ingUser(createCustomerInput.getIngUser())
				.name(createCustomerInput.getName())
				.surname(createCustomerInput.getSurname())
				.creditLimit(createCustomerInput.getCreditLimit())
				.usedCreditLimit(createCustomerInput.getUsedCreditLimit())
				.build();
		
		return customerCoreService.createOrUpdateCustomer(customer);
	}
	
	public Customer updateCustomer(Customer customer) {		
		return customerCoreService.createOrUpdateCustomer(customer);
	}
}
