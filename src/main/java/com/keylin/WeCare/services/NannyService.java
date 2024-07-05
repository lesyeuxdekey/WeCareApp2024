package com.keylin.WeCare.services;

import java.util.List;

import com.keylin.WeCare.dto.NannyDto;
import com.keylin.WeCare.entities.Nanny;

public interface NannyService {
	List<NannyDto> findAllNannies();

	Nanny findByNannyId(long nannyId);

	public Nanny updateNanny(Long id, Nanny updateNanny);

	public List<NannyDto> findNanniesByCity(String city);
}
