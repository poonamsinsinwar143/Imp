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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "imp_client")
@Entity
@Data
public class ImpClient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private LocalDate dob;
	private String address;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private String email;
	private String phone;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	
	@OneToMany(mappedBy="client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ImpInsurancePolicy> policies;                                  
}
