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

import com.imp.dto.ImpClaimDto;
import com.imp.service.ImpClaimService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class ImpClaimController {

	@Autowired
	private ImpClaimService impClaimService;
	
	@Operation(summary = "To get all claim status.")
	@GetMapping(path = "/api/claimstatus", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllClaimStatus(){
		return new ResponseEntity<>(impClaimService.getAllClaimStatus(), HttpStatus.OK);
	}
	
	@Operation(summary = "To create claim.")
	@PostMapping(path = "/api/claims", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createClaim(@Valid @RequestBody ImpClaimDto impClaimDto){
		return new ResponseEntity<>(impClaimService.createClaim(impClaimDto), HttpStatus.CREATED);
	}
	
	@Operation(summary = "To get claim.")
	@GetMapping(path = "/api/claims/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getClaim(@PathVariable Long id){
		return new ResponseEntity<>(impClaimService.getClaim(id), HttpStatus.OK);
	}
	
	@Operation(summary = "To get all claims.")
	@GetMapping(path = "/api/claims", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllClaim(){
		return new ResponseEntity<>(impClaimService.getAllClaim(), HttpStatus.OK);
	}
	
	@Operation(summary = "To update claim.")
	@PutMapping(path = "/api/claims/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateClaim(@PathVariable Long id, @RequestBody ImpClaimDto impClaimDto){
		impClaimService.updateClaim(id, impClaimDto);
		return new ResponseEntity<>("Claim updated successfully", HttpStatus.OK);
	}
	
	@Operation(summary = "To delete claim.")
	@DeleteMapping(path = "/api/claims/{id}")
	public ResponseEntity<?> deleteClaim(@PathVariable Long id){
		impClaimService.deleteClaim(id);
		return new ResponseEntity<>("Claim deleted successfully", HttpStatus.OK);
	}
}
