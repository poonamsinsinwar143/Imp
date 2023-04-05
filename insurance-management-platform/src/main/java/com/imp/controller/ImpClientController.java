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

import com.imp.dto.ImpClientDto;
import com.imp.service.ImpClientService;

import io.swagger.v3.oas.annotations.Operation;

@RestController

public class ImpClientController {
	
	@Autowired
	private ImpClientService impClientService;

	@Operation(summary = "To create client.")
	@PostMapping(path = "/api/clients", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createClient(@Valid @RequestBody ImpClientDto impClientDto){
		Long clientId = impClientService.createClient(impClientDto);
		return new ResponseEntity<>(clientId, HttpStatus.CREATED);
	}
	
	@Operation(summary = "To get client.")
	@GetMapping(path = "/api/clients/{clientId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getClient(@PathVariable Long clientId){
		return new ResponseEntity<>(impClientService.getClient(clientId),HttpStatus.OK);
	}
	
	@Operation(summary = "To get all clients.")
	@GetMapping(path = "/api/clients",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllClient(){
		return new ResponseEntity<>(impClientService.getAllClient(),HttpStatus.OK);
	}
	
	@Operation(summary = "To delete client.")
	@DeleteMapping(path = "/api/clients/{clientId}")
	public ResponseEntity<?> deleteClient(@PathVariable Long clientId){
		impClientService.deleteClient(clientId);
		return new ResponseEntity<>("Client deleted successfully",HttpStatus.OK);
	}
	
	@Operation(summary = "To update client.")
	@PutMapping(path = "/api/clients/{clientId}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateClient(@RequestBody ImpClientDto impClientDto, @PathVariable Long clientId){
		impClientService.updateClient(impClientDto,clientId);
		return new ResponseEntity<>("Client updated successfully",HttpStatus.OK);
	}
	
}
