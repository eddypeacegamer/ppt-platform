package com.business.unknow.services.services;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.business.unknow.commons.validator.FacturaValidator;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.CfdiMapper;
import com.business.unknow.services.mapper.FacturaMapper;
import com.business.unknow.services.repositories.facturas.CfdiRepository;
import com.business.unknow.services.repositories.facturas.FacturaRepository;
import com.business.unknow.services.repositories.facturas.PagoRepository;
import com.business.unknow.services.services.evaluations.CfdiEvaluatorService;
import com.business.unknow.services.services.evaluations.ConceptoEvaluatorService;
import com.business.unknow.services.services.evaluations.FacturaEvaluatorService;
import com.business.unknow.services.services.evaluations.PagoEvaluatorService;
import com.business.unknow.services.services.evaluations.TimbradoEvaluatorService;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository repository;

	@Autowired
	private CfdiRepository cfdiRepository;

	@Autowired
	private PagoRepository pagoRepository;

	@Autowired
	private FacturaMapper mapper;

	@Autowired
	private CfdiMapper cfdiMapper;

	@Autowired
	private TimbradoEvaluatorService timbradoServiceEvaluator;

	@Autowired
	private FacturaEvaluatorService facturaServiceEvaluator;

	@Autowired
	private PagoEvaluatorService pagoEvaluatorService;
	
	@Autowired
	private ConceptoEvaluatorService conceptoEvaluatorService;

	@Autowired
	private CfdiEvaluatorService cfdiEvaluatorService;
	
	

	private FacturaValidator validator = new FacturaValidator();

	//FACTURAS
	public Page<FacturaDto> getFacturasByParametros( Optional<String> folio,Optional<String> solicitante, 
			String lineaEmisor ,Optional<String> status,Date since, Date to,String rfcEmisor,String rfcRemitente, int page, int size) {
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		Page<Factura> result;
		if (folio.isPresent()) {
			result = repository.findByFolioIgnoreCaseContaining(folio.get(), PageRequest.of(0, 10));
		} else if ( solicitante.isPresent()) {
			if(status.isPresent() && status.get().length()>0) {
				result = repository.findBySolicitanteAndStatusWithParams(solicitante.get(), status.get(), start, end,String.format("%%%s%%",rfcEmisor),String.format("%%%s%%",rfcRemitente), PageRequest.of(page, size));
			}else {
				result = repository.findBySolicitanteWithParams(solicitante.get(), start, end,String.format("%%%s%%",rfcEmisor),String.format("%%%s%%",rfcRemitente), PageRequest.of(page, size));
			}
			
		} else {
			if(status.isPresent() && status.get().length()>0) {
				result = repository.findByLineaEmisorAndStatusWithParams(lineaEmisor, status.get(), start, end, String.format("%%%s%%",rfcEmisor),String.format("%%%s%%",rfcRemitente),PageRequest.of(page, size));
			}else {
				result = repository.findByLineaEmisorWithParams(lineaEmisor, start, end, String.format("%%%s%%",rfcEmisor),String.format("%%%s%%",rfcRemitente),PageRequest.of(page, size));
			}
			
		}
		return new PageImpl<>(mapper.getFacturaDtosFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public FacturaDto getfacturaByFolio(String folio) {
		FacturaDto dto = mapper.getFacturaDtoFromEntity(
				repository.findByFolio(folio).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", folio))));
		Optional<Cfdi> cfdi = cfdiRepository.findByFolio(folio);
		dto.setCfdi(cfdi.isPresent() ? cfdiMapper.getCfdiDtoFromEntity(cfdi.get()) : null);
		return dto;
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public FacturaDto insertNewFacturaWithDetail(FacturaDto facturaDto) throws InvoiceManagerException {
		validator.validatePostFacturaWithDetail(facturaDto);
		return facturaServiceEvaluator.facturaEvaluation(facturaDto).getFacturaDto();
	}

	public FacturaDto updateFactura(FacturaDto factura, String folio) {
		if (repository.findByFolio(folio).isPresent()) {
			Factura entity = mapper.getEntityFromFacturaDto(factura);
			return mapper.getFacturaDtoFromEntity(repository.save(entity));
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("La factura con el folio %s no existe", folio));
		}
	}
	
	//COMPLEMNENTOS
	public List<FacturaDto> getComplementos(String folioPadre) {
		return mapper.getFacturaDtosFromEntities(repository.findComplementosByFolioPadre(folioPadre));
	}
	
	//CFDI
	public CfdiDto getFacturaCdfi(String folio) throws InvoiceManagerException {
		Cfdi entity = cfdiRepository.findByFolio(folio)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("La factura con el folio %s no existe", folio)));
		return cfdiMapper.getCfdiDtoFromEntity(entity);
	}
	
	public CfdiDto insertNewCfdi(String folio, CfdiDto cfdi) {
		return cfdiEvaluatorService.insertNewCfdi(folio, cfdi);
	}

	public CfdiDto updateFacturaCfdi(String folio, Integer id, CfdiDto dto) throws InvoiceManagerException {
		validator.validatePostCfdi(dto, folio);
		cfdiRepository.findById(id).orElseThrow(() -> new InvoiceManagerException("Error al obtener el Cfdi",
				String.format("El cfdi con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value()));
		return cfdiMapper.getCfdiDtoFromEntity(cfdiRepository.save(cfdiMapper.getEntityFromCfdiDto(dto)));
	}

	public void deleteFacturaCfdi(String folio, Integer id) throws InvoiceManagerException {
		Cfdi entity = cfdiRepository.findById(id)
				.orElseThrow(() -> new InvoiceManagerException("Error al obtener el Cfdi",
						String.format("El cfdi con el folio %s no existe", folio), HttpStatus.NOT_FOUND.value()));
		cfdiRepository.delete(entity);
	}
	
	//TIMBRADO
	public FacturaContext timbrarFactura(String folio, FacturaDto facturaDto) throws InvoiceManagerException {
		validator.validateTimbrado(facturaDto, folio);
		return timbradoServiceEvaluator.facturaTimbradoValidation(facturaDto, folio);
	}

	public FacturaContext cancelarFactura(String folio, FacturaDto facturaDto) throws InvoiceManagerException {
		validator.validateTimbrado(facturaDto, folio);
		return timbradoServiceEvaluator.facturaCancelacionValidation(facturaDto,folio);
	}
	
	//PAGOS
	public List<PagoDto> getPagos(String folio) {
		return mapper.getPagosDtoFromEntity(pagoRepository.findByFolioPadre(folio));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto insertNewPago(String folio, PagoDto pagoDto) throws InvoiceManagerException {
		return pagoEvaluatorService.validatePagoCreation(folio,pagoDto);
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDto updatePago(PagoDto pago, Integer id) throws InvoiceManagerException {
		return  pagoEvaluatorService.validatePagoUpdate(pago,id);
		
	}

	public void deletePago(Integer id) throws InvoiceManagerException {
		pagoEvaluatorService.validatePagoDeleting(id);
	}
	
	
	//CONCEPTOS
	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public ConceptoDto insertConcepto(String folio, ConceptoDto conceptoDto) throws InvoiceManagerException {
		return conceptoEvaluatorService.validateConceptoCreation(conceptoDto,folio);
	}
	
	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public void deleteconcepto(int id,String folio) throws InvoiceManagerException {
		conceptoEvaluatorService.validateConceptoDelete(id, folio);
	}

}
