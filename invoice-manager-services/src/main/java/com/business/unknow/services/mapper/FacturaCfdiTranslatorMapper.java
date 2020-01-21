package com.business.unknow.services.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.cfdi.ComplementoPago;
import com.business.unknow.model.cfdi.Concepto;
import com.business.unknow.model.cfdi.Translado;
import com.business.unknow.model.dto.FacturaDto;
import com.business.unknow.model.dto.cfdi.ConceptoDto;
import com.business.unknow.model.dto.cfdi.ImpuestoDto;
import com.business.unknow.model.dto.services.EmpresaDto;
import com.business.unknow.model.dto.services.PagoDto;
import com.business.unknow.services.mapper.decorator.FacturaCfdiTranslatorDecorator;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
@DecoratedWith(FacturaCfdiTranslatorDecorator.class)
public interface FacturaCfdiTranslatorMapper {

	@Mappings({ @Mapping(source = "facturaDto.folio", target = "folio"),
			@Mapping(source = "facturaDto.rfcEmisor", target = "emisor.rfc"),
			@Mapping(source = "facturaDto.razonSocialEmisor", target = "emisor.nombre"),
			@Mapping(source = "facturaDto.rfcRemitente", target = "receptor.rfc"),
			@Mapping(source = "facturaDto.razonSocialRemitente", target = "receptor.nombre"),
			@Mapping(source = "facturaDto.cfdi.usoCfdi", target = "receptor.usoCfdi"),
			@Mapping(source = "facturaDto.cfdi.formaPago", target = "formaPago"),
			@Mapping(source = "facturaDto.cfdi.metodoPago", target = "metodoPago"),
			@Mapping(source = "facturaDto.cfdi.total", target = "total"),
			@Mapping(source = "facturaDto.cfdi.subtotal", target = "subtotal"),
			@Mapping(source = "facturaDto.cfdi.descuento", target = "descuento"),
			@Mapping(source = "facturaDto.cfdi.moneda", target = "moneda"),
			@Mapping(source = "facturaDto.cfdi.tipoDeComprobante", target = "tipoDeComprobante"),
			@Mapping(source = "empresaDto.certificado", target = "certificado"),
			@Mapping(source = "empresaDto.noCertificado", target = "noCertificado"),
			@Mapping(source = "facturaDto.cfdi.sello", target = "sello"),
			@Mapping(source = "empresaDto.informacionFiscal.cp", target = "lugarExpedicion"),
			@Mapping(source = "empresaDto.regimenFiscal", target = "emisor.regimenFiscal") })
	public Cfdi cdfiRootInfo(FacturaDto facturaDto, EmpresaDto empresaDto);

	@Mappings({ @Mapping(source = "facturaDto.folio", target = "folio"),
			@Mapping(source = "facturaDto.rfcEmisor", target = "emisor.rfc"),
			@Mapping(source = "facturaDto.razonSocialEmisor", target = "emisor.nombre"),
			@Mapping(constant = "P01",target = "receptor.usoCfdi"),
			@Mapping(source = "facturaDto.rfcRemitente", target = "receptor.rfc"),
			@Mapping(source = "facturaDto.razonSocialRemitente", target = "receptor.nombre"),
			@Mapping(constant = "0", target = "total"), 
			@Mapping(constant = "0", target = "subtotal"),
			@Mapping(constant = "XXX", target = "moneda"),
			@Mapping(constant = "P", target = "tipoDeComprobante"),
			@Mapping(source = "empresaDto.certificado", target = "certificado"),
			@Mapping(source = "empresaDto.noCertificado", target = "noCertificado"),
			@Mapping(source = "facturaDto.cfdi.sello", target = "sello"),
			@Mapping(source = "empresaDto.informacionFiscal.cp", target = "lugarExpedicion"),
			@Mapping(source = "empresaDto.regimenFiscal", target = "emisor.regimenFiscal"),
			@Mapping(target = "formaPago", ignore = true),
			@Mapping(target = "metodoPago", ignore = true)})
	public Cfdi complementoRootInfo(FacturaDto facturaDto, EmpresaDto empresaDto);

	@Mappings({ @Mapping(target = "impuestos", ignore = true), 
			@Mapping(constant = "1", target = "cantidad"),
			@Mapping(constant = "84111506", target = "claveProdServ"),
			@Mapping(constant = "Pago", target = "descripcion"), 
			@Mapping(constant = "ACT", target = "claveUnidad"),
			@Mapping(constant = "0", target = "importe"), 
			@Mapping(constant = "0", target = "valorUnitario") })
	public Concepto complementoConcepto(ConceptoDto dto);

	@Mappings({ @Mapping(target = "formaDePago", source = "facturaDto.cfdi.formaPago"),
			@Mapping(target = "moneda", source = "pagoDto.moneda"),
			@Mapping(target = "complementoDocRelacionado.folio", source = "facturaDto.folioPadre"),
			@Mapping(target = "complementoDocRelacionado.idDocumento", source = "facturaDto.uuid"),
			@Mapping(target = "complementoDocRelacionado.metodoDePagoDR", source = "facturaDto.cfdi.metodoPago"),
			@Mapping(target = "complementoDocRelacionado.monedaDR", source = "pagoDto.moneda"),
			@Mapping(target = "complementoDocRelacionado.serie", constant = "PFP")})
	public ComplementoPago complementoComponente(FacturaDto facturaDto,PagoDto pagoDto);

	@Mappings({ @Mapping(target = "impuestos", ignore = true) })
	public Concepto cfdiConcepto(ConceptoDto dto);

	public Translado cfdiImpuesto(ImpuestoDto dto);

	public Translado cfdiImpuestoGlobal(ImpuestoDto dto);

}
