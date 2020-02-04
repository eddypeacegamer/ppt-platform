package com.business.unknow.services.repositories.facturas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.business.unknow.services.entities.cfdi.Emisor;

@Repository
public interface EmisorRepository extends JpaRepository<Emisor, Integer> {

}
