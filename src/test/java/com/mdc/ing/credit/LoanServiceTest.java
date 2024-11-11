package com.mdc.ing.credit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mdc.ing.credit.model.entity.Customer;
import com.mdc.ing.credit.model.entity.IngUser;
import com.mdc.ing.credit.model.entity.Loan;
import com.mdc.ing.credit.service.core.LoanCoreService;
import com.mdc.ing.credit.service.customer.CustomerService;
import com.mdc.ing.credit.service.loan.ListLoansService;
import com.mdc.ing.credit.service.user.IngUserService;

@SpringBootTest
public class LoanServiceTest {
	
	@MockBean
	IngUserService ingUserService;
	
	@MockBean
	CustomerService customerService;
	
	@MockBean
	LoanCoreService loanCoreService;
	
	@Autowired
	ListLoansService listLoansService;

	@Test
	public void testListInstallments() {
		when(ingUserService.getLoggedInUser()).thenReturn(IngUser.builder().id(1L).userName("ingUser").build());
		when(customerService.findCustomerOfUser(any())).thenReturn(new Customer());
		when(loanCoreService.getLoansOfCustomer(any())).thenReturn(Collections.singletonList(Loan.builder().id(2024L).build()));
		
		assertEquals(2024L, listLoansService.listLoans(null).getLoanList().get(0).getLoanId(), "The result should be 2024");
	}
}
