package com.business.unknow.services.mapper.factura;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.cfdi.ComplementoPago;
import com.business.unknow.model.cfdi.Concepto;
import com.business.unknow.model.cfdi.Retencion;
import com.business.unknow.model.cfdi.Translado;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.CfdiDto;
import com.business.unknow.model.dto.cfdi.CfdiPagoDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.cfdi.ImpuestoDto;
import com.business.unknow.model.dto.cfdi.RetencionDto;
import com.business.unknow.model.dto.services.EmpresaDto;
import com.business.unknow.services.mapper.IgnoreUnmappedMapperConfig;
import com.business.unknow.services.mapper.decorator.FacturaCfdiTranslatorDecorator;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
@DecoratedWith(FacturaCfdiTranslatorDecorator.class)
public interface FacturaCfdiTranslatorMapper {

	@Mappings({ @Mapping(source = "facturaDto.folio", target = "folio"),
			@Mapping(source = "facturaDto.cfdi.emisor.rfc", target = "emisor.rfc"),
			@Mapping(source = "facturaDto.cfdi.emisor.nombre", target = "emisor.nombre"),
			@Mapping(source = "empresaDto.regimenFiscal", target = "emisor.regimenFiscal"),
			@Mapping(source = "facturaDto.cfdi.receptor.rfc", target = "receptor.rfc"),
			@Mapping(source = "facturaDto.cfdi.receptor.nombre", target = "receptor.nombre"),
			@Mapping(source = "facturaDto.cfdi.receptor.usoCfdi", target = "receptor.usoCfdi"),
			@Mapping(source = "facturaDto.cfdi.formaPago", target = "formaPago"),
			@Mapping(source = "facturaDto.cfdi.metodoPago", target = "metodoPago"),
			@Mapping(source = "facturaDto.cfdi.tipoCambio", target = "tipoCambio"),
			@Mapping(source = "facturaDto.cfdi.total", target = "total"),
			@Mapping(source = "facturaDto.cfdi.subtotal", target = "subtotal"),
			@Mapping(source = "facturaDto.cfdi.descuento", target = "descuento"),
			@Mapping(source = "facturaDto.cfdi.moneda", target = "moneda"),
			@Mapping(source = "facturaDto.cfdi.tipoDeComprobante", target = "tipoDeComprobante"),
			@Mapping(source = "empresaDto.certificado", target = "certificado"),
			@Mapping(source = "empresaDto.noCertificado", target = "noCertificado"),
			@Mapping(source = "facturaDto.cfdi.sello", target = "sello"),
			@Mapping(source = "empresaDto.informacionFiscal.cp", target = "lugarExpedicion")})
	public Cfdi cdfiRootInfo(FacturaDto facturaDto, EmpresaDto empresaDto);

	@Mappings({ @Mapping(source = "cfdiDto.folio", target = "folio"),
			@Mapping(source = "cfdiDto.emisor.rfc", target = "emisor.rfc"),
			@Mapping(source = "cfdiDto.emisor.nombre", target = "emisor.nombre"),
			@Mapping(source = "cfdiDto.emisor.regimenFiscal", target = "emisor.regimenFiscal"),
			@Mapping(source = "cfdiDto.receptor.rfc", target = "receptor.rfc"),
			@Mapping(source = "cfdiDto.receptor.nombre", target = "receptor.nombre"),
			@Mapping(source = "cfdiDto.receptor.usoCfdi", target = "receptor.usoCfdi"),
			@Mapping(source = "cfdiDto.total", target = "total"),
			@Mapping(source = "cfdiDto.subtotal", target = "subtotal"),
			@Mapping(source = "cfdiDto.moneda", target = "moneda"),
			@Mapping(source = "cfdiDto.tipoCambio", target = "tipoCambio"),
			@Mapping(source = "cfdiDto.tipoDeComprobante", target = "tipoDeComprobante"),
			@Mapping(source = "empresaDto.certificado", target = "certificado"),
			@Mapping(source = "cfdiDto.noCertificado", target = "noCertificado"),
			@Mapping(source = "cfdiDto.sello", target = "sello"),
			@Mapping(source = "cfdiDto.lugarExpedicion", target = "lugarExpedicion"),
			@Mapping(source = "cfdiDto.serie", target = "serie"),
			@Mapping(target = "formaPago", ignore = true),
			@Mapping(target = "metodoPago", ignore = true),
			@Mapping(target = "descuento", ignore = true),
			@Mapping(source = "cfdiDto.version", target = "version"), @Mapping(target = "conceptos", ignore = true) })
	public Cfdi complementoRootInfo(CfdiDto cfdiDto, EmpresaDto empresaDto);

	@Mappings({ @Mapping(target = "impuestos", ignore = true), @Mapping(source = "cantidad", target = "cantidad"),
			@Mapping(source = "claveProdServ", target = "claveProdServ"),
			@Mapping(source = "descripcion", target = "descripcion"),
			@Mapping(source = "claveUnidad", target = "claveUnidad"), @Mapping(source = "importe", target = "importe"),
			@Mapping(source = "valorUnitario", target = "valorUnitario") })
	public Concepto complementoConcepto(ConceptoDto dto);

	@Mappings({ @Mapping(target = "fechaPago", source = "cfdiPago.fechaPago"),
			@Mapping(target = "formaDePago", source = "cfdiPago.formaPago"),
			@Mapping(target = "moneda", source = "cfdiPago.moneda"),
			@Mapping(target = "monto", source = "cfdiPago.monto"),
			@Mapping(target = "complementoDocRelacionado.folio", source = "cfdiPago.folio"),
			@Mapping(target = "complementoDocRelacionado.idDocumento", source = "cfdiPago.idDocumento"),
			@Mapping(target = "complementoDocRelacionado.impPagado", source = "cfdiPago.importePagado"),
			@Mapping(target = "complementoDocRelacionado.impSaldoAnt", source = "cfdiPago.importeSaldoAnterior"),
			@Mapping(target = "complementoDocRelacionado.impSaldoInsoluto", source = "cfdiPago.importeSaldoInsoluto"),
			@Mapping(target = "complementoDocRelacionado.metodoDePagoDR", source = "cfdiPago.metodoPago"),
			@Mapping(target = "complementoDocRelacionado.monedaDR", source = "cfdiPago.monedaDr"),
			@Mapping(target = "complementoDocRelacionado.numParcialidad", source = "cfdiPago.numeroParcialidad"),
			@Mapping(target = "complementoDocRelacionado.serie", source = "cfdiPago.serie") })
	public ComplementoPago complementoComponente(CfdiPagoDto cfdiPago);

	@Mappings({ @Mapping(target = "impuestos", ignore = true) })
	public Concepto cfdiConcepto(ConceptoDto dto);

	public Translado cfdiImpuesto(ImpuestoDto dto);
	
	public Retencion cfdiRetencion(RetencionDto dto);

	public Translado cfdiImpuestoGlobal(ImpuestoDto dto);

}
