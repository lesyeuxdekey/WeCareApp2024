package com.keylin.WeCare.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.keylin.WeCare.entities.Family;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
    Optional<Family> findByName(String url);

    @Query("select f from Family f where f.username=:username")
    public Family findByUsername(String username);

    @Query("select n from Family n where n.city=:city")
    public List<Family> findByCity(String city);

}
