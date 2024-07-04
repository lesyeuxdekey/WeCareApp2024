package com.keylin.WeCare.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keylin.WeCare.dto.NannyDto;
import com.keylin.WeCare.entities.Nanny;
import com.keylin.WeCare.repositories.NannyRepository;
import com.keylin.WeCare.services.NannyService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class NannyServiceImpl implements NannyService {

	// The loggers to give detailed info when running in console
	Logger log = LoggerFactory.getLogger(NannyServiceImpl.class);

	@Autowired
	private NannyRepository nannyRepository;

	// Constructor

	public NannyServiceImpl(NannyRepository nannyRepository) {
		this.nannyRepository = nannyRepository;
	}

	// Business logic:n services

	// Service #1: find nanny by id
	@Override
	public Nanny findByNannyId(long nannyId) {
		Nanny nanny = nannyRepository.findById(nannyId).get();
		return nanny;

	}

	@Override
	public List<NannyDto> findAllNannies() {

		List<Nanny> nannies = nannyRepository.findAll();
		return nannies.stream().map((Nanny) -> mapToNannyDto(Nanny)).collect(Collectors.toList());
	}

	@Override
	public Nanny updateNanny(Long id, Nanny updateNanny) {
		log.info("Updating the family with ID: " + id);

		Optional<Nanny> optionalNanny = nannyRepository.findById(id);

		if (optionalNanny.isPresent()) {
			Nanny existingNanny = optionalNanny.get();

			log.info("Nanny found: " + existingNanny.toString());

			// Update
			existingNanny.setName(updateNanny.getName());
			existingNanny.setEmail(updateNanny.getEmail());
			existingNanny.setTelephone(updateNanny.getTelephone());
			existingNanny.setCity(updateNanny.getCity());
			existingNanny.setPostalCode(updateNanny.getPostalCode());
			existingNanny.setAddress(updateNanny.getAddress());
			existingNanny.setServiceFee(updateNanny.getServiceFee());
			existingNanny.setDescription(updateNanny.getDescription());
			existingNanny.setYearsOfExperience(updateNanny.getYearsOfExperience());
			existingNanny.setPhotoUrl(updateNanny.getPhotoUrl());

			// Save the family in the data base
			Nanny savedNanny = nannyRepository.save(existingNanny);

			log.info("Nanny updated: " + savedNanny.toString());

			return savedNanny;
		} else {

			throw new EntityNotFoundException("Not family found with id : " + id);
		}
	}

	private NannyDto mapToNannyDto(Nanny nanny) {
		NannyDto nannyDto = NannyDto.builder()
				.id(nanny.getId())
				.name(nanny.getName())
				.age(nanny.getAge())
				.city(nanny.getCity())
				.postalCode(nanny.getPostalCode())
				.serviceFee(nanny.getServiceFee())
				.description(nanny.getDescription())
				.yearsOfExperience(nanny.getYearsOfExperience())
				.photoUrl(nanny.getPhotoUrl())
				.build();
		return nannyDto;
	}
}
