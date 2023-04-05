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

import com.imp.dto.ImpClaimDto;
import com.imp.dto.ImpClaimStatusMasterDto;
import com.imp.entity.ImpClaim;
import com.imp.entity.ImpClaimStatusMaster;
import com.imp.entity.ImpInsurancePolicy;
import com.imp.entity.repository.ImpClaimRepository;
import com.imp.entity.repository.ImpClaimStatusMasterRepository;
import com.imp.entity.repository.ImpInsurancePolicyRepository;
import com.imp.exception.ImpBusinessException;

@Service
public class ImpClaimService {
	private static final Logger LOG = LoggerFactory.getLogger(ImpClaimService.class);

	@Autowired
	private ImpClaimRepository impClaimRepository;

	@Autowired
	private ImpInsurancePolicyRepository impInsurancePolicyRepository;

	@Autowired
	private ImpClaimStatusMasterRepository impClaimStatusMasterRepository;

	private static final String DEFAULT_CLAIM_STATUS = "PENDING";

	public Long createClaim(ImpClaimDto impClaimDto) {
		LOG.info("createClaim method started.");
		ImpClaim impClaim = mapToImpClaim(impClaimDto);
		impClaim = impClaimRepository.save(impClaim);
		return impClaim.getClaimNumber();
	}

	public ImpClaimDto getClaim(Long id) {
		LOG.info("getClaim method called for id:{}", id);
		ImpClaimDto impClaimDto = null;
		Optional<ImpClaim> optClaim = impClaimRepository.findById(id);
		if (optClaim.isPresent()) {
			impClaimDto = mapToImpClaimDto(optClaim.get());
		} else {
			LOG.error("Claim not found, claimId:{}", id);
			throw new ImpBusinessException("Claim not found.", HttpStatus.NOT_FOUND);
		}
		return impClaimDto;
	}

	public List<ImpClaimDto> getAllClaim() {
		LOG.info("getAllClaim method started.");
		List<ImpClaimDto> impClaimDtos = new ArrayList<>();
		List<ImpClaim> impClaims = impClaimRepository.findAll();
		if (impClaims.isEmpty()) {
			LOG.error("No claim found.");
			throw new ImpBusinessException("No claim found.", HttpStatus.NOT_FOUND);
		}
		for (ImpClaim impClaim : impClaims) {
			ImpClaimDto impClaimDto = mapToImpClaimDto(impClaim);
			impClaimDtos.add(impClaimDto);
		}
		return impClaimDtos;
	}

	public void deleteClaim(Long id) {
		LOG.info("deleteClaim method called for id:{}", id);
		Optional<ImpClaim> optClaim = impClaimRepository.findById(id);
		if (optClaim.isPresent()) {
			impClaimRepository.deleteById(id);
		} else {
			LOG.error("Claim not found");
			throw new ImpBusinessException("Claim not found", HttpStatus.NOT_FOUND);
		}
	}

	public void updateClaim(Long id, ImpClaimDto impClaimDto) {
		LOG.info("updateClaim method started.");
		Optional<ImpClaim> optClaim = impClaimRepository.findById(id);
		if (!optClaim.isPresent()) {
			LOG.error("Claim not found, claimId:{}", id);
			throw new ImpBusinessException("Claim not found.", HttpStatus.NOT_FOUND);
		}
		
		ImpClaim impClaim = optClaim.get();
		if (impClaimDto.getClaimAmount() != null) {
			impClaim.setClaimAmount(impClaimDto.getClaimAmount());
		}

		if (impClaimDto.getClaimDate() != null) {
			impClaim.setClaimDate(impClaimDto.getClaimDate());
		}

		if (impClaimDto.getClaimStatusId() != null) {
			Optional<ImpClaimStatusMaster> optClaimStatus = impClaimStatusMasterRepository
					.findById(impClaimDto.getClaimStatusId());
			if (optClaimStatus.isPresent()) {
				impClaim.setClaimStatus(optClaimStatus.get());
			}
		}

		if (impClaimDto.getDescription() != null) {
			impClaim.setDescription(impClaimDto.getDescription());
		}

		if (impClaimDto.getPolicyNumber() != null) {
			Optional<ImpInsurancePolicy> optPolicy = impInsurancePolicyRepository
					.findById(impClaimDto.getPolicyNumber());
			if (optPolicy.isPresent()) {
				impClaim.setPolicy(optPolicy.get());
			}
		}
		impClaim.setUpdatedOn(LocalDateTime.now());
		impClaimRepository.save(impClaim);
	}

	private ImpClaimDto mapToImpClaimDto(ImpClaim impClaim) {

		ImpClaimDto impClaimDto = new ImpClaimDto();

		impClaimDto.setClaimNumber(impClaim.getClaimNumber());
		impClaimDto.setClaimAmount(impClaim.getClaimAmount());
		impClaimDto.setClaimDate(impClaim.getClaimDate());
		impClaimDto.setCreatedOn(impClaim.getCreatedOn());
		impClaimDto.setUpdatedOn(impClaim.getUpdatedOn());

		impClaimDto.setClaimStatusId(impClaim.getClaimStatus().getId());
		impClaimDto.setClaimStatusName(impClaim.getClaimStatus().getName());
		impClaimDto.setDescription(impClaim.getDescription());
		impClaimDto.setPolicyNumber(impClaim.getPolicy().getPolicyNumber());

		return impClaimDto;
	}

	private ImpClaim mapToImpClaim(ImpClaimDto impClaimDto) {
		ImpClaim impClaim = new ImpClaim();
		impClaim.setClaimDate(impClaimDto.getClaimDate());
		impClaim.setDescription(impClaimDto.getDescription());
		impClaim.setCreatedOn(LocalDateTime.now());
		impClaim.setClaimAmount(impClaimDto.getClaimAmount());

		Optional<ImpClaimStatusMaster> optClaimStatusMaster = impClaimStatusMasterRepository
				.findByName(DEFAULT_CLAIM_STATUS);
		if (optClaimStatusMaster.isPresent()) {
			impClaim.setClaimStatus(optClaimStatusMaster.get());
		}
		Optional<ImpInsurancePolicy> optInsurancePolicy = impInsurancePolicyRepository
				.findById(impClaimDto.getPolicyNumber());
		if (optInsurancePolicy.isPresent()) {
			impClaim.setPolicy(optInsurancePolicy.get());
		}
		return impClaim;
	}

	public List<ImpClaimStatusMasterDto> getAllClaimStatus() {
		LOG.info("getAllClaimStatus method started.");
		List<ImpClaimStatusMaster> allClaimStatus = impClaimStatusMasterRepository.findAll();

		if (allClaimStatus.isEmpty()) {
			LOG.error("Claim status not found.");
			throw new ImpBusinessException("Claim status not found.", HttpStatus.NOT_FOUND);
		}

		List<ImpClaimStatusMasterDto> impClaimStatusMasterDtos = new ArrayList<>();

		for (ImpClaimStatusMaster impClaimStatusMaster : allClaimStatus) {

			ImpClaimStatusMasterDto impClaimStatusMasterDto = new ImpClaimStatusMasterDto();

			impClaimStatusMasterDto.setId(impClaimStatusMaster.getId());
			impClaimStatusMasterDto.setName(impClaimStatusMaster.getName());
			impClaimStatusMasterDto.setDescription(impClaimStatusMaster.getDescription());

			impClaimStatusMasterDtos.add(impClaimStatusMasterDto);
		}
		return impClaimStatusMasterDtos;
	}

}
