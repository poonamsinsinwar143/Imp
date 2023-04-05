package com.imp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "imp_claim")
@Entity
@Data
public class ImpClaim {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long claimNumber;
	
	private String description;
	private LocalDate claimDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "policy")
	private ImpInsurancePolicy policy;
	
	private Double claimAmount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "claim_status")
	private ImpClaimStatusMaster claimStatus;
	
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
