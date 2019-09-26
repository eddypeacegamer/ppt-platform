package com.business.unknow.services.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.business.unknow.services.entities.Contribuyente;

public interface ContribuyenteRepository extends JpaRepository<Contribuyente, Integer> {

	public Optional<Contribuyente> findByRfc(String rfc);
	public Optional<Contribuyente> findByRazonSocial(String razonSocial);
	
}
