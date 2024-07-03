package com.keylin.WeCare.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.keylin.WeCare.dto.FamilyDto;
import com.keylin.WeCare.entities.Family;
import com.keylin.WeCare.repositories.FamilyRepository;
import com.keylin.WeCare.services.FamilyService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FamilyServiceImpl implements FamilyService {

    private FamilyRepository familyRepository;

    public FamilyServiceImpl(FamilyRepository familyRepository) {
        super();
        this.familyRepository = familyRepository;
    }

    @Override
    public List<FamilyDto> findAllFamilies() {
        List<Family> families = familyRepository.findAll();
        return families.stream().map((Family) -> mapToFamilyDto(Family)).collect(Collectors.toList());
    }

    @Override
    public Family findByFamilyId(long familyId) {
        Family family = familyRepository.findById(familyId).get();
        return family;

    }

    @Override
    public Family updateFamily(Long id, Family updatedFamily) {
        System.out.println("Actualizando familia con ID: " + id);

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

            System.out.println("Family updated: " + savedFamily.toString());

            return savedFamily;
        } else {

            throw new EntityNotFoundException("Not family found with id : " + id);
        }
    }

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
