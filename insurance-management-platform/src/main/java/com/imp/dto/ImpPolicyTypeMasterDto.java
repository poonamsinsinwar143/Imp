package com.imp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class ImpPolicyTypeMasterDto {
	private Integer id;

	private String name;

	private String description;
}
