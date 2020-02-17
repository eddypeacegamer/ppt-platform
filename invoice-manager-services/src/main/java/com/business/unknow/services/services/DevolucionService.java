/**
 * 
 */
package com.business.unknow.services.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.commons.validator.DevolucionValidator;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.services.DevolucionDto;
import com.business.unknow.model.dto.services.PagoDevolucionDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Client;
import com.business.unknow.services.entities.Devolucion;
import com.business.unknow.services.entities.PagoDevolucion;
import com.business.unknow.services.mapper.DevolucionMapper;
import com.business.unknow.services.mapper.PagoDevolucionMapper;
import com.business.unknow.services.repositories.ClientRepository;
import com.business.unknow.services.repositories.PagoDevolucionRepository;
import com.business.unknow.services.repositories.facturas.DevolucionRepository;
import com.business.unknow.services.services.builder.DevolucionesBuilderService;
import com.business.unknow.services.services.evaluations.DevolucionEvaluatorService;
import com.business.unknow.services.services.executor.DevolucionExecutorService;

/**
 * @author ralfdemoledor
 *
 */
@Service
public class DevolucionService {

	@Autowired
	private DevolucionRepository repository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private PagoDevolucionRepository pagoDevolucionRepository;
	
	@Autowired
	private DevolucionMapper mapper;

	@Autowired
	private PagoDevolucionMapper pagoDevolucionMapper;

	@Autowired
	private DevolucionesBuilderService devolucionesBuilderService;

	@Autowired
	private DevolucionEvaluatorService devolucionEvaluatorService;

	@Autowired
	private DevolucionExecutorService devolucionExecutorService;

	private DevolucionValidator devolucionValidator = new DevolucionValidator();

