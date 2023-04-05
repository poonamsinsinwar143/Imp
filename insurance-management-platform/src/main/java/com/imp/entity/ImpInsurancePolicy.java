package com.imp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "imp_insurance_policy")
@Entity
@Data
public class ImpInsurancePolicy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long policyNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "policy_type")
	private ImpPolicyTypeMaster policyType;       
	
	private Double coverageAmount;
	private Double premium;
	private LocalDate startDate;
	private LocalDate endDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client")
	private ImpClient client;                    
	
	@OneToMany(mappedBy="policy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ImpClaim> claims;
	
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
