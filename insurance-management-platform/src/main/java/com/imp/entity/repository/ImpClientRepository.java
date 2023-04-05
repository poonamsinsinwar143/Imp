package com.imp.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imp.entity.ImpClient;

public interface ImpClientRepository extends JpaRepository<ImpClient, Long>{

	Optional<ImpClient> findByEmail(String email);
	
	Optional<ImpClient> findByPhone(String phone);
}
