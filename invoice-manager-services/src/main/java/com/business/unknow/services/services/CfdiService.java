/**
 * 
 */
package com.business.unknow.services.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

import com.business.unknow.commons.validator.AbstractValidator;
import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.model.dto.cfdi.ComplementoDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.cfdi.ImpuestoDto;
import com.business.unknow.model.dto.cfdi.RetencionDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.cfdi.CfdiPago;
import com.business.unknow.services.entities.cfdi.Concepto;
import com.business.unknow.services.entities.cfdi.Emisor;
import com.business.unknow.services.entities.cfdi.Impuesto;
import com.business.unknow.services.entities.cfdi.Receptor;
import com.business.unknow.services.entities.cfdi.Relacionado;
import com.business.unknow.services.entities.cfdi.Retencion;
import com.business.unknow.services.mapper.factura.CfdiMapper;
import com.business.unknow.services.repositories.facturas.CfdiPagoRepository;
import com.business.unknow.services.repositories.facturas.CfdiRelacionadoRepository;
import com.business.unknow.services.repositories.facturas.CfdiRepository;
import com.business.unknow.services.repositories.facturas.ConceptoRepository;
import com.business.unknow.services.repositories.facturas.EmisorRepository;
import com.business.unknow.services.repositories.facturas.ImpuestoRepository;
import com.business.unknow.services.repositories.facturas.ReceptorRepository;
import com.business.unknow.services.repositories.facturas.RetencionRepository;

