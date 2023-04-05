package com.imp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.imp.dto.ImpClientDto;
import com.imp.entity.ImpClient;
import com.imp.entity.repository.ImpClientRepository;
import com.imp.exception.ImpBusinessException;

@Service
public class ImpClientService {
	private static final Logger LOG = LoggerFactory.getLogger(ImpClientService.class);

	@Autowired
	private ImpClientRepository impClientRepository;

	public Long createClient(ImpClientDto impClientDto) {

		Optional<ImpClient> optImpClient = impClientRepository.findByEmail(impClientDto.getEmail());
		if (optImpClient.isPresent()) {
			LOG.error("Email already exists:{}", impClientDto.getEmail());
			throw new ImpBusinessException("Email already exists!", HttpStatus.BAD_REQUEST);
		}

		optImpClient = impClientRepository.findByPhone(impClientDto.getPhone());

		if (optImpClient.isPresent()) {
			LOG.error("Phone already exists:{}", impClientDto.getPhone());
			throw new ImpBusinessException("Phone already exists!", HttpStatus.BAD_REQUEST);
		}

		ImpClient impClient = mapImpClient(impClientDto);
		impClient = impClientRepository.save(impClient);
		return impClient.getId();
	}

	private ImpClient mapImpClient(ImpClientDto impClientDto) {
		ImpClient impClient = new ImpClient();
		impClient.setName(impClientDto.getName());
		impClient.setDob(impClientDto.getDob());
		impClient.setAddress(impClientDto.getAddress());
		impClient.setCity(impClientDto.getCity());
		impClient.setState(impClientDto.getState());
		impClient.setCountry(impClientDto.getCountry());
		impClient.setZipcode(impClientDto.getZipcode());
		impClient.setEmail(impClientDto.getEmail());
		impClient.setPhone(impClientDto.getPhone());
		impClient.setCreatedOn(LocalDateTime.now());
		return impClient;
	}

	public ImpClientDto getClient(Long clientId) {
		LOG.info("getClient method called for id:{}", clientId);
		Optional<ImpClient> optClient = impClientRepository.findById(clientId);
		ImpClientDto impClientDto = null;
		if (optClient.isPresent()) {
			impClientDto = mapImpClientDto(optClient.get());
		} else {
			LOG.error("Client not found.");
			throw new ImpBusinessException("Client not found.", HttpStatus.NOT_FOUND);
		}
		return impClientDto;
	}

	private ImpClientDto mapImpClientDto(ImpClient impClient) {
		ImpClientDto impClientDto = new ImpClientDto();
		impClientDto.setId(impClient.getId());
		impClientDto.setName(impClient.getName());
		impClientDto.setDob(impClient.getDob());
		impClientDto.setAddress(impClient.getAddress());
		impClientDto.setCity(impClient.getCity());
		impClientDto.setState(impClient.getState());
		impClientDto.setCountry(impClient.getCountry());
		impClientDto.setEmail(impClient.getEmail());
		impClientDto.setPhone(impClient.getPhone());
		impClientDto.setZipcode(impClient.getZipcode());
		return impClientDto;
	}

	public List<ImpClientDto> getAllClient() {
		LOG.info("getAllClient method started.");
		List<ImpClient> clients = impClientRepository.findAll();
		if (clients.isEmpty()) {
			LOG.error("No client found");
			throw new ImpBusinessException("No client found.", HttpStatus.NOT_FOUND);

		}
		List<ImpClientDto> impClientDtos = new ArrayList<>();
		for (ImpClient client : clients) {
			impClientDtos.add(mapImpClientDto(client));
		}
		return impClientDtos;
	}

	public void deleteClient(Long clientId) {
		LOG.info("deleteClient method called for id:{}", clientId);
		Optional<ImpClient> optClient = impClientRepository.findById(clientId);
		if (optClient.isPresent()) {
			impClientRepository.deleteById(clientId);
		} else {
			LOG.error("Client not found.");
			throw new ImpBusinessException("Client not found.", HttpStatus.NOT_FOUND);
		}
	}

	public void updateClient(ImpClientDto impClientDto, Long clientId) {
		LOG.info("updateClient method started.");
		Optional<ImpClient> optClient = impClientRepository.findById(clientId);
		if (!optClient.isPresent()) {
			LOG.error("Client not found for Id:{}", clientId);
			throw new ImpBusinessException("Claim not found.", HttpStatus.NOT_FOUND);
		}

		ImpClient client = optClient.get();
		if (impClientDto.getName() != null) {
			client.setName(impClientDto.getName());
		}
		if (impClientDto.getDob() != null) {
			client.setDob(impClientDto.getDob());
		}
		if (impClientDto.getAddress() != null) {
			client.setAddress(impClientDto.getAddress());
		}
		if (impClientDto.getCity() != null) {
			client.setCity(impClientDto.getCity());
		}
		if (impClientDto.getState() != null) {
			client.setState(impClientDto.getState());
		}
		if (impClientDto.getCountry() != null) {
			client.setCountry(impClientDto.getCountry());
		}
		if (impClientDto.getEmail() != null) {
			Optional<ImpClient> optImpClient = impClientRepository.findByEmail(impClientDto.getEmail());
			if (optImpClient.isPresent()) {
				LOG.error("Email already exists:{}", impClientDto.getEmail());
				throw new ImpBusinessException("Email already exists!", HttpStatus.BAD_REQUEST);
			}
			client.setEmail(impClientDto.getEmail());
		}

		if (impClientDto.getPhone() != null) {
			Optional<ImpClient> optImpClient = impClientRepository.findByPhone(impClientDto.getPhone());
			if (optImpClient.isPresent()) {
				LOG.error("Phone already exists:{}", impClientDto.getPhone());
				throw new ImpBusinessException("Phone already exists!", HttpStatus.BAD_REQUEST);
			}
			client.setPhone(impClientDto.getPhone());
		}

		if (impClientDto.getZipcode() != null) {
			client.setZipcode(impClientDto.getZipcode());
		}
		client.setUpdatedOn(LocalDateTime.now());
		impClientRepository.save(client);
	}

}