	public Page<DevolucionDto> getDevolucionesByParams(Optional<String> receptorType, Optional<String> idReceptor,
			int page, int size) {
		Page<Devolucion> result = null;
		if (!receptorType.isPresent() && !idReceptor.isPresent()) {
			result = repository.findAll(PageRequest.of(page, size));
		} else {
			result = repository.findDevolucionesByParamsPage(receptorType.get(), idReceptor.get(), "D",
					PageRequest.of(page, size,Sort.by("fechaCreacion").descending()));
		}
		return new PageImpl<>(mapper.getDevolucionesDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public List<DevolucionDto> getDevolucionesPorReceptor(String tipoReceptor, String idReceptor) {
		return mapper
				.getDevolucionesDtoFromEntities(repository.findDevolucionesByParams(tipoReceptor, idReceptor, "D"));
	}

	public Double getMontoDevoluciones(String tipoReceptor, String idReceptor) {
		Double result = repository.findMontoByParams(tipoReceptor, idReceptor);
		return (result == null) ? 0.0 : result;
	}

	public DevolucionDto insertDevolution(DevolucionDto devolucion) {
		return mapper.getDevolucionDtoFromEntity(repository.save(mapper.getEntityFromDevolucionDto(devolucion)));
	}

	public List<PagoDevolucionDto> solicitudDevoluciones(List<PagoDevolucionDto> solicitudes)
			throws InvoiceManagerException {
		List<PagoDevolucionDto> pagos = new ArrayList<>();
		for (PagoDevolucionDto dto : solicitudes) {
			pagos.add(solicitudDevolucion(dto));
		}
		return pagos;
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDevolucionDto solicitudDevolucion(PagoDevolucionDto dto) throws InvoiceManagerException {
		devolucionValidator.validatePostDevolucionPago(dto);
		dto = devolucionesBuilderService.buildDevolucionPago(dto);
		Devolucion dev = repository.save(devolucionesBuilderService.buildPagoDevolucion(dto));
		dto.setIdDevolucion(dev.getId());
		return pagoDevolucionMapper.getPagoDevolucionDtoFromEntity(
				pagoDevolucionRepository.save(pagoDevolucionMapper.getEntityFromPagoDevolucionDto(dto)));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public PagoDevolucionDto solicitudDevolucionUpdate(PagoDevolucionDto dto,Integer id) throws InvoiceManagerException {
		devolucionValidator.validatePostDevolucionPago(dto);
		Optional<PagoDevolucion> pagoDevolucion =pagoDevolucionRepository.findById(id);
		if(pagoDevolucion.isPresent()) {
			pagoDevolucion.get().setStatus(dto.getStatus());
			pagoDevolucion.get().setAutorizador(dto.getAutorizador());
			pagoDevolucion.get().setComentarios(dto.getComentarios());
			if("RECHAZADO".equalsIgnoreCase(dto.getStatus())) {
				repository.deleteById(dto.getIdDevolucion()); //returns ammount to account
			}else {
				//TODO include here expenses in amounts table
				pagoDevolucion.get().setRfcEmpresa(dto.getRfcEmpresa());
				pagoDevolucion.get().setCuentaPago(dto.getCuentaPago());
			}
			return pagoDevolucionMapper.getPagoDevolucionDtoFromEntity(
					pagoDevolucionRepository.save(pagoDevolucion.get()));
		}else {
			throw new InvoiceManagerException("The Pago devolucion does not exist",
					String.format("The Pago devolucion does not exist %d", id),
					HttpStatus.BAD_REQUEST.value());
		}
	}

	public void generarDevolucionesPorPago(FacturaDto facturaDto, PagoDto pagoDto) throws InvoiceManagerException {
		Client client = clientRepository.findByRfc(facturaDto.getRfcRemitente())
				.orElseThrow(() -> new InvoiceManagerException("The type of document not supported",
						String.format("The type of document %s not valid", facturaDto.getTipoDocumento()),
						HttpStatus.BAD_REQUEST.value()));
		FacturaContext context;
		BigDecimal baseComisiones;
		switch (TipoDocumentoEnum.findByDesc(facturaDto.getTipoDocumento())) {
		case FACTURA:
			baseComisiones = facturaDto.getCfdi().getTotal().subtract(facturaDto.getCfdi().getSubtotal());
			context = devolucionesBuilderService.buildFacturaContextForPueDevolution(facturaDto, pagoDto);
			devolucionEvaluatorService.devolucionPueValidation(context);
			devolucionExecutorService.executeDevolucionForPue(context, client, facturaDto.getCfdi().getTotal(),
					baseComisiones);
			break;
		case COMPLEMENTO:
			context = devolucionesBuilderService.buildFacturaContextForComplementoDevolution(facturaDto, pagoDto);
			baseComisiones = context.getFacturaPadreDto().getCfdi().getTotal()
					.subtract(context.getFacturaPadreDto().getCfdi().getSubtotal())
					.divide(context.getFacturaPadreDto().getCfdi().getTotal(), 2, RoundingMode.HALF_UP);
			devolucionEvaluatorService.devolucionPpdValidation(context);
			devolucionExecutorService.executeDevolucionForPpd(context, client,
					context.getFacturaPadreDto().getCfdi().getTotal(), baseComisiones);
			break;
		default:
			throw new InvoiceManagerException("The type of document not supported",
					String.format("The type of document %s not valid", facturaDto.getTipoDocumento()),
					HttpStatus.BAD_REQUEST.value());
		}
	}
	
	public Page<PagoDevolucionDto> getPagoDevolucionesByParams(String status,String formaPago,String beneficiario,String tipoReceptor, String idReceptor,int page, int size) {
		Page<PagoDevolucion> result = new PageImpl<>(new ArrayList<>());
		if (tipoReceptor.length()>0 && idReceptor.length()>0) {
			result = pagoDevolucionRepository.findByTipoReceptorAndReceptor(tipoReceptor, idReceptor, PageRequest.of(page, size,Sort.by("fechaCreacion").descending()));
		} else if(status.length()>0){
			result = pagoDevolucionRepository.findByStatusAndParams(status, String.format("%%%s%%", formaPago),  String.format("%%%s%%", beneficiario), String.format("%%%s%%", idReceptor),String.format("%%%s%%", tipoReceptor), PageRequest.of(page, size,Sort.by("fechaCreacion").descending()));
		}else {
			result = pagoDevolucionRepository.findByParams(String.format("%%%s%%", formaPago),  String.format("%%%s%%", beneficiario), String.format("%%%s%%", idReceptor),String.format("%%%s%%", tipoReceptor), PageRequest.of(page, size,Sort.by("fechaCreacion").descending()));
		}
		return new PageImpl<>(pagoDevolucionMapper.getPagoDevolucionesDtoFromEntities(result.getContent()), result.getPageable(), result.getTotalElements());
	}

}
