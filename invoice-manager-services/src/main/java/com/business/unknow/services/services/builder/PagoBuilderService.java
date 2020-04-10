package com.business.unknow.services.services.builder;

import org.springframework.stereotype.Service;

@Service
@Deprecated
public class PagoBuilderService {
	

//	public FacturaContext deletePagoPpdBuilder(FacturaDto factura, Pago pago, int id) throws InvoiceManagerException {
//		Pago pagoPadre = pagoRepository.findByFolio(factura.getFolioPadre()).stream().findFirst()
//				.orElseThrow(() -> new InvoiceManagerException("Pago a credito no encontrado",
//						String.format("Verificar consitencia de pagos del folio %s", factura.getFolioPadre()),
//						HttpStatus.SC_NOT_FOUND));
//		PagoDto pagoDto = pagoMapper.getPagoDtoFromEntity(pago);
//		FacturaContext context = new FacturaContextBuilder().setPagos(Arrays.asList(pagoDto)).setCurrentPago(pagoDto)
//				.setFacturaDto(factura)
//				.setPagoCredito(pagoMapper.getPagoDtoFromEntity(pagoPadre)).build();
//		return context;
//	}
//	
//	public FacturaContext deletePagoPueBuilder(FacturaDto factura, Pago pago, int id) throws InvoiceManagerException {
//		List<Pago> pagos = pagoRepository.findByFolio(factura.getFolio());
//		Optional<Pago> pagoCredito = pagos.stream()
//				.filter(p -> p.getFormaPago().equals(FormaPagoEnum.CREDITO.getPagoValue())).findFirst();
//		return new FacturaContextBuilder().setCurrentPago(pagoMapper.getPagoDtoFromEntity(pago))
//				.setPagos(pagoMapper.getPagosDtoFromEntities(pagos))
//				.setFacturaDto(factura)
//				.setPagoCredito(pagoCredito.isPresent() ? pagoMapper.getPagoDtoFromEntity(pagoCredito.get()) : null)
//				.build();
//	}

	
}
