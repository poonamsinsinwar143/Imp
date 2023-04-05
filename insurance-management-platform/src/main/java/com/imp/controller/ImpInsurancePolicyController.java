package com.imp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imp.dto.ImpInsurancePolicyDto;
import com.imp.service.ImpInsurancePolicyService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class ImpInsurancePolicyController {

	@Autowired
	private ImpInsurancePolicyService impInsurancePolicyService;

	@Operation(summary = "Get all policy types")
	@GetMapping(path = "/api/policytypes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPolicyTypes() {
		return new ResponseEntity<>(impInsurancePolicyService.getPolicyTypes(), HttpStatus.OK);
	}

	@Operation(summary = "To create insurance policy")
	@PostMapping(path = "/api/policies", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createInsurancePolicy(@Valid @RequestBody ImpInsurancePolicyDto impInsurancePolicyDto) {
		Long policyNumber = impInsurancePolicyService.createInsurancePolicy(impInsurancePolicyDto);
		return new ResponseEntity<>(policyNumber, HttpStatus.CREATED);
	}

	@Operation(summary = "To Get insurance policy")
	@GetMapping(path = "/api/policies/{policyNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getInsurancePolicy(@PathVariable Long policyNumber) {
		return new ResponseEntity<>(impInsurancePolicyService.getInsurancePolicy(policyNumber), HttpStatus.OK);
	}

	@Operation(summary = "To Get all insurance policies")
	@GetMapping(path = "/api/policies", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllInsurancePolicy() {
		return new ResponseEntity<>(impInsurancePolicyService.getAllInsurancePolicy(), HttpStatus.OK);
	}
	
	@Operation(summary = "To update insurance policy")
	@PutMapping(path = "/api/policies/{policyNumber}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateInsurancePolicy(@RequestBody ImpInsurancePolicyDto impInsurancePolicyDto,@PathVariable Long policyNumber){
		impInsurancePolicyService.updateInsurancePolicy(impInsurancePolicyDto,policyNumber);
		return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
	}

	@Operation(summary = "To delete insurance policy")
	@DeleteMapping(path = "/api/policies/{policyNumber}")
	public ResponseEntity<?> deleteInsurancePolicy(@PathVariable Long policyNumber){
		impInsurancePolicyService.deleteInsurancePolicy(policyNumber);
		return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
	}
}
