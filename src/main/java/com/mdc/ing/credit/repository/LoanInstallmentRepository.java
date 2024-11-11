package com.mdc.ing.credit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdc.ing.credit.model.entity.Loan;
import com.mdc.ing.credit.model.entity.LoanInstallment;

@Repository
public interface LoanInstallmentRepository extends JpaRepository<LoanInstallment, Long> {
	
	Optional<List<LoanInstallment>> findAllByLoan(Loan loan);
	
}
