package com.keylin.WeCare.services;

import java.util.List;

import com.keylin.WeCare.dto.FamilyDto;
import com.keylin.WeCare.entities.Family;

public interface FamilyService {
    List<FamilyDto> findAllFamilies();

    Family findByFamilyId(long familyId);

    public Family updateFamily(Long id, Family updatedFamily);

    public List<FamilyDto> findFamiliesByCity(String city);

}
