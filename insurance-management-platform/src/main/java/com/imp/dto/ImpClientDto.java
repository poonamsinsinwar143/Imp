package com.imp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.imp.entity.ImpInsurancePolicy;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class ImpClientDto {
	private Long id;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotNull(message = "DOB is required")
	@JsonFormat(pattern="yyyy-MM-dd", shape = Shape.STRING)
	private LocalDate dob;
	
	private String address;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "Phone is required")
	private String phone;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", shape = Shape.STRING)
	private LocalDateTime createdOn;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", shape = Shape.STRING)
	private LocalDateTime updatedOn;
	private List<ImpInsurancePolicy> policies;
}
