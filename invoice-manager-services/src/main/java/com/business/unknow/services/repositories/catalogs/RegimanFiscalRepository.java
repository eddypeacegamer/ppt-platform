package com.business.unknow.services.repositories.catalogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.catalogs.RegimenFiscal;

@Repository
public interface RegimanFiscalRepository extends JpaRepository<RegimenFiscal, Integer> {

}
