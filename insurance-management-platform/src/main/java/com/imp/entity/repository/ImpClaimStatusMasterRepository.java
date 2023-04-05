package com.imp.entity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imp.entity.ImpClaimStatusMaster;

public interface ImpClaimStatusMasterRepository extends JpaRepository<ImpClaimStatusMaster, Integer>{

	Optional<ImpClaimStatusMaster> findByName(String string);

}
