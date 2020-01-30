package com.business.unknow.services.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.unknow.Constants.ComplementoPpdDefaults;
import com.business.unknow.commons.builder.CfdiComplementoPagoBuilder;
import com.business.unknow.commons.builder.CfdiDtoBuilder;
import com.business.unknow.commons.builder.ConceptoDtoBuilder;
import com.business.unknow.commons.builder.FacturaBuilder;
import com.business.unknow.commons.builder.FacturaContextBuilder;
import com.business.unknow.enums.FormaPagoEnum;
import com.business.unknow.enums.TipoDocumentoEnum;
import com.business.unknow.model.context.FacturaContext;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.model.dto.cfdi.ComplementoDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.model.error.InvoiceManagerException;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.entities.factura.Factura;
import com.business.unknow.services.mapper.EmpresaMapper;
import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.mapper.factura.FacturaMapper;
import com.business.unknow.services.repositories.EmpresaRepository;
import com.business.unknow.services.repositories.PagoRepository;
import com.business.unknow.services.repositories.facturas.FacturaRepository;

@Service
public class FacturaBuilderService {

	@Autowired
	protected FacturaRepository repository;

	@Autowired
	protected PagoRepository pagoRepository;

	@Autowired
	protected EmpresaRepository empresaRepository;

	@Autowired
	protected FacturaMapper mapper;

	@Autowired
	protected PagoMapper pagoMapper;

	@Autowired
	protected EmpresaMapper empresaMapper;

	public FacturaContext buildFacturaContextPagoPpdCreation(PagoDto pagoDto, String folio)
			throws InvoiceManagerException {
		Factura facturaPadre = repository.findByFolio(folio)
				.orElseThrow(() -> new InvoiceManagerException("No se encuentra la factura en el sistema",
						String.format("Folio with the name %s not found", folio), HttpStatus.SC_NOT_FOUND));
		List<Pago> pagos = pagoRepository.findByFolioPadre(facturaPadre.getFolio());
		Pago pagoPadre = pagos.stream().filter(p -> p.getFolio().equals(folio)).findFirst()
				.orElseThrow(() -> new InvoiceManagerException("Pago a credito no encontrado",
						String.format("Verificar consitencia de pagos del folio %s", folio), HttpStatus.SC_NOT_FOUND));
		return new FacturaContextBuilder().setPagos(pagoMapper.getPagosDtoFromEntities(pagos))
				.setEmpresaDto(
						empresaMapper.getEmpresaDtoFromEntity(empresaRepository.findByRfc(facturaPadre.getRfcEmisor()).orElseThrow(() -> new InvoiceManagerException("Pago a credito no encontrado",
								String.format("No existe El emisor %s", facturaPadre.getRfcEmisor()), HttpStatus.SC_NOT_FOUND))))
				.setFacturaPadreDto(mapper.getFacturaDtoFromEntity(facturaPadre))
				.setPagoCredito(pagoMapper.getPagoDtoFromEntity(pagoPadre)).setCurrentPago(pagoDto).build();
	}

	public FacturaContext buildFacturaContextPagoPueCreation(String folio, PagoDto pagoDto) {
		List<Pago> pagos = pagoRepository.findByFolio(folio);
		Optional<Factura> factura = repository.findByFolio(folio);
		Optional<Pago> pagoCredito = pagos.stream()
				.filter(p -> p.getFormaPago().equals(FormaPagoEnum.CREDITO.getPagoValue())).findFirst();
		return new FacturaContextBuilder().setPagos(Arrays.asList(pagoDto))
				.setPagos(pagoMapper.getPagosDtoFromEntities(pagos)).setCurrentPago(pagoDto)
				.setFacturaDto(factura.isPresent() ? mapper.getFacturaDtoFromEntity(factura.get()) : null)
				.setPagoCredito(pagoCredito.isPresent() ? pagoMapper.getPagoDtoFromEntity(pagoCredito.get()) : null)
				.build();
	}

