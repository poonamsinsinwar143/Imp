package com.imp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class ImpClaimDto {
	private Long claimNumber;
	private String description;
	
	@NotNull(message = "Claim date is required.")
	@JsonFormat(pattern="yyyy-MM-dd", shape = Shape.STRING)
	private LocalDate claimDate;
	
	@NotNull(message = "Claim amount is required.")
	private Double claimAmount;
	
	private Long policyNumber;
	
	private Integer claimStatusId;
	
	private String claimStatusName;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", shape = Shape.STRING)
	private LocalDateTime createdOn;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", shape = Shape.STRING)
	private LocalDateTime updatedOn;
}
