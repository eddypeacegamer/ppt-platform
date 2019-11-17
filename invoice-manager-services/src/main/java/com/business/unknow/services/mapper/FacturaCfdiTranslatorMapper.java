package com.business.unknow.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.business.unknow.model.cfdi.Cfdi;
import com.business.unknow.model.factura.FacturaDto;

@Mapper
public interface FacturaCfdiTranslatorMapper {

	@Mappings({ @Mapping(source = "folio", target = "folio"),
				@Mapping(source = "rfcEmisor", target = "emisor.rfc"),
				@Mapping(source = "razonSocialEmisor", target = "emisor.nombre"),
				@Mapping(source = "rfcRemitente", target = "receptor.rfc"),
				@Mapping(source = "razonSocialEmisor", target = "receptor.nombre"),
				@Mapping(source = "formaPago", target = "formaPago"),
				@Mapping(source = "metodoPago", target = "metodoPago"),
				@Mapping(source = "total", target = "total"),
				@Mapping(source = "subtotal", target = "subtotal"),
				@Mapping(source = "descuento", target = "descuento")})
	public Cfdi cdfiRootInfo(FacturaDto facturaDto);

}
