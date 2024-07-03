package com.keylin.WeCare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.keylin.WeCare.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.nanny.id = :nannyId")
    List<Booking> findByNannyId(Long nannyId);

    @Query("SELECT b FROM Booking b WHERE b.family.id = :familyId")
    List<Booking> findByFamilyId(Long familyId);
}