	public FacturaDto buildFacturaDtoPagoPpdCreation(FacturaContext facturaContext) {
		return new FacturaBuilder().setFolioPadre(facturaContext.getFacturaPadreDto().getFolio())
				.setPackFacturacion(facturaContext.getFacturaPadreDto().getPackFacturacion())
				.setCfdi(facturaContext.getFacturaPadreDto().getCfdi())
				.setLineaEmisor(facturaContext.getFacturaPadreDto().getLineaEmisor())
				.setRfcEmisor(facturaContext.getFacturaPadreDto().getRfcEmisor())
				.setMetodoPago(ComplementoPpdDefaults.METODO_PAGO)
				.setRfcRemitente(facturaContext.getFacturaPadreDto().getRfcRemitente())
				.setLineaRemitente(facturaContext.getFacturaPadreDto().getLineaRemitente())
				.setRazonSocialEmisor(facturaContext.getFacturaPadreDto().getRazonSocialEmisor())
				.setRazonSocialRemitente(facturaContext.getFacturaPadreDto().getRazonSocialRemitente())
				.setSolicitante(facturaContext.getFacturaPadreDto().getSolicitante())
				.setTipoDocumento(TipoDocumentoEnum.COMPLEMENTO.getDescripcion())
				.setFormaPago(FormaPagoEnum.findByDesc(facturaContext.getCurrentPago().getFormaPago()).getClave())
				.build();
	}

	public CfdiDto buildFacturaComplementoCreation(FacturaContext facturaContext) {
		CfdiDtoBuilder cfdiBuilder = new CfdiDtoBuilder().setVersion(ComplementoPpdDefaults.VERSION)
				.setCertificado(facturaContext.getEmpresaDto().getCertificado())
				.setLugarExpedicion(facturaContext.getEmpresaDto().getInformacionFiscal().getCp())
				.setMoneda(ComplementoPpdDefaults.MONEDA)
				.setNoCertificado(facturaContext.getEmpresaDto().getNoCertificado())
				.setSerie(ComplementoPpdDefaults.SERIE).setSubtotal(new BigDecimal(ComplementoPpdDefaults.SUB_TOTAL))
				.setTotal(new BigDecimal(ComplementoPpdDefaults.TOTAL))
				.setTipoDeComprobante(ComplementoPpdDefaults.COMPROBANTE);
		CfdiDto dto = cfdiBuilder.build();
		dto.setConceptos(buildFacturaComplementoConceptos(facturaContext));
		dto.setComplemento(new ComplementoDto());
		dto.getComplemento().setPagos(buildFacturaComplementoPagos(facturaContext));
		return dto;
	}

	public List<ConceptoDto> buildFacturaComplementoConceptos(FacturaContext facturaContext) {
		List<ConceptoDto> conceptos = new ArrayList<ConceptoDto>();
		ConceptoDtoBuilder conceptoBuilder = new ConceptoDtoBuilder().setCantidad(ComplementoPpdDefaults.CANTIDAD)
				.setClaveProdServ(ComplementoPpdDefaults.CLAVE_PROD).setClaveUnidad(ComplementoPpdDefaults.CLAVE)
				.setDescripcion(ComplementoPpdDefaults.DESCRIPCION)
				.setImporte(new BigDecimal(ComplementoPpdDefaults.IMPORTE))
				.setValorUnitario(new BigDecimal(ComplementoPpdDefaults.VALOR_UNITARIO));
		conceptos.add(conceptoBuilder.build());
		return conceptos;
	}

	public List<CfdiPagoDto> buildFacturaComplementoPagos(FacturaContext facturaContext) {
		List<CfdiPagoDto> pagos = new ArrayList<CfdiPagoDto>();
		CfdiComplementoPagoBuilder cfdiComplementoPagoBuilder = new CfdiComplementoPagoBuilder()
				.setVersion(ComplementoPpdDefaults.VERSION).setFechaPago(facturaContext.getCurrentPago().getFechaPago())
				.setFormaPago(FormaPagoEnum.findByDesc(facturaContext.getCurrentPago().getFormaPago()).getClave())
				.setMoneda(facturaContext.getCurrentPago().getMoneda())
				.setMonto(facturaContext.getCurrentPago().getMonto())
				.setFolio(facturaContext.getFacturaPadreDto().getFolio())
				.setIdDocumento(facturaContext.getFacturaPadreDto().getUuid())
				.setImportePagado(facturaContext.getCurrentPago().getMonto())
				.setMonedaDr(facturaContext.getCurrentPago().getMoneda())
				.setMetodoPago(ComplementoPpdDefaults.METODO_PAGO).setSerie(ComplementoPpdDefaults.SERIE_PAGO)
				.setNumeroParcialidad(facturaContext.getCtdadComplementos())
				.setImporteSaldoAnterior(facturaContext.getPagoCredito().getMonto())
				.setImporteSaldoInsoluto(facturaContext.getPagoCredito().getMonto()
						.subtract(facturaContext.getCurrentPago().getMonto()));
		pagos.add(cfdiComplementoPagoBuilder.build());
		return pagos;
	}
}
