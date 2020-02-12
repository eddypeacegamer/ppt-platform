package com.business.unknow.services.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.business.unknow.services.entities.PagoDevolucion;

@Repository
public interface PagoDevolucionRepository extends JpaRepository<PagoDevolucion, Integer> {

	public Optional<PagoDevolucion> findById(Integer id);
}
