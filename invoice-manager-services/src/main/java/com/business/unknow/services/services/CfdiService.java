/**
 * 
 */
package com.business.unknow.services.services;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.cfdi.components.CfdiDto;
import com.business.unknow.model.factura.cfdi.components.ConceptoDto;
import com.business.unknow.model.factura.cfdi.components.ImpuestoDto;
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
		for (ConceptoDto concepto  : cfdi.getConceptos()) {
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
	
	
	public CfdiDto insertNewConceptoToCfdi(String folio, ConceptoDto newConcept) throws InvoiceManagerException {
		CfdiDto cfdi  = mapper.getCfdiDtoFromEntity(repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("NO se ecnontro CFDI con folio %s", folio))));
		cfdi.getConceptos().add(newConcept);
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
		Cfdi entity = repository.save(mapper.getEntityFromCfdiDto(cfdi));//Update CFDI ammounts
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
	
	public CfdiDto removeConceptFromCfdi(String folio,ConceptoDto concepto) throws InvoiceManagerException {
		CfdiDto cfdi  = mapper.getCfdiDtoFromEntity(repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("No se ecnontro CFDI con folio %s", folio))));
		cfdi.getConceptos().remove(cfdi.getConceptos().indexOf(concepto));//Removing concepto
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
	
		 for (ImpuestoDto impuesto : concepto.getImpuestos()) {
			impuestoRepository.delete(impuestoMapper.getEntityFromClientDto(impuesto)); //Deleting impuesto
		 }
		 conceptoRepository.deleteById(concepto.getId());
		 Cfdi entity = repository.save(mapper.getEntityFromCfdiDto(cfdi));//Update CFDI ammounts			
		 return mapper.getCfdiDtoFromEntity(repository.findById(entity.getId()).orElse(null));
	}
	
	public CfdiDto updateConceptoFromCfdi(String folio,Integer conceptoId, ConceptoDto concepto) {
		//1.-  find Conceto by folio and Id
		
		// 2.- Update concepto and its references
		
		// 3.- Get CFDI info with conceptos
		
		// 4.- recalculate values
		return new CfdiDto();
	}
	
	private void validateCfdi(CfdiDto cfdi) throws InvoiceManagerException {
		if(cfdi.getConceptos().isEmpty()) {
			throw new InvoiceManagerException("El CFDI no puede tener 0 conceptos","Numero de comceptos invalido",HttpStatus.CONFLICT.value());
		}
		//TODO add more validations here
	}
	
	
	private void recalculateCfdiAmmounts(CfdiDto cfdi) {
		BigDecimal subtotal = cfdi.getConceptos().stream().map(c->c.getImporte()).reduce(BigDecimal.ZERO,(i1,i2)->i1.add(i2));
		BigDecimal impuestos = cfdi.getConceptos().stream()
				.map(i->i.getImpuestos().stream().map(imp->imp.getImporte()).reduce(BigDecimal.ZERO,(i1,i2)->i1.add(i2)))//suma importe impuestos por concepto
				.reduce(BigDecimal.ZERO,(i1,i2)->i1.add(i2));
		BigDecimal total  = subtotal.add(impuestos);
		log.info("Calculating cfdi values suatotal = {}, impuestos = {} , total = {}", subtotal, impuestos, total);
		cfdi.setSubtotal(subtotal);
		cfdi.setTotal(total);
		cfdi.setDescuento(BigDecimal.ZERO);// los descuentos no estan soportados
	}
	
	
	
	

//		public CfdiDto updateFacturaCfdi(String folio, Integer id, CfdiDto dto) throws InvoiceManagerException {
//			validator.validatePostCfdi(dto, folio);
//			cfdiRepository.findById(id).orElseThrow(() -> new InvoiceManagerException("Error al obtener el Cfdi",
//					String.format("El cfdi con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value()));
//			return cfdiMapper.getCfdiDtoFromEntity(cfdiRepository.save(cfdiMapper.getEntityFromCfdiDto(dto)));
//		}
//
//		public void deleteFacturaCfdi(String folio, Integer id) throws InvoiceManagerException {
//			Cfdi entity = cfdiRepository.findById(id)
//					.orElseThrow(() -> new InvoiceManagerException("Error al obtener el Cfdi",
//							String.format("El cfdi con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value()));
//			cfdiRepository.delete(entity);
//		}
		

}
