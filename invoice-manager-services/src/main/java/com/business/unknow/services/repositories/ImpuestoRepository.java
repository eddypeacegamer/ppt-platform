package com.business.unknow.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.business.unknow.services.entities.cfdi.Impuesto;

public interface ImpuestoRepository extends JpaRepository<Impuesto, Integer> {

}
