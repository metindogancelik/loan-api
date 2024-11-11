package com.mdc.ing.credit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdc.ing.credit.model.entity.IngUser;

@Repository
public interface IngUserRepository extends JpaRepository<IngUser, Long> {

	Optional<IngUser> findByUserName(String userName);
	
}
