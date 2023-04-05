package com.imp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
public class ImpInsurancePolicyDto {
	private Long policyNumber;
	
	@NotNull(message = "Policy type id is required")
	private Integer policyTypeId;
	
	private String policyTypeName;  
	
	@NotNull(message = "Coverage amount is required")
	private Double coverageAmount;
	
	@NotNull(message = "Premium is required")
	private Double premium;
	
	@JsonFormat(pattern="yyyy-MM-dd", shape = Shape.STRING)
	private LocalDate startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd", shape = Shape.STRING)
	private LocalDate endDate;
	
	@NotNull(message = "cliend id is required")
	private Long clientId;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", shape = Shape.STRING)
	private LocalDateTime createdOn;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", shape = Shape.STRING)
	private LocalDateTime updatedOn;
}
