package com.business.unknow.services.services.evaluations;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.Constants.FacturaComplemento;
import com.business.unknow.commons.builder.FacturaContextBuilder;
import com.business.unknow.enums.PackFacturarionEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.rules.suites.CancelacionSuite;
import com.business.unknow.rules.suites.ComplementoSuite;
import com.business.unknow.rules.suites.TimbradoSuite;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.entities.cfdi.Cfdi;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.services.executor.FacturacionModernaExecutor;
import com.business.unknow.services.services.executor.SwSapinsExecutorService;
import com.business.unknow.services.services.translators.FacturaTranslator;

@Service
public class TimbradoEvaluatorService extends AbstractFacturaServiceEvaluator {

	@Autowired
	private ComplementoSuite complementoSuite;

	@Autowired
	private CancelacionSuite cancelacionSuite;

	@Autowired
	private TimbradoSuite FacturarSuite;

	@Autowired
	private FacturaTranslator facturaTranslator;

	@Autowired
	private SwSapinsExecutorService swSapinsExecutorService;

	@Autowired
	private FacturacionModernaExecutor facturacionModernaExecutor;

	@Autowired
	private RulesEngine rulesEngine;

	public FacturaContext facturaComplementoValidation(FacturaContext facturaContext) throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(complementoSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		return facturaContext;
	}

	public FacturaContext facturaCancelacionValidation(FacturaContext facturaContext) throws InvoiceManagerException {
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(cancelacionSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		switch (PackFacturarionEnum.findByNombre(facturaContext.getFacturaDto().getPackFacturacion())) {
		case SW_SAPIENS:
			swSapinsExecutorService.cancelarFactura(facturaContext);
			break;
		case FACTURACION_MODERNA:
			facturacionModernaExecutor.cancelarFactura(facturaContext);
			break;
		default:
			throw new InvoiceManagerException("Pack not supported yet", "Validate with programers",
					HttpStatus.SC_BAD_REQUEST);
		}
		updateCanceladoValues(facturaContext);
		return facturaContext;
	}

	public FacturaContext facturaTimbradoValidation(FacturaDto facturaDto, String folio)
			throws InvoiceManagerException {
		FacturaContext facturaContext = buildFacturaContextTimbrado(facturaDto, folio);
		Facts facts = new Facts();
		facts.put("facturaContext", facturaContext);
		rulesEngine.fire(FacturarSuite.getSuite(), facts);
		validateFacturaContext(facturaContext);
		switch (TipoDocumentoEnum.findByDesc(facturaContext.getTipoDocumento())) {
		case FACRTURA:
			facturaContext = facturaTranslator.translateFactura(facturaContext);
			break;
		case COMPLEMENTO:
			facturaContext = facturaTranslator.translateComplemento(facturaContext);
			break;
		default:
			throw new InvoiceManagerException("The type of document not supported",
					String.format("The type of document %s not valid", facturaContext.getTipoDocumento()),
					HttpStatus.SC_BAD_REQUEST);
		}
		switch (PackFacturarionEnum.findByNombre(facturaContext.getFacturaDto().getPackFacturacion())) {
		case SW_SAPIENS:
			swSapinsExecutorService.stamp(facturaContext);
			break;
		case FACTURACION_MODERNA:
			facturacionModernaExecutor.stamp(facturaContext);
			break;
		default:
			throw new InvoiceManagerException("Pack not supported yet", "Validate with programers",
					HttpStatus.SC_BAD_REQUEST);
		}
		updateFacturaAndCfdiValues(facturaContext);
		return facturaContext;
	}

	private FacturaContext buildFacturaContextTimbrado(FacturaDto facturaDto, String folio)
			throws InvoiceManagerException {
		Optional<Factura> folioPadreEntity = repository.findByFolio(facturaDto.getFolioPadre());
		Factura folioEnity = repository.findByFolio(folio)
				.orElseThrow(() -> new InvoiceManagerException("Folio not found",
						String.format("Folio with the name %s not found", facturaDto.getFolio()),
						HttpStatus.SC_NOT_FOUND));
		Optional<Cfdi> cfdi = cfdiRepository.findByFolio(folio);
		EmpresaDto empresaDto = empresaMapper
				.getEmpresaDtoFromEntity(empresaRepository.findByRfc(facturaDto.getRfcEmisor())
						.orElseThrow(() -> new InvoiceManagerException("Empresa not found",
								String.format("La empresa con el rfc no existe", facturaDto.getRfcEmisor()),
								HttpStatus.SC_NOT_FOUND)));
		Optional<Pago> pagoCredito = pagoRepository.findByFolioAndFormaPagoAndComentarioPago(facturaDto.getFolioPadre(),
				FacturaComplemento.FORMA_PAGO, FacturaComplemento.PAGO_COMENTARIO);
		FacturaDto currentFacturaDto = mapper.getFacturaDtoFromEntity(folioEnity);
		getEmpresaFiles(empresaDto, currentFacturaDto);
		currentFacturaDto.setPackFacturacion(facturaDto.getPackFacturacion());
		return new FacturaContextBuilder().setFacturaDto(currentFacturaDto)
				.setPagos(mapper.getPagosDtoFromEntity(pagoRepository.findByFolio(folio)))
				.setCfdi(cfdi.isPresent() ? cfdiMapper.getCfdiDtoFromEntity(cfdi.get()) : null)
				.setEmpresaDto(empresaDto)
				.setPagoCredito(pagoCredito.isPresent() ? mapper.getPagoDtoFromEntity(pagoCredito.get()) : null)
				.setFacturaPadreDto(
						folioPadreEntity.isPresent() ? mapper.getFacturaDtoFromEntity(folioPadreEntity.get()) : null)
				.setTipoFactura(facturaDto.getMetodoPago()).setTipoDocumento(facturaDto.getTipoDocumento())
				.setCtdadComplementos(repository
						.findByFolioPadre(
								facturaDto.getFolioPadre() != null ? facturaDto.getFolioPadre() : facturaDto.getFolio())
						.size())
				.build();
	}

}
