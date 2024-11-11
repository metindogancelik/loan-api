package com.mdc.ing.credit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdc.ing.credit.model.entity.Customer;
import com.mdc.ing.credit.model.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

	Optional<List<Loan>> findLoanByCustomer(Customer customer);
	
}
