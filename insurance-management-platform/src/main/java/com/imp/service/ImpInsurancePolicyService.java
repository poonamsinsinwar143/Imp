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

import com.imp.dto.ImpInsurancePolicyDto;
import com.imp.dto.ImpPolicyTypeMasterDto;
import com.imp.entity.ImpClient;
import com.imp.entity.ImpInsurancePolicy;
import com.imp.entity.ImpPolicyTypeMaster;
import com.imp.entity.repository.ImpClientRepository;
import com.imp.entity.repository.ImpInsurancePolicyRepository;
import com.imp.entity.repository.ImpPolicyTypeMasterRepository;
import com.imp.exception.ImpBusinessException;

@Service
public class ImpInsurancePolicyService {
	private static final Logger LOG = LoggerFactory.getLogger(ImpInsurancePolicyService.class);

	@Autowired
	private ImpInsurancePolicyRepository impInsurancePolicyRepository;

	@Autowired
	private ImpPolicyTypeMasterRepository impPolicyTypeMasterRepository;

	@Autowired
	private ImpClientRepository impClientRepository;

	public Long createInsurancePolicy(ImpInsurancePolicyDto impInsurancePolicyDto) {
		LOG.info("createInsurancePolicy method started.");
		ImpInsurancePolicy impInsurancePolicy = mapImpInsurancePolicy(impInsurancePolicyDto);
		impInsurancePolicy = impInsurancePolicyRepository.save(impInsurancePolicy);
		return impInsurancePolicy.getPolicyNumber();
	}

	private ImpInsurancePolicy mapImpInsurancePolicy(ImpInsurancePolicyDto impInsurancePolicyDto) {
		ImpInsurancePolicy impInsurancePolicy = new ImpInsurancePolicy();
		Optional<ImpPolicyTypeMaster> optImpPolicyTypeMaster = impPolicyTypeMasterRepository
				.findById(impInsurancePolicyDto.getPolicyTypeId());
		if (optImpPolicyTypeMaster.isPresent()) {
			impInsurancePolicy.setPolicyType(optImpPolicyTypeMaster.get());
		}
		impInsurancePolicy.setCoverageAmount(impInsurancePolicyDto.getCoverageAmount());
		impInsurancePolicy.setPremium(impInsurancePolicyDto.getPremium());
		impInsurancePolicy.setStartDate(impInsurancePolicyDto.getStartDate());
		impInsurancePolicy.setEndDate(impInsurancePolicyDto.getEndDate());
		impInsurancePolicy.setCreatedOn(LocalDateTime.now());

		Optional<ImpClient> optclient = impClientRepository.findById(impInsurancePolicyDto.getClientId());
		if (optclient.isPresent()) {
			impInsurancePolicy.setClient(optclient.get());
		}
		return impInsurancePolicy;
	}

	public List<ImpPolicyTypeMasterDto> getPolicyTypes() {
		LOG.info("getPolicyTypes method started.");
		List<ImpPolicyTypeMaster> policyTypes = impPolicyTypeMasterRepository.findAll();
		if (policyTypes.isEmpty()) {
			LOG.error("Any Policy type not found.");
			throw new ImpBusinessException("Any Policy type not found.", HttpStatus.NOT_FOUND);
		}
		List<ImpPolicyTypeMasterDto> impPolicyTypeMasterDtos = new ArrayList<>();

		for (ImpPolicyTypeMaster policyType : policyTypes) {

			ImpPolicyTypeMasterDto impPolicyTypeMasterDto = new ImpPolicyTypeMasterDto();

			impPolicyTypeMasterDto.setId(policyType.getId());
			impPolicyTypeMasterDto.setName(policyType.getName());
			impPolicyTypeMasterDto.setDescription(policyType.getDescription());
			impPolicyTypeMasterDtos.add(impPolicyTypeMasterDto);
		}
		return impPolicyTypeMasterDtos;
	}

	public ImpInsurancePolicyDto getInsurancePolicy(Long policyNumber) {
		LOG.info("getInsurancePolicy method called with policyNumber:{}", policyNumber);
		Optional<ImpInsurancePolicy> optImpInsurancePolicy = impInsurancePolicyRepository.findById(policyNumber);
		ImpInsurancePolicyDto impInsurancePolicyDto = new ImpInsurancePolicyDto();
		if (optImpInsurancePolicy.isPresent()) {
			ImpInsurancePolicy impInsurancePolicy = optImpInsurancePolicy.get();
			impInsurancePolicyDto.setPolicyNumber(policyNumber);

			impInsurancePolicyDto.setPolicyTypeId(impInsurancePolicy.getPolicyType().getId());
			impInsurancePolicyDto.setPolicyTypeName(impInsurancePolicy.getPolicyType().getName());
			impInsurancePolicyDto.setClientId(impInsurancePolicy.getClient().getId());

			impInsurancePolicyDto.setCoverageAmount(impInsurancePolicy.getCoverageAmount());
			impInsurancePolicyDto.setPremium(impInsurancePolicy.getPremium());
			impInsurancePolicyDto.setStartDate(impInsurancePolicy.getStartDate());
			impInsurancePolicyDto.setEndDate(impInsurancePolicy.getEndDate());
			impInsurancePolicyDto.setCreatedOn(impInsurancePolicy.getCreatedOn());
			impInsurancePolicyDto.setUpdatedOn(impInsurancePolicy.getUpdatedOn());
		} else {
			LOG.error("Insurance policy not found.");
			throw new ImpBusinessException("Insurance policy not found.", HttpStatus.NOT_FOUND);
		}
		return impInsurancePolicyDto;
	}

