/**
 * 
 */
package com.business.unknow.services.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.model.dto.cfdi.ComplementoDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.cfdi.ImpuestoDto;
import com.business.unknow.model.dto.cfdi.RetencionDto;
import com.business.unknow.model.dto.cfdi.TimbradoFiscalDigitialDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.cfdi.Concepto;
import com.business.unknow.services.entities.cfdi.Emisor;
import com.business.unknow.services.entities.cfdi.Impuesto;
import com.business.unknow.services.entities.cfdi.Receptor;
import com.business.unknow.services.entities.cfdi.Retencion;
import com.business.unknow.services.entities.cfdi.TimbradoFiscalDigitial;
import com.business.unknow.services.mapper.factura.CfdiMapper;
import com.business.unknow.services.repositories.facturas.CfdiPagoRepository;
import com.business.unknow.services.repositories.facturas.CfdiRepository;
import com.business.unknow.services.repositories.facturas.ConceptoRepository;
import com.business.unknow.services.repositories.facturas.EmisorRepository;
import com.business.unknow.services.repositories.facturas.ImpuestoRepository;
import com.business.unknow.services.repositories.facturas.ReceptorRepository;
import com.business.unknow.services.repositories.facturas.RetencionRepository;
import com.business.unknow.services.repositories.facturas.TimbradoFiscalDigitialRepository;

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
	private EmisorRepository emisorRepository;

	@Autowired
	private CfdiPagoRepository cfdiPagoRepository;

	@Autowired
	private ReceptorRepository receptorReceptor;

	@Autowired
	private ImpuestoRepository impuestoRepository;
	
	@Autowired
	private RetencionRepository retencionRepository;
	
	@Autowired
	private TimbradoFiscalDigitialRepository timbradoFiscalDigitialRepository;

	@Autowired
	private CfdiMapper mapper;

	private static final Logger log = LoggerFactory.getLogger(CfdiService.class);

	public CfdiDto getCfdiByFolio(String folio) {
		CfdiDto cfdiDto = mapper.getCfdiDtoFromEntity(
				repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se encontro CFDI con folio %s", folio))));
		for (ConceptoDto conceptoDto : cfdiDto.getConceptos()) {
			conceptoDto.setImpuestos(getImpuestosByConcepto(conceptoDto.getId()));
		}
		List<CfdiPagoDto> pagosCfdi = getCfdiPagosByCfdi(cfdiDto.getId());
		if (cfdiDto.getComplemento() == null) {
			cfdiDto.setComplemento(new ComplementoDto());
		}
		if(pagosCfdi!=null&&!pagosCfdi.isEmpty()) {
			cfdiDto.getComplemento().setPagos(pagosCfdi);
		}
		cfdiDto.getComplemento().setTimbreFiscal(getTimbradoDigital(cfdiDto.getId()));
		return cfdiDto;
	}

	private TimbradoFiscalDigitialDto getTimbradoDigital(int idCfdi) {
		Optional<TimbradoFiscalDigitial> timbre=timbradoFiscalDigitialRepository.findByIdCfdi(idCfdi);
		if(timbre.isPresent()) {
			return mapper.getComplementoDtoFromEntity(timbre.get());
		}
		else {
			return null;
		}
	}
	
	private List<ImpuestoDto> getImpuestosByConcepto(int id) {
		return mapper.getImpuestosDtosFromEntities(impuestoRepository.findById(id));
	}

	private List<CfdiPagoDto> getCfdiPagosByCfdi(int id) {
		return mapper.getCfdiPagosDtoFromEntities(cfdiPagoRepository.findByCfdi(id));
	}

	public CfdiDto insertNewCfdi(CfdiDto cfdi) throws InvoiceManagerException {
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
		Cfdi entity = repository.save(mapper.getEntityFromCfdiDto(cfdi));
		Emisor emisor = mapper.getEntityFromEmisorDto(cfdi.getEmisor());
		if(emisor.getNombre()==null) {
			emisor.setNombre(emisor.getRfc());
		}
		emisor.setCfdi(entity);
		emisorRepository.save(emisor);
		Receptor receptor = mapper.getEntityFromEmisorDto(cfdi.getReceptor());
		if(receptor.getNombre()==null) {
			receptor.setNombre(receptor.getRfc());
		}
		receptor.setCfdi(entity);
		receptorReceptor.save(receptor);
		for (ConceptoDto concepto : cfdi.getConceptos()) {
			Concepto conceptoEntity = mapper.getEntityFromConceptoDto(concepto);
			conceptoEntity.setCfdi(entity);
			conceptoEntity = conceptoRepository.save(conceptoEntity);
			for (ImpuestoDto impuesto : concepto.getImpuestos()) {
				Impuesto imp = mapper.getEntityFromImpuestoDto(impuesto);
				imp.setConcepto(conceptoEntity);
				impuestoRepository.save(imp);
			}
			for (RetencionDto retencion : concepto.getRetenciones()) {
				Retencion reten = mapper.getEntityFromRetencionDto(retencion);
				reten.setConcepto(conceptoEntity);
				retencionRepository.save(reten);
			}
			
		}
		return mapper.getCfdiDtoFromEntity(repository.findById(entity.getId()).orElse(null));
	}

	@Transactional(rollbackFor = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public CfdiDto updateCfdiBody(String folio, Integer id, CfdiDto cfdi) throws InvoiceManagerException {
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
		repository.findById(id).orElseThrow(() -> new InvoiceManagerException("Error al obtener el Cfdi",
				String.format("El cfdi con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value()));
		return mapper.getCfdiDtoFromEntity(repository.save(mapper.getEntityFromCfdiDto(cfdi)));
	}

	@Transactional(rollbackFor = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
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

	@Transactional(rollbackFor = { InvoiceManagerException.class, DataAccessException.class,
			SQLException.class }, isolation = Isolation.READ_UNCOMMITTED)
	public CfdiDto insertNewConceptoToCfdi(String folio, ConceptoDto newConcept) throws InvoiceManagerException {
		CfdiDto cfdi = mapper.getCfdiDtoFromEntity(
				repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se ecnontro CFDI con folio %s", folio))));
		cfdi.getConceptos().add(newConcept);
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
		Cfdi entity = repository.save(mapper.getEntityFromCfdiDto(cfdi));// Update CFDI ammounts
		Concepto conceptoEntity = mapper.getEntityFromConceptoDto(newConcept);
		conceptoEntity.setCfdi(entity);
		conceptoEntity = conceptoRepository.save(conceptoEntity);
		for (ImpuestoDto impuesto : newConcept.getImpuestos()) {
			Impuesto imp = mapper.getEntityFromImpuestoDto(impuesto);
			imp.setConcepto(conceptoEntity);
			impuestoRepository.save(imp);
		}
		for(RetencionDto retencion:newConcept.getRetenciones()) {
			Retencion ret = mapper.getEntityFromRetencionDto(retencion);
			ret.setConcepto(conceptoEntity);
			retencionRepository.save(ret);
		}
		return cfdi;
	}

	@Transactional(rollbackFor = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public CfdiDto removeConceptFromCfdi(String folio, Integer conceptoId) throws InvoiceManagerException {
		CfdiDto cfdi = mapper.getCfdiDtoFromEntity(
				repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se encontro el CFDI con folio  %s", folio))));
		Concepto concepto = conceptoRepository.findById(conceptoId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se encontro concepto con id %d", conceptoId)));
		cfdi.getConceptos()
				.remove(cfdi.getConceptos().stream().filter(c -> c.getId().equals(conceptoId)).findFirst()
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
								String.format("No se encontro concepto con id %d", conceptoId))));
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
		Cfdi entity = repository.save(mapper.getEntityFromCfdiDto(cfdi));// Update CFDI ammounts
		for (Impuesto impuesto : concepto.getImpuestos()) {
			impuestoRepository.delete(impuesto); // Deleting impuesto
		}
		for (Retencion retencion : concepto.getRetenciones()) {
			retencionRepository.delete(retencion); // Deleting retenciones
		}
		conceptoRepository.deleteById(concepto.getId());
		return mapper.getCfdiDtoFromEntity(entity);
	}

	@Transactional(rollbackFor = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public CfdiDto updateConceptoFromCfdi(String folio, Integer conceptoId, ConceptoDto concepto)
			throws InvoiceManagerException {
		CfdiDto cfdi = mapper.getCfdiDtoFromEntity(
				repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se encontro el CFDI con folio  %s", folio))));
		int index = cfdi.getConceptos()
				.indexOf(cfdi.getConceptos().stream().filter(c -> c.getId().equals(conceptoId)).findFirst()
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
								String.format("No se encontro concepto con id %d", conceptoId))));
		cfdi.getConceptos().set(index, concepto);
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
		Cfdi entity = repository.save(mapper.getEntityFromCfdiDto(cfdi));
		Concepto conceptoEntity = mapper.getEntityFromConceptoDto(concepto);
		conceptoEntity.setCfdi(entity);
		conceptoRepository.save(conceptoEntity);
		impuestoRepository.deleteByConcepto(conceptoEntity);
		retencionRepository.deleteByConcepto(conceptoEntity);
		// 2.- Update concepto and its references
		for (ImpuestoDto impuesto : concepto.getImpuestos()) {
			Impuesto imp = mapper.getEntityFromImpuestoDto(impuesto);
			imp.setConcepto(conceptoEntity);
			impuestoRepository.save(imp);
		}
		// 4.- recalculate values
		return cfdi;
	}

	private void validateCfdi(CfdiDto cfdi) throws InvoiceManagerException {
		if (cfdi.getConceptos().isEmpty()) {
			throw new InvoiceManagerException("El CFDI no puede tener 0 conceptos", "Numero de comceptos invalido",
					HttpStatus.CONFLICT.value());
		}
		if (cfdi.getMetodoPago().equals("01")) {
			throw new InvoiceManagerException(
					"En pagos en efectivo el monto a facturar no debe de ser superior a 2000 pesos",
					"Metodo de pago invalido", HttpStatus.CONFLICT.value());
		}
		// TODO add more validations here
	}

	private void recalculateCfdiAmmounts(CfdiDto cfdi) {
		BigDecimal subtotal = cfdi.getConceptos().stream().map(c -> c.getImporte()).reduce(BigDecimal.ZERO,
				(i1, i2) -> i1.add(i2));
		BigDecimal retenciones =cfdi.getConceptos().stream()
				.map(i -> i.getRetenciones().stream().map(imp -> imp.getImporte()).reduce(BigDecimal.ZERO,
						(i1, i2) -> i1.add(i2)))// suma importe retencioness por concepto
				.reduce(BigDecimal.ZERO, (i1, i2) -> i1.add(i2));
		BigDecimal impuestos = cfdi.getConceptos().stream()
				.map(i -> i.getImpuestos().stream().map(imp -> imp.getImporte()).reduce(BigDecimal.ZERO,
						(i1, i2) -> i1.add(i2)))// suma importe impuestos por concepto
				.reduce(BigDecimal.ZERO, (i1, i2) -> i1.add(i2));
		BigDecimal total = subtotal.add(impuestos).subtract(retenciones);
		log.info("Calculating cfdi values subtotal = {}, impuestos = {} , total = {}", subtotal, impuestos, total);
		cfdi.setSubtotal(subtotal);
		cfdi.setTotal(total);
		cfdi.setDescuento(BigDecimal.ZERO);// los descuentos no estan soportados
	}

}
