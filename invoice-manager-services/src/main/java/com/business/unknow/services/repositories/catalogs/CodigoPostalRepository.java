package com.business.unknow.services.repositories.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.catalogs.CodigoPostal;

@Repository
public interface CodigoPostalRepository extends JpaRepository<CodigoPostal, String> {

	public List<CodigoPostal> findByCodigoPostal(String codigoPostal);
}