	public List<ImpInsurancePolicyDto> getAllInsurancePolicy() {
		LOG.info("getAllInsurancePolicy method started.");
		List<ImpInsurancePolicy> impInsurancePolicies = impInsurancePolicyRepository.findAll();
		if (impInsurancePolicies.isEmpty()) {
			LOG.error("No insurance policy found.");
			throw new ImpBusinessException("No insurance policy found.", HttpStatus.NOT_FOUND);
		}
		List<ImpInsurancePolicyDto> impInsurancePolicyDtos = new ArrayList<>();
		for (ImpInsurancePolicy impInsurancePolicy : impInsurancePolicies) {

			ImpInsurancePolicyDto impInsurancePolicyDto = new ImpInsurancePolicyDto();

			impInsurancePolicyDto.setPolicyTypeId(impInsurancePolicy.getPolicyType().getId());
			impInsurancePolicyDto.setPolicyTypeName(impInsurancePolicy.getPolicyType().getName());
			impInsurancePolicyDto.setClientId(impInsurancePolicy.getClient().getId());

			impInsurancePolicyDto.setPolicyNumber(impInsurancePolicy.getPolicyNumber());
			impInsurancePolicyDto.setCoverageAmount(impInsurancePolicy.getCoverageAmount());
			impInsurancePolicyDto.setPremium(impInsurancePolicy.getPremium());
			impInsurancePolicyDto.setStartDate(impInsurancePolicy.getStartDate());
			impInsurancePolicyDto.setEndDate(impInsurancePolicy.getEndDate());
			impInsurancePolicyDto.setCreatedOn(impInsurancePolicy.getCreatedOn());
			impInsurancePolicyDto.setUpdatedOn(impInsurancePolicy.getUpdatedOn());

			impInsurancePolicyDtos.add(impInsurancePolicyDto);
		}
		return impInsurancePolicyDtos;
	}

	public void deleteInsurancePolicy(Long policyNumber) {
		LOG.info("deleteInsurancePolicy method called with policyNumber:{}", policyNumber);
		Optional<ImpInsurancePolicy> optInsurancePolicy = impInsurancePolicyRepository.findById(policyNumber);
		if (optInsurancePolicy.isPresent()) {
			impInsurancePolicyRepository.deleteById(policyNumber);
		} else {
			LOG.error("No insurance policy found.");
			throw new ImpBusinessException("No insurance policy found.", HttpStatus.NOT_FOUND);
		}
	}

	public void updateInsurancePolicy(ImpInsurancePolicyDto impInsurancePolicyDto, Long policyNumber) {
		LOG.info("updateInsurancePolicy method called for policyNumber:{}", policyNumber);
		Optional<ImpInsurancePolicy> optInsurancePolicy = impInsurancePolicyRepository.findById(policyNumber);

		if (!optInsurancePolicy.isPresent()) {
			LOG.error("Insurance policy not found, policyNumber:{}", policyNumber);
			throw new ImpBusinessException("Insurance policy not found.", HttpStatus.NOT_FOUND);
		}

		ImpInsurancePolicy insurancePolicy = optInsurancePolicy.get();

		if (impInsurancePolicyDto.getCoverageAmount() != null) {
			insurancePolicy.setCoverageAmount(impInsurancePolicyDto.getCoverageAmount());
		}
		if (impInsurancePolicyDto.getPremium() != null) {
			insurancePolicy.setPremium(impInsurancePolicyDto.getPremium());
		}

		if (impInsurancePolicyDto.getPolicyTypeId() != null) {
			Optional<ImpPolicyTypeMaster> optPolicyTypeMaster = impPolicyTypeMasterRepository
					.findById(impInsurancePolicyDto.getPolicyTypeId());
			if (optPolicyTypeMaster.isPresent()) {
				insurancePolicy.setPolicyType(optPolicyTypeMaster.get());
			}
		}
		if (impInsurancePolicyDto.getClientId() != null) {
			Optional<ImpClient> optImpClient = impClientRepository.findById(impInsurancePolicyDto.getClientId());
			if (optImpClient.isPresent()) {
				insurancePolicy.setClient(optImpClient.get());
			}
		}

		if (impInsurancePolicyDto.getStartDate() != null) {
			insurancePolicy.setStartDate(impInsurancePolicyDto.getStartDate());
		}
		if (impInsurancePolicyDto.getEndDate() != null) {
			insurancePolicy.setEndDate(impInsurancePolicyDto.getEndDate());
		}

		impInsurancePolicyRepository.save(insurancePolicy);

	}
}