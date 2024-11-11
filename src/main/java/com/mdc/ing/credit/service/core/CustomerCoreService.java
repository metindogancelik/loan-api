package com.mdc.ing.credit.service.core;

import org.springframework.stereotype.Service;

import com.mdc.ing.credit.model.entity.Customer;
import com.mdc.ing.credit.model.entity.IngUser;
import com.mdc.ing.credit.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerCoreService {

	private final CustomerRepository customerRepository;
	
	public Customer findCustomerByIngUser(IngUser ingUser) {
		return customerRepository.findByIngUser(ingUser).orElse(null);		
	}
	
	public Customer createOrUpdateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
}
