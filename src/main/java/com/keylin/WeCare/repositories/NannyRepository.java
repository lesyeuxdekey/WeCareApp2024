package com.keylin.WeCare.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.keylin.WeCare.entities.Nanny;

@Repository
public interface NannyRepository extends JpaRepository<Nanny, Long> {
	Optional<Nanny> findByName(String url);

	@Query("select n from Nanny n where n.username=:username")
	public Nanny findByUsername(String username);

	@Query("select n from Nanny n where n.city=:city")
	public List<Nanny> findByCity(String city);

}
