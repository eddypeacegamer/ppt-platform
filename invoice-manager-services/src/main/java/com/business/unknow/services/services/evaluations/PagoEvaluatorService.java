package com.business.unknow.services.services.evaluations;

import java.util.Arrays;
import java.util.List;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.business.unknow.commons.builder.FacturaBuilder;
import com.business.unknow.commons.builder.FacturaContextBuilder;
import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.enums.MetodosPagoEnum;
import com.business.unknow.enums.ResourceFileEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.enums.TipoRecursoEnum;
import com.business.unknow.model.PagoDto;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.rules.suites.ComplementoSuite;
import com.business.unknow.rules.suites.PagoPpdSuite;
import com.business.unknow.rules.suites.PagoPueSuite;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.entities.factura.Factura;

@Service
public class PagoEvaluatorService extends AbstractFacturaServiceEvaluator {

	@Autowired
	private PagoPpdSuite pagoPpdSuite;

	@Autowired
	private PagoPueSuite pagoPueSuite;

	@Autowired
	private ComplementoSuite complementoSuite;

	@Autowired
	private RulesEngine rulesEngine;

	public PagoDto validatePagoCreation(String folio, PagoDto pagoDto) throws InvoiceManagerException {
		Factura factura = repository.findByFolio(folio)
				.orElseThrow(() -> new InvoiceManagerException("No se encuentra la factura en el sistema",
						String.format("Folio with the name %s not found", folio), HttpStatus.NOT_FOUND.value()));
		FacturaContext facturaContext;
		pagoDto.setCreateUser(pagoDto.getUltimoUsuario());
		if (factura.getMetodoPago().equals(MetodosPagoEnum.PPD.getNombre())) {
			facturaContext=validatePagoPpdCreation(folio, pagoDto, factura);
		} else if (factura.getMetodoPago().equals(MetodosPagoEnum.PUE.getNombre())) {
			facturaContext=validatePagoPueCreation(folio, pagoDto);
		} else {
			throw new InvoiceManagerException("Metodo de pago no soportado",
					String.format("El metodo de pago %s no es valido", factura.getMetodoPago()),
					HttpStatus.BAD_REQUEST.value());
		}
		if (pagoDto.getDocumento() != null) {
			createResourceFile(pagoDto.getDocumento(), pagoDto.getFolio(), TipoRecursoEnum.PAGO.getDescripcion(),
					ResourceFileEnum.IMAGEN.getDescripcion());
		}
		return mapper.getPagoDtoFromEntity(
				pagoRepository.save(mapper.getEntityFromPagoDto(facturaContext.getPagos().stream().findFirst().get())));
	}

	private FacturaContext validatePagoPpdCreation(String folio, PagoDto pagoDto, Factura factura)
			throws InvoiceManagerException {
		Factura facturaPadre = repository.findByFolio(folio)
				.orElseThrow(() -> new InvoiceManagerException("No se encuentra la factura en el sistema",
						String.format("Folio with the name %s not found", folio), HttpStatus.NOT_FOUND.value()));
		List<Pago> pagos = pagoRepository.findByFolioPadre(pagoDto.getFolioPadre());
		Pago pagoPadre = pagos.stream().filter(p -> p.getFolio().equals(folio)).findFirst()
				.orElseThrow(() -> new InvoiceManagerException("Pago a credito no encontrado",
						String.format("Verificar consitencia de pagos del folio %s", folio),
						HttpStatus.NOT_FOUND.value()));
		FacturaContext facturaContext = new FacturaContextBuilder().setPagos(Arrays.asList(pagoDto))
				.setFacturaPadreDto(mapper.getFacturaDtoFromEntity(facturaPadre))
				.setPagoCredito(pagoMapper.getPagoDtoFromEntity(pagoPadre)).build();
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(pagoPpdSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		return buildFacturaContextPagoPpdCreation(folio, facturaContext, pagoDto);
	}

	private FacturaContext validatePagoPueCreation(String folio, PagoDto pagoDto) throws InvoiceManagerException {
		FacturaContext facturaContext = buildFacturaContextPagoPueCreation(folio, pagoDto);
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(pagoPueSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		return facturaContext;
	}

	private FacturaContext buildFacturaContextPagoPpdCreation(String folio, FacturaContext facturaContext,
			PagoDto pagoDto) throws InvoiceManagerException {
		FacturaBuilder facturaBuilder = new FacturaBuilder()
				.setFolioPadre(facturaContext.getFacturaPadreDto().getFolio())
				.setPackFacturacion(facturaContext.getFacturaPadreDto().getPackFacturacion())
				.setMetodoPago(facturaContext.getFacturaPadreDto().getMetodoPago())
				.setRfcEmisor(facturaContext.getFacturaPadreDto().getRfcEmisor())
				.setRfcRemitente(facturaContext.getFacturaPadreDto().getRfcRemitente())
				.setRazonSocialEmisor(facturaContext.getFacturaPadreDto().getRazonSocialEmisor())
				.setRazonSocialRemitente(facturaContext.getFacturaPadreDto().getRazonSocialRemitente())
				.setTotal(pagoDto.getMonto()).setTipoDocumento(TipoDocumentoEnum.COMPLEMENTO.getDescripcion())
				.setFormaPago(FormaPagoEnum.findByDesc(pagoDto.getFormaPago()).getClave());
		facturaContext.setFacturaDto(facturaBuilder.build());
		facturaContext = createNewComplemento(facturaContext, folio);
		pagoDto.setFolio(facturaContext.getFacturaDto().getFolio());
		pagoDto.setFolioPadre(facturaContext.getFacturaDto().getFolioPadre());
		facturaContext.setPagos(Arrays.asList(pagoDto));
		facturaContext.getPagoCredito().setMonto(facturaContext.getPagoCredito().getMonto() - pagoDto.getMonto());
		pagoRepository.save(pagoMapper.getEntityFromPagoDto(facturaContext.getPagoCredito()));
		return facturaContext;
	}

	private FacturaContext buildFacturaContextPagoPueCreation(String folio, PagoDto pagoDto) {
		return new FacturaContextBuilder().setPagos(Arrays.asList(pagoDto)).build();
	}

	public FacturaContext createNewComplemento(FacturaContext facturaContext, String folio) throws InvoiceManagerException {
		Facts facts = new Facts();
		facturaDefaultValues.assignaDefaultsComplemento(facturaContext.getFacturaDto());
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(complementoSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		facturaContext
				.setFacturaDto(mapper.getFacturaDtoFromEntity(repository.save(mapper.getEntityFromFacturaDto(facturaContext.getFacturaDto()))));
		return facturaContext;
	}
}