/**
 * @author ralfdemoledor
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
	private ReceptorRepository receptorRepository;

	@Autowired
	private ImpuestoRepository impuestoRepository;
	
	@Autowired
	private CfdiRelacionadoRepository cfdiRelacionadoRepository;

	@Autowired
	private RetencionRepository retencionRepository;

	@Autowired
	private CfdiMapper mapper;

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private CatalogCacheService cacheCatalogsService;

	@Autowired
	private AbstractValidator validator = new AbstractValidator();

	private static final Logger log = LoggerFactory.getLogger(CfdiService.class);

	public CfdiDto getCfdiByFolio(String folio) {
		CfdiDto cfdiDto = mapper.getCfdiDtoFromEntity(
				repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se encontro CFDI con folio %s", folio))));
		if (cfdiDto.getConceptos() == null || cfdiDto.getConceptos().isEmpty()) {
			cfdiDto.setConceptos(mapper.getDtosFromConceptoEntities(conceptoRepository.findByCfdi(cfdiDto.getId())));
		}
		for (ConceptoDto conceptoDto : cfdiDto.getConceptos()) {
			conceptoDto.setImpuestos(getImpuestosByConcepto(conceptoDto.getId()));
		}
		List<CfdiPagoDto> pagosCfdi = getCfdiPagosByCfdi(cfdiDto.getId());
		if (cfdiDto.getComplemento() == null) {
			cfdiDto.setComplemento(new ComplementoDto());
		}
		if (pagosCfdi != null && !pagosCfdi.isEmpty()) {
			cfdiDto.getComplemento().setPagos(pagosCfdi);
		}
		if (cfdiDto.getEmisor() == null) {
			Optional<Emisor> emisor = emisorRepository.findByIdCfdi(cfdiDto.getId());
			if (emisor.isPresent()) {
				cfdiDto.setEmisor(mapper.getEmisorDtoFromEntity(emisor.get()));
			}
		}
		if (cfdiDto.getReceptor() == null) {
			Optional<Receptor> receptor = receptorRepository.findByIdCfdi(cfdiDto.getId());
			if (receptor.isPresent()) {
				cfdiDto.setReceptor(mapper.getRecetorDtoFromEntity(receptor.get()));
			}
		}
		return cfdiDto;
	}

	public CfdiDto getCfdiById(Integer id) {
		CfdiDto cfdiDto = mapper.getCfdiDtoFromEntity(
				repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("El cfdi con folio %d no fue encontrado", id))));

		if (cfdiDto.getComplemento() == null) {
			cfdiDto.setComplemento(new ComplementoDto());
			List<CfdiPagoDto> pagosCfdi = getCfdiPagosByCfdi(cfdiDto.getId());
			if (pagosCfdi != null && !pagosCfdi.isEmpty()) {
				cfdiDto.getComplemento().setPagos(pagosCfdi);
			}
		}

		return cfdiDto;
	}

	private List<ImpuestoDto> getImpuestosByConcepto(int id) {
		return mapper.getImpuestosDtosFromEntities(impuestoRepository.findById(id));
	}

	public List<CfdiPagoDto> getCfdiPagosByFolio(String folio) {
		return mapper.getCfdiPagosDtoFromEntities(cfdiPagoRepository.findByFolio(folio));
	}
	
	private List<CfdiPagoDto> getCfdiPagosByCfdi(int id) {
		return mapper.getCfdiPagosDtoFromEntities(cfdiPagoRepository.findByCfdi(id));
	}

	public List<CfdiPagoDto> getPagosPPD(Integer id) {
		Cfdi cfdi = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El cfdi con folio %d no fue encontrado", id)));
		return mapper.getCfdiPagosDtoFromEntities(cfdiPagoRepository.findByFolio(cfdi.getFolio()));
	}

	public CfdiDto insertNewCfdi(CfdiDto cfdi) throws InvoiceManagerException {
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
		Cfdi entity = repository.save(mapper.getEntityFromCfdiDto(cfdi));
		Emisor emisor = mapper.getEntityFromEmisorDto(cfdi.getEmisor());
		emisor.setCfdi(entity);
		emisorRepository.save(emisor);
		Receptor receptor = mapper.getEntityFromReceptorDto(cfdi.getReceptor());
		receptor.setCfdi(entity);
		receptorRepository.save(receptor);
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
		if(cfdi.getRelacionado()!=null&&cfdi.getRelacionado().getRelacion()!=null) {
			Relacionado relacionado=mapper.getEntityFromRelacionadoDto(cfdi.getRelacionado());
			relacionado.setCfdi(entity);
			cfdiRelacionadoRepository.save(relacionado);
		}
		if (cfdi.getComplemento() != null && cfdi.getComplemento().getPagos() != null
				&& !cfdi.getComplemento().getPagos().isEmpty()) {
			for (CfdiPagoDto cfdiPagoDto : cfdi.getComplemento().getPagos()) {
				CfdiPago cfdiPago = mapper.getEntityFromCdfiPagosDto(cfdiPagoDto);
				cfdiPago.setCfdi(entity);
				cfdiPagoRepository.save(cfdiPago);
			}
		}
		CfdiDto dto = mapper.getCfdiDtoFromEntity(repository.findById(entity.getId()).orElse(null));
		dto.setConceptos(cfdi.getConceptos());
		return dto;
	}

	@Transactional(rollbackFor = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public CfdiDto updateCfdiBody(Integer id, CfdiDto cfdi) throws InvoiceManagerException {
		
		
		cfdi.setConceptos(mapper.getDtosFromConceptoEntities(conceptoRepository.findByCfdi(cfdi.getId())));
		if (cfdi.getConceptos() != null && !cfdi.getConceptos().isEmpty()) {
			for (ConceptoDto conceptoDto : cfdi.getConceptos()) {
				conceptoDto.setImpuestos(getImpuestosByConcepto(conceptoDto.getId()));
			}
		}
		
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
		repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("El cfdi con el prefolio %d no existe", id)));

		Emisor emisor = emisorRepository.findById(cfdi.getEmisor().getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, String
						.format("EL emisor %s no esta ligado al CFDI %d", cfdi.getEmisor().getRfc(), cfdi.getId())));
		emisor.setDireccion(cfdi.getEmisor().getDireccion());
		emisorRepository.save(emisor);

		Receptor receptor = receptorRepository.findById(cfdi.getReceptor().getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, String.format(
						"El receptor %s no esta ligado al CFDI %d", cfdi.getReceptor().getRfc(), cfdi.getId())));
		receptor.setDireccion(cfdi.getReceptor().getDireccion());
		receptor.setUsoCfdi(cfdi.getReceptor().getUsoCfdi());
		receptorRepository.save(receptor);
		repository.save(mapper.getEntityFromCfdiDto(cfdi));
		
		facturaService.recreatePdf(cfdi);
		return cfdi;
	}

	@Transactional(rollbackFor = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public void deleteCfdi(Integer id) throws InvoiceManagerException {
		Cfdi entity = repository.findById(id).orElseThrow(() -> new InvoiceManagerException("Error al obtener el Cfdi",
				String.format("El cfdi con el prefolio %d no existe", id), HttpStatus.NOT_FOUND.value()));
		Optional<Emisor> emisor = emisorRepository.findByIdCfdi(id);
		Optional<Receptor> receptor = receptorRepository.findByIdCfdi(id);
		List<CfdiPago> pagos = cfdiPagoRepository.findByCfdi(id);
		if (emisor.isPresent()) {
			emisorRepository.delete(emisor.get());
		}
		if (receptor.isPresent()) {
			receptorRepository.delete(receptor.get());
		}
		for(CfdiPago pago:pagos) {
			cfdiPagoRepository.delete(pago);
		}
		for (Concepto concepto : entity.getConceptos()) {
			for (Impuesto impuesto : concepto.getImpuestos()) {
				impuestoRepository.delete(impuesto);
			}
			for (Retencion retencion : concepto.getRetenciones()) {
				retencionRepository.delete(retencion);
			}
			conceptoRepository.delete(concepto);
		}
		repository.delete(entity);
	}

	@Transactional(rollbackFor = { InvoiceManagerException.class, DataAccessException.class,
			SQLException.class }, isolation = Isolation.READ_UNCOMMITTED)
	public CfdiDto insertNewConceptoToCfdi(Integer idCfdi, ConceptoDto newConcept) throws InvoiceManagerException {
		CfdiDto cfdi = mapper.getCfdiDtoFromEntity(
				repository.findById(idCfdi).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se ecnontro CFDI con prefolio %d", idCfdi))));
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
			imp.setBase(imp.getBase().setScale(6, RoundingMode.DOWN));
			imp.setImporte(imp.getImporte().setScale(6, RoundingMode.DOWN));
			impuestoRepository.save(imp);
		}
		for (RetencionDto retencion : newConcept.getRetenciones()) {
			Retencion ret = mapper.getEntityFromRetencionDto(retencion);
			ret.setConcepto(conceptoEntity);
			ret.setBase(ret.getBase().setScale(6, RoundingMode.DOWN));
			ret.setImporte(ret.getImporte().setScale(6, RoundingMode.DOWN));
			retencionRepository.save(ret);
		}
		facturaService.updateTotalAndSaldoFactura(cfdi.getId(), Optional.of(cfdi.getTotal()), Optional.empty());
		// 4.- recalculate pdf
		facturaService.recreatePdf(cfdi);
		return cfdi;
	}

	@Transactional(rollbackFor = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public CfdiDto removeConceptFromCfdi(Integer idCfdi, Integer conceptoId) throws InvoiceManagerException {
		CfdiDto cfdi = mapper.getCfdiDtoFromEntity(
				repository.findById(idCfdi).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se ecnontro CFDI con prefolio %d", idCfdi))));
		Concepto concepto = conceptoRepository.findById(conceptoId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se encontro concepto con id %d", conceptoId)));
		cfdi.getConceptos()
				.remove(cfdi.getConceptos().stream().filter(c -> c.getId().equals(conceptoId)).findFirst()
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
								String.format("No se encontro concepto con id %d", conceptoId))));
		validateCfdi(cfdi);
		recalculateCfdiAmmounts(cfdi);
		repository.save(mapper.getEntityFromCfdiDto(cfdi));// Update CFDI ammounts
		for (Impuesto impuesto : concepto.getImpuestos()) {
			impuestoRepository.delete(impuesto); // Deleting impuesto
		}
		for (Retencion retencion : concepto.getRetenciones()) {
			retencionRepository.delete(retencion); // Deleting retenciones
		}
		conceptoRepository.deleteById(concepto.getId());

		facturaService.updateTotalAndSaldoFactura(cfdi.getId(), Optional.of(cfdi.getTotal()), Optional.empty());
		// 4.- recalculate pdf
		facturaService.recreatePdf(cfdi);

		return cfdi;
	}

	@Transactional(rollbackFor = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public CfdiDto updateConceptoFromCfdi(Integer idCfdi, Integer conceptoId, ConceptoDto concepto)
			throws InvoiceManagerException {
		CfdiDto cfdi = mapper.getCfdiDtoFromEntity(
				repository.findById(idCfdi).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("No se ecnontro CFDI con prefolio %d", idCfdi))));
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
		// 3.- Update concepto and its references
		for (RetencionDto retencionDto : concepto.getRetenciones()) {
			Retencion ret = mapper.getEntityFromRetencionDto(retencionDto);
			ret.setConcepto(conceptoEntity);
			retencionRepository.save(ret);
		}

		facturaService.updateTotalAndSaldoFactura(cfdi.getId(), Optional.of(cfdi.getTotal()), Optional.empty());
		// 4.- recalculate pdf
		facturaService.recreatePdf(cfdi);

		return cfdi;
	}
	

	public void validateCfdi(CfdiDto cfdi) throws InvoiceManagerException {

		validator.checkNotNull(cfdi.getEmisor(), "Emisor info");
		validator.checkNotNull(cfdi.getEmisor().getRfc(), "RFC Emisor");
		validator.checkNotNull(cfdi.getEmisor().getNombre(), "Razon social emisor");
		validator.checkNotEmpty(cfdi.getEmisor().getNombre(), "Razon social emisor");
		validator.checkNotNull(cfdi.getEmisor().getDireccion(), "Dirección emisor");
		validator.checkNotEmpty(cfdi.getEmisor().getDireccion(), "Dirección emisor");
		validator.checkNotNull(cfdi.getEmisor().getRegimenFiscal(), "Regimen fiscal emisor");

		validator.checkNotNull(cfdi.getReceptor(), "Receptor info");
		validator.checkNotNull(cfdi.getReceptor().getRfc(), "RFC receptor");
		validator.checkNotNull(cfdi.getReceptor().getNombre(), "Razon social receptor");
		validator.checkNotEmpty(cfdi.getReceptor().getNombre(), "Razon social receptor");
		validator.checkNotNull(cfdi.getReceptor().getDireccion(), "Dirección receptor");
		validator.checkNotEmpty(cfdi.getReceptor().getDireccion(), "Dirección receptor");
		validator.checkNotNull(cfdi.getReceptor().getUsoCfdi(), "Uso CFDI receptor");

		if (!cfdi.getMetodoPago().equals(MetodosPagoEnum.PPD.name())
				&& !cfdi.getMetodoPago().equals(MetodosPagoEnum.PUE.name())) {
			throw new InvoiceManagerException("El metodo de pago de la factura solo puede ser PUE o PPD",
					"Metodo de pago invalido", HttpStatus.CONFLICT.value());
		}

		if (!cacheCatalogsService.getUsoCfdi(cfdi.getReceptor().getUsoCfdi()).isPresent()) {
			throw new InvoiceManagerException("Uso de CFDI invalido", "Uso de CFDI invalido",
					HttpStatus.CONFLICT.value());
		}

		if (!cacheCatalogsService.getFormaPago(cfdi.getFormaPago()).isPresent()) {
			throw new InvoiceManagerException(String.format("La forma de pago %s es invalida", cfdi.getFormaPago()),
					"Forma de pago invalida", HttpStatus.CONFLICT.value());
		}

		if (FormaPagoEnum.EFECTIVO.getClave().equals(cfdi.getMetodoPago())) {
			throw new InvoiceManagerException(
					"En pagos en efectivo el monto a facturar no debe de ser superior a 2000 pesos",
					"Metodo de pago invalido", HttpStatus.CONFLICT.value());
		}

		if (cfdi.getConceptos().isEmpty()) {
			throw new InvoiceManagerException("El CFDI no puede tener 0 conceptos", "Numero de comceptos invalido",
					HttpStatus.CONFLICT.value());
		} else {
			for (ConceptoDto conceptoDto : cfdi.getConceptos()) {
				validator.checkNotNull(conceptoDto.getDescripcion(), "Descripción de concepto");
				validator.checkNotEmpty(conceptoDto.getDescripcion(), "Descripción de concepto");
				validator.checkNotNull(conceptoDto.getCantidad(), "cantidad concepto");
				validator.checkNotNull(conceptoDto.getClaveProdServ(), "clave producto servicio ");
				validator.checkNotNegative(conceptoDto.getImporte(), "Importe");
				validator.checkNotNegative(conceptoDto.getCantidad(), "Cantidad");
				validator.checkNotNegative(conceptoDto.getValorUnitario(), "valor unitario");
			}
		}

		// TODO add more validations here
	}

	/**
	 * This method recalculate CFDI amounts based on SAT rounding rules, do not move
	 * or update this method without carefully review.
	 * 
	 * @param cfdi
	 */
	public CfdiDto recalculateCfdiAmmounts(CfdiDto cfdi) {

		// Importes, retenciones y traslados recalculo
		cfdi.getConceptos().forEach(a -> {
			a.setImporte(a.getValorUnitario().multiply(a.getCantidad()));
			a.getImpuestos().forEach(b -> {
				b.setImporte(b.getBase().multiply(b.getTasaOCuota()));
			});
			a.getRetenciones().forEach(b -> {
				b.setImporte(b.getBase().multiply(b.getTasaOCuota()));
			});
		});

		BigDecimal subtotal = cfdi.getConceptos().stream().map(c -> c.getValorUnitario().multiply(c.getCantidad()))
				.reduce(BigDecimal.ZERO, (i1, i2) -> i1.add(i2));
		BigDecimal retenciones = cfdi.getConceptos().stream()
				.map(i -> i.getRetenciones().stream().map(imp -> imp.getBase().multiply(imp.getTasaOCuota()))
						.reduce(BigDecimal.ZERO, (i1, i2) -> i1.add(i2)))// suma importe retencioness por concepto
				.reduce(BigDecimal.ZERO, (i1, i2) -> i1.add(i2)).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal impuestos = cfdi.getConceptos().stream()
				.map(i -> i.getImpuestos().stream().map(imp -> imp.getBase().multiply(imp.getTasaOCuota()))
						.reduce(BigDecimal.ZERO, (i1, i2) -> i1.add(i2)))// suma importe impuestos por concepto
				.reduce(BigDecimal.ZERO, (i1, i2) -> i1.add(i2)).setScale(2, BigDecimal.ROUND_HALF_UP);

		BigDecimal total = subtotal.add(impuestos).subtract(retenciones);
		log.info("Calculating cfdi values subtotal = {}, impuestos = {} , total = {}", subtotal, impuestos, total);

		cfdi.setImpuestosTrasladados(impuestos);
		cfdi.setImpuestosRetenidos(retenciones);
		cfdi.setSubtotal(subtotal.setScale(2, BigDecimal.ROUND_HALF_UP));
		cfdi.setTotal(total.setScale(2, BigDecimal.ROUND_HALF_UP));
		cfdi.setDescuento(BigDecimal.ZERO);// los descuentos no estan soportados
		
		return cfdi;
	}

}
