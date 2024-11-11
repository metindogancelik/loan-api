package com.mdc.ing.credit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdc.ing.credit.model.entity.DynamicParameter;

@Repository
public interface DynamicParameterRepository extends JpaRepository<DynamicParameter, String> {
	
}
