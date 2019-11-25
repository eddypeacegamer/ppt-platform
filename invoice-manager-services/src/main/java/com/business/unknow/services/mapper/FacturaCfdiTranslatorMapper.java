package com.business.unknow.services.mapper;


import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.business.unknow.model.EmpresaDto;
import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.cfdi.Concepto;
import com.business.unknow.model.cfdi.Translado;
import com.business.unknow.model.factura.FacturaDto;
import com.business.unknow.model.factura.cfdi.components.ConceptoDto;
import com.business.unknow.model.factura.cfdi.components.ImpuestoDto;
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
				@Mapping(source = "facturaDto.formaPago", target = "formaPago"),
				@Mapping(source = "facturaDto.metodoPago", target = "metodoPago"),
				@Mapping(source = "facturaDto.total", target = "total"),
				@Mapping(source = "facturaDto.subtotal", target = "subtotal"),
				@Mapping(source = "facturaDto.descuento", target = "descuento"),
				@Mapping(source = "facturaDto.cfdi.moneda", target = "moneda"),
				@Mapping(source = "facturaDto.cfdi.tipoDeComprobante", target = "tipoDeComprobante"),
				@Mapping(source = "empresaDto.certificado", target = "certificado"),
				@Mapping(source = "empresaDto.noCertificado", target = "noCertificado"),
				@Mapping(source = "facturaDto.cfdi.sello", target = "sello"),
				@Mapping(source = "empresaDto.informacionFiscal.cp", target = "lugarExpedicion"),
				@Mapping(source = "empresaDto.regimenFiscal", target = "emisor.regimenFiscal")})
	public Cfdi cdfiRootInfo(FacturaDto facturaDto,EmpresaDto empresaDto);
	
	@Mappings({ @Mapping(target = "impuestos", ignore = true)})
	public Concepto cfdiConcepto(ConceptoDto dto);
	
	public Translado cfdiImpuesto(ImpuestoDto dto);
	
	public Translado cfdiImpuestoGlobal(ImpuestoDto dto);

}
