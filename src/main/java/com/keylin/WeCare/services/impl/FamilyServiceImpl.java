package com.keylin.WeCare.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.keylin.WeCare.dto.FamilyDto;
import com.keylin.WeCare.entities.Family;
import com.keylin.WeCare.repositories.FamilyRepository;
import com.keylin.WeCare.services.FamilyService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FamilyServiceImpl implements FamilyService {

    // The loggers to give detailed info when running in console
    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    // Repositories needed

    private FamilyRepository familyRepository;

    // Constructor

    public FamilyServiceImpl(FamilyRepository familyRepository) {
        super();
        this.familyRepository = familyRepository;
    }

    // Business logic

    // 1 service: get a list of all the objects of the entity nanny

    @Override
    public List<FamilyDto> findAllFamilies() {
        List<Family> families = familyRepository.findAll();
        log.info("Finding all nannies and the map it to a dto");
        return families.stream().map((Family) -> mapToFamilyDto(Family)).collect(Collectors.toList());
    }

    // 2 service: get a specific nanny

    @Override
    public Family findByFamilyId(long familyId) {
        Family family = familyRepository.findById(familyId).get();
        log.info("Finding a nanny by id");
        return family;
    }

    // Service find families by the city written by user on the search bar
    @Override
    public List<FamilyDto> findFamiliesByCity(String city) {
        List<Family> familyCity = familyRepository.findByCity(city);
        log.info("Finding a list of Families based of the City ");
        return familyCity.stream().map((Family) -> mapToFamilyDto(Family)).collect(Collectors.toList());
    }

    // update nanny when click on edit button on the user's profile

    @Override
    public Family updateFamily(Long id, Family updatedFamily) {
        log.info("Updating the family with ID: " + id);

        Optional<Family> optionalFamily = familyRepository.findById(id);

        if (optionalFamily.isPresent()) {
            Family existingFamily = optionalFamily.get();

            System.out.println("Family found: " + existingFamily.toString());

            // Update
            existingFamily.setName(updatedFamily.getName());
            existingFamily.setEmail(updatedFamily.getEmail());
            existingFamily.setTelephone(updatedFamily.getTelephone());
            existingFamily.setCity(updatedFamily.getCity());
            existingFamily.setPostalCode(updatedFamily.getPostalCode());
            existingFamily.setAddress(updatedFamily.getAddress());
            existingFamily.setServiceFee(updatedFamily.getServiceFee());
            existingFamily.setDescription(updatedFamily.getDescription());
            existingFamily.setNumChildren(updatedFamily.getNumChildren());
            existingFamily.setPhotoUrl(updatedFamily.getPhotoUrl());

            // Save the family in the data base
            Family savedFamily = familyRepository.save(existingFamily);

            log.info("Family updated: " + savedFamily.toString());

            return savedFamily;
        } else {

            throw new EntityNotFoundException("Not family found with id : " + id);
        }
    }

    // mapping
    private FamilyDto mapToFamilyDto(Family family) {
        FamilyDto familyDto = FamilyDto.builder()
                .id(family.getId())
                .name(family.getName())
                .city(family.getCity())
                .postalCode(family.getPostalCode())
                .serviceFee(family.getServiceFee())
                .description(family.getDescription())
                .numChildren(family.getNumChildren())
                .photoUrl(family.getPhotoUrl())
                .build();
        return familyDto;
    }
}
