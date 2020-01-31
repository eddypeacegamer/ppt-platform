package com.business.unknow.services.services.builder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.commons.builder.FacturaContextBuilder;
import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.PagoRepository;

@Service
public class PagoBuilderService {
	
	@Autowired
	private PagoRepository pagoRepository;
	
	@Autowired
	private PagoMapper pagoMapper;
	
	@Autowired
	private FacturaMapper facturaMapper;

	public FacturaContext deletePagoPpdBuilder(Factura factura, Pago pago, int id) throws InvoiceManagerException {
		Pago pagoPadre = pagoRepository.findByFolio(factura.getFolioPadre()).stream().findFirst()
				.orElseThrow(() -> new InvoiceManagerException("Pago a credito no encontrado",
						String.format("Verificar consitencia de pagos del folio %s", factura.getFolioPadre()),
						HttpStatus.SC_NOT_FOUND));
		PagoDto pagoDto = pagoMapper.getPagoDtoFromEntity(pago);
		FacturaContext context = new FacturaContextBuilder().setPagos(Arrays.asList(pagoDto)).setCurrentPago(pagoDto)
				.setFacturaDto(facturaMapper.getFacturaDtoFromEntity(factura))
				.setPagoCredito(pagoMapper.getPagoDtoFromEntity(pagoPadre)).build();
		return context;
	}
	
	public FacturaContext deletePagoPueBuilder(Factura factura, Pago pago, int id) throws InvoiceManagerException {
		List<Pago> pagos = pagoRepository.findByFolio(factura.getFolio());
		Optional<Pago> pagoCredito = pagos.stream()
				.filter(p -> p.getFormaPago().equals(FormaPagoEnum.CREDITO.getPagoValue())).findFirst();
		return new FacturaContextBuilder().setCurrentPago(pagoMapper.getPagoDtoFromEntity(pago))
				.setPagos(pagoMapper.getPagosDtoFromEntities(pagos))
				.setFacturaDto(facturaMapper.getFacturaDtoFromEntity(factura))
				.setPagoCredito(pagoCredito.isPresent() ? pagoMapper.getPagoDtoFromEntity(pagoCredito.get()) : null)
				.build();
	}
	
	public void buildUpdatePagoEntity(Pago entity,PagoDto pago) {
		entity.setComentarioPago(pago.getComentarioPago());
		entity.setRevision1(pago.getRevision1());
		entity.setRevision2(pago.getRevision2());
		entity.setUltimoUsuario(pago.getUltimoUsuario());
		entity.setStatusPago(pago.getStatusPago());
	}
	
}
