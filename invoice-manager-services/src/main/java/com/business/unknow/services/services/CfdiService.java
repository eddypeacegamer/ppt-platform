/**
 * 
 */
package com.business.unknow.services.services;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.cfdi.ImpuestoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.cfdi.Concepto;
import com.business.unknow.services.entities.cfdi.Impuesto;
import com.business.unknow.services.mapper.CfdiMapper;
import com.business.unknow.services.mapper.ConceptoMapper;
import com.business.unknow.services.mapper.ImpuestoMapper;
import com.business.unknow.services.repositories.facturas.CfdiRepository;
import com.business.unknow.services.repositories.facturas.ConceptoRepository;
import com.business.unknow.services.repositories.facturas.ImpuestoRepository;

/**
 * @author hha0009
 *
 */
@Service
public class CfdiService {

	@Autowired
	private CfdiRepository repository;

	@Autowired
	private ConceptoRepository conceptoRepository;

	@Autowired
	private ImpuestoRepository impuestoRepository;

	@Autowired
	private CfdiMapper mapper;

	@Autowired
	private ConceptoMapper conceptoMapper;

	@Autowired
	private ImpuestoMapper impuestoMapper;

	private static final Logger log = LoggerFactory.getLogger(CfdiService.class);

	public CfdiDto getCfdiByFolio(String folio) {
		return mapper.getCfdiDtoFromEntity(
				repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se ecnontro CFDI con folio %s", folio))));

	}

	public CfdiDto insertNewCfdi(CfdiDto cfdi) throws InvoiceManagerException {
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
		Cfdi entity = repository.save(mapper.getEntityFromCfdiDto(cfdi));
		for (ConceptoDto concepto : cfdi.getConceptos()) {
			Concepto conceptoEntity = conceptoMapper.getEntityFromConceptoDto(concepto);
			conceptoEntity.setCfdi(entity);
			conceptoEntity = conceptoRepository.save(conceptoEntity);
			for (ImpuestoDto impuesto : concepto.getImpuestos()) {
				Impuesto imp = impuestoMapper.getEntityFromClientDto(impuesto);
				imp.setConcepto(conceptoEntity);
				impuestoRepository.save(imp);
			}
		}
		return mapper.getCfdiDtoFromEntity(repository.findById(entity.getId()).orElse(null));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public CfdiDto updateCfdiBody(String folio, Integer id, CfdiDto cfdi) throws InvoiceManagerException {
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
		repository.findById(id).orElseThrow(() -> new InvoiceManagerException("Error al obtener el Cfdi",
				String.format("El cfdi con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value()));
		return mapper.getCfdiDtoFromEntity(repository.save(mapper.getEntityFromCfdiDto(cfdi)));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public void deleteCfdi(String folio) throws InvoiceManagerException {
		Cfdi entity = repository.findByFolio(folio)
				.orElseThrow(() -> new InvoiceManagerException("Error al obtener el Cfdi",
						String.format("El cfdi con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value()));
		
		for (Concepto concepto : entity.getConceptos()) {
			for (Impuesto impuesto : concepto.getImpuestos()) {
				impuestoRepository.delete(impuesto);
			}
			conceptoRepository.delete(concepto);
		}
		repository.delete(entity);
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public CfdiDto insertNewConceptoToCfdi(String folio, ConceptoDto newConcept) throws InvoiceManagerException {
		CfdiDto cfdi = mapper.getCfdiDtoFromEntity(
				repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se ecnontro CFDI con folio %s", folio))));
		cfdi.getConceptos().add(newConcept);
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
		Cfdi entity = repository.save(mapper.getEntityFromCfdiDto(cfdi));// Update CFDI ammounts
		Concepto conceptoEntity = conceptoMapper.getEntityFromConceptoDto(newConcept);
		conceptoEntity.setCfdi(entity);
		conceptoEntity = conceptoRepository.save(conceptoEntity);
		for (ImpuestoDto impuesto : newConcept.getImpuestos()) {
			Impuesto imp = impuestoMapper.getEntityFromClientDto(impuesto);
			imp.setConcepto(conceptoEntity);
			impuestoRepository.save(imp);
		}
		return mapper.getCfdiDtoFromEntity(repository.findById(entity.getId()).orElse(null));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public CfdiDto removeConceptFromCfdi(String folio, Integer conceptoId) throws InvoiceManagerException {
		Concepto concepto = conceptoRepository.findById(conceptoId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se encontro concepto con id %d", conceptoId)));
		for (Impuesto impuesto : concepto.getImpuestos()) {
			impuestoRepository.delete(impuesto); // Deleting impuesto
		}
		conceptoRepository.deleteById(concepto.getId());
		CfdiDto cfdi = mapper.getCfdiDtoFromEntity(repository.findByFolio(folio).orElseThrow(() -> // propagate
																									// InvoiceManagerException
																									// to force
																									// rolleback
		new InvoiceManagerException(
				String.format("El CFDI con folio %s no se encuentra ligado al concepto con id %d", folio, conceptoId),
				HttpStatus.CONFLICT.value())));
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
		Cfdi entity = repository.save(mapper.getEntityFromCfdiDto(cfdi));// Update CFDI ammounts
		return mapper.getCfdiDtoFromEntity(repository.findById(entity.getId()).orElse(null));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public CfdiDto updateConceptoFromCfdi(String folio, Integer conceptoId, ConceptoDto concepto)
			throws InvoiceManagerException {
		// 1.- find Conceto by folio and Id
		Concepto conceptoEntity = conceptoRepository.findById(conceptoId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se encontro concepto con id %d", conceptoId)));
		// 2.- Update concepto and its references
		for (ImpuestoDto impuesto : concepto.getImpuestos()) {
			Impuesto imp = impuestoMapper.getEntityFromClientDto(impuesto);
			imp.setConcepto(conceptoEntity);
			impuestoRepository.save(imp);
		}
		conceptoRepository.save(conceptoMapper.getEntityFromConceptoDto(concepto));
		// 3.- Get CFDI info with conceptos
		CfdiDto cfdi = mapper.getCfdiDtoFromEntity(repository.findByFolio(folio).orElseThrow(() -> // propagate
																									// InvoiceManagerException
																									// to force
																									// rolleback
		new InvoiceManagerException(
				String.format("El CFDI con folio %s no se encuentra ligado al concepto con id %d", folio, conceptoId),
				HttpStatus.CONFLICT.value())));
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
		// 4.- recalculate values
		return cfdi;
	}

	private void validateCfdi(CfdiDto cfdi) throws InvoiceManagerException {
		if (cfdi.getConceptos().isEmpty()) {
			throw new InvoiceManagerException("El CFDI no puede tener 0 conceptos", "Numero de comceptos invalido",
					HttpStatus.CONFLICT.value());
		}
		// TODO add more validations here
	}

	private void recalculateCfdiAmmounts(CfdiDto cfdi) {
		BigDecimal subtotal = cfdi.getConceptos().stream().map(c -> c.getImporte()).reduce(BigDecimal.ZERO,
				(i1, i2) -> i1.add(i2));
		BigDecimal impuestos = cfdi.getConceptos().stream()
				.map(i -> i.getImpuestos().stream().map(imp -> imp.getImporte()).reduce(BigDecimal.ZERO,
						(i1, i2) -> i1.add(i2)))// suma importe impuestos por concepto
				.reduce(BigDecimal.ZERO, (i1, i2) -> i1.add(i2));
		BigDecimal total = subtotal.add(impuestos);
		log.info("Calculating cfdi values suatotal = {}, impuestos = {} , total = {}", subtotal, impuestos, total);
		cfdi.setSubtotal(subtotal);
		cfdi.setTotal(total);
		cfdi.setDescuento(BigDecimal.ZERO);// los descuentos no estan soportados
	}

}
