/**
 * 
 */
package com.business.unknow.services.services;

import java.sql.SQLException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.business.unknow.model.DevolucionDto;
import com.business.unknow.model.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.services.entities.Devolucion;
import com.business.unknow.services.mapper.DevolucionMapper;
import com.business.unknow.services.repositories.facturas.DevolucionRepository;
import com.business.unknow.services.services.evaluations.DevolucionEvaluatorService;

/**
 * @author ralfdemoledor
 *
 */
@Service
public class DevolucionService {

	@Autowired
	private DevolucionRepository repository;

	@Autowired
	private DevolucionEvaluatorService service;

	@Autowired
	private DevolucionMapper mapper;

	@Autowired
	private FacturaService facturaService;

	public Page<DevolucionDto> getDevolucionesByParams(Optional<String> receptorType, Optional<String> idReceptor,
			Optional<String> statusPay, int page, int size) {
		Page<Devolucion> result = null;
		if (!receptorType.isPresent() && !idReceptor.isPresent() && (!statusPay.isPresent()
				|| statusPay.get().isEmpty())) {
			result = repository.findAll(PageRequest.of(page, size));
		} else if (receptorType.isPresent() && idReceptor.isPresent() && (!statusPay.isPresent()
				|| statusPay.get().isEmpty())) {
			result = repository.findDevolucionesByParams(receptorType.get(), idReceptor.get(),
					PageRequest.of(page, size));
		} else {
			result = repository.findDevolucionesByParamsWithPayStatus(receptorType.get(), idReceptor.get(),
					String.format("%%%s%%", statusPay.get()), PageRequest.of(page, size));
		}
		return new PageImpl<>(mapper.getDevolucionesDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

	public DevolucionDto insertDevolution(DevolucionDto devolucion) {
		devolucion.setStatusPago("VALIDACION");
		return mapper.getDevolucionDtoFromEntity(repository.save(mapper.getEntityFromDevolucionDto(devolucion)));
	}

	@Transactional(rollbackOn = { InvoiceManagerException.class, DataAccessException.class, SQLException.class })
	public void generarDevolucionesPorPago(PagoDto pagoDto) throws InvoiceManagerException {
		FacturaDto facturaDto = facturaService.getfacturaByFolio(pagoDto.getFolio());
		service.generarDevolucionesPorPago(facturaDto,pagoDto);
	}

}
