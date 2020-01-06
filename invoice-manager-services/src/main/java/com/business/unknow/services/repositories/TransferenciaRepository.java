package com.business.unknow.services.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.business.unknow.services.entities.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {
	
	
	public Page<Transferencia> findAll(Pageable pageable);
	
	@Query("select t from Transferencia t where upper(t.lineaRetiro) like upper(:from) and t.folio = null and upper(t.lineaDeposito) like upper(:to)")
	public Page<Transferencia> findTransfersFromTo(@Param("from") String desde,@Param("to") String hacia ,Pageable pageable);
	public Optional<Transferencia> findTransferById(int id);

}
